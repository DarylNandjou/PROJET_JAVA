package affichageIm;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

class ComposantImage extends JComponent{
    private static final long serialVersionUID = 1L;
    private Image image;
    public ComposantImage(String name){
    	
    	
    	try (var input = ComposantImage.class.getResourceAsStream( "/images/" + name + "_0.png")){
    		if (input == null) {
          System.err.println("Image not found: " + name);
          return;
          }
    		image = ImageIO.read(input);  
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    public void display (Graphics g, int x, int y){
        if(image == null) return;
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        g.drawImage(image, x, y, this);

        for (int i = 0; i*imageWidth <= getWidth(); i++)
            for(int j = 0; j*imageHeight <= getHeight();j++)
                if(i+j>0) g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
    }
}

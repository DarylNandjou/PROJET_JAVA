package affichageIm;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

class ImageComponent extends JComponent{
    private static final long serialVersionUID = 1L;
    private Image image;
    public ImageComponent(String name){/*
        try{
            File im = new File("/images/"+name+".png");
            image = ImageIO.read(im);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    	try (var input = ImageComponent.class.getResourceAsStream( "/images/" + name + ".png")){
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

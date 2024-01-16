package affichageIm;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

class ComposantImage {
  private Image image;

  public ComposantImage(String name) {
      try (var input = ComposantImage.class.getResourceAsStream("/images/" + name + "_0.png")) {
          if (input == null) {
              System.err.println("Image not found: " + name);
              return;
          }
          image = ImageIO.read(input);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public void display(Graphics g, int x, int y) {
      if (image == null) return;
      g.drawImage(image, x, y, null);
  }
}

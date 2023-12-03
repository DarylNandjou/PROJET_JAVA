package fr.umlv.zen5.demo;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.swing.JComponent;

import javax.imageio.ImageIO;

public class Demo {
  /*static class Area {
    private Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, 0, 0);
    
    void draw(ApplicationContext context, float x, float y) {
      context.renderFrame(graphics -> {
        // hide the previous rectangle
        graphics.setColor(Color.ORANGE);
        graphics.fill(ellipse);
        
        // show a new ellipse at the position of the pointer
        graphics.setColor(Color.MAGENTA);
        ellipse = new Ellipse2D.Float(x - 20, y - 20, 40, 40);
        graphics.fill(ellipse);
      });
    }
  }*/
	
	
	
	//Display image : with im.getGraphics()
  
	
	
	
  public static void main(String[] args) {
	  
	  
	  
	  
	  
	  
	  var test_im = new ImageComponent("ALGAE_0");
	  
    Application.run(Color.BLACK, context -> {
      
      // get the size of the screen
      ScreenInfo screenInfo = context.getScreenInfo();
      float width = screenInfo.getWidth();
      float height = screenInfo.getHeight();
      System.out.println("size of the screen (" + width + " x " + height + ")");
      
      /*context.renderFrame(graphics -> {
        graphics.setColor(Color.ORANGE);
        graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
        
      });*/
   
      
      //Area area = new Area();
      for(;;) {
        Event event = context.pollOrWaitEvent(10);
        if (event == null) {  // no event
          continue;
        }
        Action action = event.getAction();
        if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
          System.out.println("abort abort !");
          context.exit(0);
          return;
        }
        System.out.println(event);
        
        Point2D.Float location = event.getLocation();
        //area.draw(context, location.x, location.y);
        context.renderFrame(graphics ->{
        	test_im.display(graphics, (int)location.x, (int)location.y);
        });
        
      }
    });
  }
}
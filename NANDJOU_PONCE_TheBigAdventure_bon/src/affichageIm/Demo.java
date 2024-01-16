package affichageIm;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.ScreenInfo;

public class Demo {
	
	
	
  public static void main(String[] args) {
	  
	  var test_im = new ComposantImage("ALGAE");
	  
    Application.run(Color.WHITE, context -> {
      
      // get the size of the screen
      ScreenInfo screenInfo = context.getScreenInfo();
      float width = screenInfo.getWidth();
      float height = screenInfo.getHeight();
      System.out.println("size of the screen (" + width + " x " + height + ")");
      
   
      
      
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
        
        context.renderFrame(graphics ->{
        	graphics.setColor(Color.WHITE);
            graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
        	test_im.display(graphics, (int)location.x, (int)location.y);
        });
        
      }
    });
  }
}
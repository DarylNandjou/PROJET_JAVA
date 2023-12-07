package verifyMap;
import java.io.File;
import java.io.IOException;

public class MapCheck {
  
  public static boolean ValidMap(String name) {
    File file = new File(name);
    
    if(file.length() == 0) {
      System.out.println("La carte est vide !");
      return false;
    }
    return true;
  }
  
  

  
  public static void main(String[] args) throws IOException {
    String filePath = "maps/badGridDataEncodingDefinedTwice.map";
    //String map = ReadFile.readAllLines(filePath);

    if (ValidMap(filePath)) {
        System.out.println("La carte est valide !");
    }
  }
	
}

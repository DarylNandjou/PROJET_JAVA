package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

  public static void main(String[] args) throws IOException {
	  
    var path = Path.of("demo.map");
    var text = Files.readString(path);
    var lexer = new Lexer(text);
    Result result;
    while((result = lexer.nextResult()) != null) {
      System.out.println(result);
    }
  }
}
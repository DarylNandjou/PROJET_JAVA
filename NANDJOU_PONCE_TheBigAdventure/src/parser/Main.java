package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import map.Map;

public class Main {

  public static void main(String[] args) throws IOException {
	var map = new Map();
	String tile="";
	String code="";//encodings
	boolean encodingTile=true;
	
    var path = Path.of("demo.map");
    var text = Files.readString(path);
    var lexer = new Lexer(text);
    Result result;
    String state = "base"; // base, size, encodings, data, element, name, skin,
    					   // player, position, health, kind, zone, behavior, damage,
    				 	   // text, steal, trade, locked, flow, phantomized, teleport
    while((result = lexer.nextResult()) != null) {
    	
    	
    	switch(state) {
    	case "base":
    		switch(result.content()) {
    		case "size":state="size";break;
    		case "encodings":state="encodings";break;
    		case "data":state="data";break;
    		case "element":state="element";break;
    		}
    		break;
    	case "size":
    		switch(result.content()) {
    		case "(":break;
    		}
    		encodingTile=true;//utilise la même variable que les encodings
    		break;
    	case "encodings":
    		switch(result.token()) {
    		case IDENTIFIER:
    			switch (result.content()) {
    			case "data":state="data";encodingTile=true;break;
    			case "element":state="element";encodingTile=true;break;
    			case "size":state="size";encodingTile=true;break;
	//encodingTile=true permet de ne pas rentrer des données bizarres dans la map
	//parce qu'on sort des encodings dès qu'on trouve les mots data, element ou size
	//On évite ainsi de rentrer dans la partie "else" en-dessous
    			}
    			if (encodingTile) {
    			tile=result.content();
    			encodingTile=false;
    			}
    			else {
    				code=result.content();
    				encodingTile=true;
    				map.encoder.add(code,tile);
    			}
    			break;
    		default:break; 
    		}
    	case "data":
    		switch(result.content()) {
    		case "encodings":state="encodings";break;
    		case "element" : state="element";break;
    		case "size":state="size";break;
    		}
    		break;
    		
    		
    	}
    	
    	
    	
    	
        System.out.println(result);
    }
  }
}
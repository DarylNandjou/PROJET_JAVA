package verifyMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import parser.Lexer;
import parser.Token;
import parser.Result;

public class MapCheck {
  
  public static boolean validMap(String map) {
    File file = new File(map);
    
    if(file.length() == 0) {
      System.out.println("La carte est vide !");
      return false;
    }
    return true;
  }
  
  
  private static int dataLig(String map) {
  	
    try {
        boolean blockData = false;
        int dataLig = 0;

        List<String> lines = Files.readAllLines(Paths.get(map));

        for (String line : lines) {
            line = line.trim();
            
            if (line.startsWith("data: \"\"\"")) {
                blockData = true;
                continue; 
            }

            if (blockData) {
                if (line.startsWith("\"\"\"")) {
                    blockData = false;
                    break;
                }

                // Si la ligne n'est pas vide, l'incrémenter
                if (!line.isEmpty()) {
                    dataLig++;
                }
            }
        }

        return dataLig;
    } catch (IOException e) {
        e.printStackTrace();
    }

    return 0;
}
  
  
  private static int  dataCol(String map){
  	 int nbCol = -1;  // Longueur attendue de la première ligne

  	try {
  		  boolean blockData = false;
  		  List<String> lines = Files.readAllLines(Paths.get(map));
  	     	    int dataCol = 0;

  	    for (int i = 0; i < lines.size(); i++) {
  	    	 String line = lines.get(i).trim();      
  	        
  	        if (line.startsWith("data: \"\"\"")) {
              blockData = true;
              continue; 
  	        }
  	        
  	        if (blockData) {
  	        	
              if (line.startsWith("\"\"\"")) {
                  blockData = false;
                  break;
              }

              // Si la ligne n'est pas vide, l'incrémenter
              if (!line.isEmpty()) {
              	
	              	if (nbCol == -1) {
	     	            // La première ligne définit la longueur attendue
	     	            nbCol = line.length();
	              	} 
	              	else if (line.length() != nbCol) {
	     	            // La longueur de cette ligne ne correspond pas à la longueur attendue
	     	            System.out.println("Erreur à la ligne " + (i + 1) + ": Les longueurs des lignes ne correspondent pas.");
	     	            return -1;
	              	}
            }
  	      } 
  		} 
  	    //System.out.println("Toutes les lignes ont la même longueur.");
  	}catch (IOException e) {
          e.printStackTrace();
  	 }
    // Toutes les lignes ont la même longueur
    
		return nbCol;
}
  
  
  public static boolean validSize(String filePath) {

    int lig = 0;
    int col = 0;
    int dataCol = 0;
    int dataLig =0;

    // Analyser le texte à l'aide du lexer
    Result result;
    Token token;
    String map;
    try {
      map = ReadFile.readAllLines(filePath).stream().collect(Collectors.joining("\n"));
      Lexer lexer = new Lexer(map);
      while ((result = lexer.nextResult()) != null) {

          if (Token.NUMBER.equals(result.token())) {
             
              col = Integer.parseInt(result.content());
              lexer.nextResult(); // Ignorer "x"
              lig = Integer.parseInt(lexer.nextResult().content());
              lexer.nextResult(); // Ignorer ")"             
              break; // Sortir de la boucle une fois que les dimensions ont été trouvées
          }
      }
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    dataLig = dataLig(filePath);
    dataCol = dataCol(filePath);
    
    if(dataCol == -1) {
    	return false;
    }
//    System.out.println(dataCol);
//    System.out.println(dataLig);
//    System.out.println(col);
//    System.out.println(lig);
    return lig == dataLig && col == dataCol;
  }
  
  
  
  
  public static List<String> extractEncodings(String filePath) {
    List<String> encodingList = new ArrayList<>();

    try {
    	List<String> lines = ReadFile.readAllLines(filePath);
      Lexer lexer = new Lexer(lines.stream().collect(Collectors.joining("\n")));
      boolean blockEnco = false;

      Result result;
      while ((result = lexer.nextResult()) != null) {
        if ("encodings".equals(result.content())) {
            blockEnco = true;

            } 
        else if (blockEnco && "data".equals(result.content())) {
          // Ajouter le contenu entre les parenthèses à la liste
        break;
      }
        
        else if ((blockEnco && Token.IDENTIFIER.equals(result.token()))) {
                // Sortir de la boucle si la section "data" est atteinte
        	encodingList.add(result.content());
            } 
    
        }

        System.out.println("Encodings list: " + encodingList);

    } catch (IOException e) {
        e.printStackTrace();
    }

    return encodingList;
}

  
  public static Map<String, String> validEncoding(List<String> encodingList) {
  	 Map<String, String> encodingMap = new HashMap<>();
     List<String> seenValues = new ArrayList<>();
     for (int i = 0; i < encodingList.size(); i += 2) {
       if (i + 1 < encodingList.size()) {
           String key = encodingList.get(i);
           String value = encodingList.get(i + 1);
           
           if (!isaLetter(value)) {
             throw new IllegalArgumentException("La valeur doit être une seule lettre.");
           }
           else {
          	 if (seenValues.contains(value)) {
               // Si la valeur existe déjà, lancer une exception
               throw new IllegalArgumentException("La même valeur ne peut pas être associée à plusieurs clés.");
            	 	}
             encodingMap.put(key, value);
             seenValues.add(value);
             }
           }
          	 
        }

     return encodingMap;
}

  private static boolean isaLetter(String value) {
    return value.length() == 1;
  }
  
  public static boolean validEncoding2(List<String> encodingList) {
  	 Map<String, String> encodingMap = new HashMap<>();
     List<String> seenValues = new ArrayList<>();

     for (int i = 0; i < encodingList.size(); i += 2) {
         if (i + 1 < encodingList.size()) {
             String key = encodingList.get(i);
             String value = encodingList.get(i + 1);
             
             if (!isaLetter(value)) {
               throw new IllegalArgumentException("La Valeur doit être une seule lettre.");
             }
             else {
            	 if (seenValues.contains(value)) {
                 // Si la valeur existe déjà, lancer une exception
                 throw new IllegalArgumentException("La même valeur ne peut pas être associée à plusieurs clés.");
	            	 	}
	             encodingMap.put(key, value);
	             seenValues.add(value);
	             }
             }
            	 
          }

     return true;
}

  
 
  
  public static void main(String[] args) throws IOException {
    String filePath = "maps/monster_house.map";
    //String map = ReadFile.readAllLines(filePath);

//    if (validMap(filePath)) {
//        System.out.println("La carte est valide !");
//        if(validSize(filePath)) {
//          System.out.println("La taille de la carte est valide !");
//        }
//        else {
//          System.out.println("La taille de la carte n'est pas valide !");
//        }
//    }
    //validEncoding(filePath);
   // extractEncodings( filePath);
    if(validMap(filePath)) {
    	System.out.println("La carte existe !");
    }
    else {
    	System.out.println("La carte n'existe pas !");
    }
    if(validSize(filePath)) {
    	System.out.println("La taille de la carte est valide !");
    }
    else {
    	System.out.println("La taille de la carte n'est pas valide !");
    }
    
    
    List<String> encodingList = extractEncodings(filePath);
    //Map<String, String> encodingMap = validEncoding(encodingList);




    // Utilisez la liste de HashMap comme nécessaire
    //System.out.println("HashMap List: " + encodingMap);
    Map<String, String> result = validEncoding(encodingList);

    // Affiche le résultat
    System.out.println("Encodings valides : ");
    result.forEach((key, value) -> System.out.println(key + ": " + value));
    
    
    if(validMap(filePath) && validSize(filePath) && validEncoding2(encodingList)) {
    	System.out.println("La carte est VALIDE !");
    }
    else {
    	System.out.println("La carte n'est pas valide !");
    }

    //System.out.println();

  }
}
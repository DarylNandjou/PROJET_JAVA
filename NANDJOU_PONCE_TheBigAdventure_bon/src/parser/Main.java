package parser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import verifyMap.ReadFile;



public class Main {

      public static void main(String[] args) {
        // Lire le contenu du fichier de test
        String filePath = "maps/monster_house.map";
        try {
             
              String map = ReadFile.readAllLines(filePath).stream().collect(Collectors.joining("\n"));
              // Créer une instance du lexer avec le contenu du fichier de test
              Lexer lexer = new Lexer(map);

              // Analyser le texte à l'aide du lexer
              Result result;
              while ((result = lexer.nextResult()) != null) {
                  System.out.println("Token: " + result.token() + ", Content: " + result.content());
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }

}

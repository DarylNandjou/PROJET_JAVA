package verifyMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {
  public static List<String> readAllLines(String filePath) throws IOException{
    Path path = Paths.get(filePath);
    return Files.readAllLines(path);
  }
}

package test_data;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.swing.text.EditorKit;
import test_data.models.LoginCred;

public class DataObjectBuilder {

  public static <T> T buildDataObject(String relativeFilePath, Class<T> dataType) {
    Reader reader;

    // Read the JSON content
    String absoluteFilePath = System.getProperty("user.dir").concat(relativeFilePath);
    if (Files.exists(Paths.get(absoluteFilePath))) {
      try {
        reader = Files.newBufferedReader(Paths.get(absoluteFilePath));
      } catch (Exception e) {
        System.out.println(e.getMessage());
        throw new RuntimeException("[ERR] Error while reading file");
      }
    } else {
      try {
        InputStream inputStream = DataObjectBuilder.class.getResourceAsStream(relativeFilePath);
        reader = new InputStreamReader(inputStream);
      } catch (Exception e) {
        throw new RuntimeException("ERR error while reading file from resource: " + e.getMessage());
      }
    }
    Gson gson = new Gson();
    return gson.fromJson(reader, dataType);
  }

  public static void main(String[] args) {
    String inValidFilePath = "src/test/java/test_data/authen/LoginCredData.json";
    String validFilePath = "/src/main/java/test_data/authen/LoginCredData.json";

//    LoginCred loginCred = buildDataObject(inValidFilePath, LoginCred.class);
    LoginCred[] loginCred = buildDataObject(validFilePath, LoginCred[].class);

    System.out.println(Arrays.toString(loginCred));
  }
}

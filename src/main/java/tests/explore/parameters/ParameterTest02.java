package tests.explore.parameters;

import java.util.GregorianCalendar;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest02 {

  @Test
  @Parameters({"udid", "systemPort"})
  public void getParams(String teo, String ti) {
    try {
      Thread.sleep(1000);
    } catch (Exception ignored) {
    }
    System.out.println(new GregorianCalendar().getTime());
    System.out.printf("systemPort: %s | udid: %s\n", teo, ti);
  }

}

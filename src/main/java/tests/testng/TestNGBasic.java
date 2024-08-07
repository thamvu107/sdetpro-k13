package tests.testng;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

public class TestNGBasic {

  @Test
  public void openApp() {
    AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
    appiumDriver.quit();
  }

}

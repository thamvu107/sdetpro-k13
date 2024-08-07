package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import models.components.global.NavComponent;
import models.pages.BasePage;

public class TestComponentBasedModel {

  public static void main(String[] args) {
    AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

    try {
      BasePage page = new BasePage(appiumDriver);
      NavComponent navComponent = page.navComponent();
      // Navigate to the login screen
      navComponent.clickOnLoginIcon();

      // DEBUG purpose only
      Thread.sleep(3000);
    } catch (Exception e) {
      e.printStackTrace();
    }

    appiumDriver.quit();
  }

}

package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import models.pages.LoginPage03;

public class LoginPageTest03 {

  public static void main(String[] args) {
    AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

    try {
      LoginPage03 loginPage = new LoginPage03(appiumDriver);
      loginPage.navComponent().clickOnLoginIcon();
      loginPage
          .inputUsername("teo@sth.com")
          .inputPassword("12345678")
          .clickOnLoginBtn();
    } catch (Exception e) {
      e.printStackTrace();
    }

    appiumDriver.quit();
  }


}

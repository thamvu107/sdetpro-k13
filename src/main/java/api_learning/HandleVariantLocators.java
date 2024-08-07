package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ElementHandler;

public class HandleVariantLocators {

  private static Map<Platform, By> navloginBtnLocMap = Map.of(
      Platform.ANDROID, AppiumBy.accessibilityId("Login"),
      Platform.IOS, AppiumBy.accessibilityId("try-to-have-difference-here")
  );
  private static By emailFieldLoc = AppiumBy.accessibilityId("input-email");
  private static By passwordLoc = AppiumBy.accessibilityId("input-password");
  private static By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");

  public static void main(String[] args) {

    AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

    try {
      ElementHandler elementHandler = new ElementHandler(appiumDriver);
      WebElement navLoginBtnEle = elementHandler.findElement(navloginBtnLocMap);
      navLoginBtnEle.click();

      WebElement emailFieldEle = appiumDriver.findElement(emailFieldLoc);
      emailFieldEle.clear();
      emailFieldEle.sendKeys("teo@sth.com");

      // Input password
      WebElement passwordEle = appiumDriver.findElement(passwordLoc);
      passwordEle.sendKeys("12345678");

      // Click on Login Btn
      WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
      loginBtnEle.click();

      // Debug purpose only
      Thread.sleep(2000);
    } catch (Exception e) {
      e.printStackTrace();
    }

    appiumDriver.quit();
  }

}


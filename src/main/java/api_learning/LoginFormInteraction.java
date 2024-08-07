package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFormInteraction {

  public static void main(String[] args) {
    AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

    try {
      // Login Action
//      By navloginBtnLoc = By.xpath("//android.widget.Button[@content-desc='Login']");
      By navloginBtnLoc = AppiumBy.accessibilityId("Login");
      WebElement navLoginBtnEle = appiumDriver.findElement(navloginBtnLoc);
      navLoginBtnEle.click();

      // Input username
//      By emailFieldLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-email\"]");
      By emailFieldLoc = AppiumBy.accessibilityId("input-email");
      WebElement emailFieldEle = appiumDriver.findElement(emailFieldLoc);
      emailFieldEle.clear();
      emailFieldEle.sendKeys("teo@sth.com");

      // Input password
//      By passwordLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-password\"]");
      By passwordLoc = AppiumBy.accessibilityId("input-password");
      WebElement passwordEle = appiumDriver.findElement(passwordLoc);
      passwordEle.sendKeys("12345678");

      // Click on Login Btn
      By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
      WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
      loginBtnEle.click();

      // Wait for the dialog displayed
      By dialogMsgLoc = AppiumBy.id("android:id/message");
      By dialogBtnLoc = AppiumBy.id("android:id/button1");

      // Using explicit wait
      WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15));
      WebElement dialogMsgEle = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogMsgLoc));
      System.out.printf("Dialog msg: %s\n", dialogMsgEle.getText());
      appiumDriver.findElement(dialogBtnLoc).click();

    } catch (Exception e) {
      e.printStackTrace();
    }

    // DEBUG PURPOSE ONLY
    try {
      Thread.sleep(3000);
    } catch (Exception ignored) {
    }

    appiumDriver.quit();
  }

  /*
  * Locator | By | from AppiumBy
  * Element | WebElement
  *
  * */

}

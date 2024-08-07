package models.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import models.components.login.LoginDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

  private final static By usernameSel = AppiumBy.accessibilityId("input-email");
  private final static By passwordSel = AppiumBy.accessibilityId("input-password");
  private final static By loginBtnSel = AppiumBy.accessibilityId("button-LOGIN");
  private final static By invalidEmailTxtSel = AppiumBy.xpath(
      "//*[contains(@text, 'valid email')]");
  private final static By invalidPasswordTxtSel = AppiumBy.xpath(
      "//*[contains(@text, 'at least 8')]");

  public LoginPage(AppiumDriver appiumDriver) {
    super(appiumDriver);
  }

  public WebElement username() {
    return component.findElement(usernameSel);
  }

  public WebElement password() {
    return component.findElement(passwordSel);
  }

  public WebElement loginBtn() {
    return component.findElement(loginBtnSel);
  }

  public String getInvalidEmailStr() {
    return component.findElement(invalidEmailTxtSel).getText();
  }

  public String getInvalidPasswordStr() {
    return component.findElement(invalidPasswordTxtSel).getText();
  }


  @Step("Input username as {username}")
  public void inputUsername(String username) {
    username().sendKeys(username);
  }

  @Step("Input password as {password}")
  public void inputPassword(String password) {
    password().sendKeys(password);
  }

  @Step("Click on login button")
  public void clickOnLoginBtn() {
    loginBtn().click();
  }

  public LoginDialog loginDialog() {
    return new LoginDialog(appiumDriver);
  }

}

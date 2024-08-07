package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import models.pages.LoginPage;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.Assert;
import test_flows.BaseFlow;

public class LoginFlow extends BaseFlow {

  private String username;
  private String password;
  private LoginPage loginPage;

  public LoginFlow(AppiumDriver appiumDriver, String username, String password) {
    super(appiumDriver);
    this.username = username;
    this.password = password;
    loginPage = new LoginPage(appiumDriver);
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void login() {
    if (!username.isEmpty()) {
      loginPage.username().clear();
      loginPage.inputUsername(username);
    }
    if (!password.isEmpty()) {
      loginPage.password().clear();
      loginPage.inputPassword(password);
    }
    loginPage.clickOnLoginBtn();
  }

  public void verifyLogin() {
    final int MIN_PASS_LENGTH = 8;
    boolean isEmailValid = EmailValidator.getInstance().isValid(username);
    boolean isPasswordValid = password.length() >= MIN_PASS_LENGTH;

    if (isEmailValid && isPasswordValid) {
      verifyCorrectLoginCreds();
    }

    if (!isEmailValid) {
      verifyIncorrectEmailLogin();
    }

    if (!isPasswordValid) {
      verifyIncorrectPassword();
    }

  }

  private void verifyCorrectLoginCreds() {
    String expectedSuccessMsg = "You are logged in!";
    String actualSuccessMsg = loginPage.loginDialog().getDialogMsg();
    Assert.assertEquals(expectedSuccessMsg, actualSuccessMsg, "[ERR] Wrong success login msg");
  }

  private void verifyIncorrectEmailLogin() {
    String expectedInvalidEmailStr = "Please enter a valid email address";
    String actualInvalidEmailStr = loginPage.getInvalidEmailStr();
    if (!actualInvalidEmailStr.equalsIgnoreCase(expectedInvalidEmailStr)) {
      throw new RuntimeException("[ERR] Invalid email string incorrect!");
    }
  }

  private void verifyIncorrectPassword() {
    String expectedInvalidPasswordStr = "Please enter at least 8 characters";
    String actualInvalidPasswordStr = loginPage.getInvalidPasswordStr();
    if (!actualInvalidPasswordStr.equalsIgnoreCase(expectedInvalidPasswordStr)) {
      throw new RuntimeException("[ERR] Invalid password string incorrect!");
    }
  }

}

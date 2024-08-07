package tests.authen;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.models.LoginCred;
import test_flows.authentication.LoginFlow;
import tests.BaseTest;

public class AuthenticationTest extends BaseTest {



  @Test(dataProvider = "loginCredData")
  public void loginWithCreds(LoginCred loginCred) {
    AppiumDriver appiumDriver = getDriver();
    LoginFlow loginFlow = new LoginFlow(
        appiumDriver, loginCred.getEmail(), loginCred.getPassword()
    );
    loginFlow.gotoLoginScreen();
    loginFlow.login();
    loginFlow.verifyLogin();
  }

  @DataProvider
  public LoginCred[] loginCredData() {
    String loginCredDataPath = "/src/main/java/test_data/authen/LoginCredData.json";
    return DataObjectBuilder.buildDataObject(loginCredDataPath, LoginCred[].class);
  }

}

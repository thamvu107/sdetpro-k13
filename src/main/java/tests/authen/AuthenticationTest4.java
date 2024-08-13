package tests.authen;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.models.LoginCred;
import test_flows.authentication.LoginFlow;
import tests.BaseTest;

import java.lang.reflect.Method;

public class AuthenticationTest4 extends BaseTest {



  @Test(dataProvider = "loginCredData")
  public void loginWithCreds(LoginCred loginCred, Method method) {
    AppiumDriver appiumDriver = getDriver();
    System.out.println("Device: " + udid + "Class -------------: " + getClass().getSimpleName() + ", test method: " + method.getName() + " , appiumDriver: " + appiumDriver);

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

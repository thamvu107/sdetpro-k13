package test_flows;

import io.appium.java_client.AppiumDriver;
import models.pages.BasePage;

public class BaseFlow {

  protected final AppiumDriver appiumDriver;

  public BaseFlow(AppiumDriver appiumDriver) {
    this.appiumDriver = appiumDriver;
  }

  public void gotoLoginScreen() {
    new BasePage(appiumDriver).navComponent().clickOnLoginIcon();
  }

}

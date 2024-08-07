package models.components.global;

import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import java.util.Map;
import models.components.Component;
import models.components.ComponentXpathSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//@ComponentXpathSelector(value = "//android.view.ViewGroup[2]/android.view.View")
@ComponentXpathSelector(value = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.View")
public class NavComponent extends Component {

  private final static By homeIconSel = AppiumBy.accessibilityId("fasf");
  private final static By webviewIconSel = AppiumBy.accessibilityId("asfdas");
  private final static By loginIconSel = AppiumBy.accessibilityId("Login");
  private final static By FormsIconSel = AppiumBy.accessibilityId("afsaf");
  private final static By swipeIconSel = AppiumBy.accessibilityId("faf");
  private final static By dragIconSel = AppiumBy.accessibilityId("fasfa");

  private static Map<Platform, By> navloginBtnLocMap = Map.of(
      Platform.ANDROID, AppiumBy.accessibilityId("Login"),
      Platform.IOS, AppiumBy.accessibilityId("try-to-have-difference-here")
  );

  public NavComponent(AppiumDriver appiumDriver, WebElement component) {
    super(appiumDriver, component);
  }

  public void clickOnLoginIcon() {
    component.findElement(loginIconSel).click();
    // TODO: Make sure we are on the Login screen. Implement below...
    // By targetScreenEleLocator = new ElementHandler(appiumDriver).getElementLocatorFrom(navloginBtnLocMap);
    // wait.until()....
  }

}

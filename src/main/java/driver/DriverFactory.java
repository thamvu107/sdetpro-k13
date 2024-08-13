package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

    private AppiumDriver appiumDriver;

    public static AppiumDriver getDriver(Platform platform) {
        AppiumDriver appiumDriver = null;
        // DesiredCaps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(AndroidCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(AndroidCapabilityType.AUTOMATION_NAME_OPTION, "uiautomator2");
        desiredCapabilities.setCapability(AndroidCapabilityType.UDID_OPTION, "emulator-5554");
        desiredCapabilities.setCapability(AndroidCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
        desiredCapabilities.setCapability(AndroidCapabilityType.APP_ACTIVITY_OPTION,
                "com.wdiodemoapp.MainActivity");
        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (appiumServer == null) {
            throw new RuntimeException("Can't construct the appium server URL");
        }

        switch (platform) {
            case ANDROID:
                appiumDriver = new AndroidDriver(appiumServer, desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver(appiumServer, desiredCapabilities);
                break;
        }

        // Need one more thing here that we will talk in next lesson
        // Global wait time applied for the WHOLE driver session - Implicit wait
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2L));
        return appiumDriver;
    }

    public AppiumDriver getDriver(Platform platform, String systemPort, String udid,
                                  String platformVersion) {


        if (appiumDriver == null) {
            URL appiumServer = null;
            // Getting remote env var
            String remoteInfoViaEnvVar = System.getenv("remote");
            String remoteInfoViaCmdLineArgs = System.getProperty("remote");
            String isRemote = remoteInfoViaEnvVar != null ? remoteInfoViaEnvVar : remoteInfoViaCmdLineArgs;
            if (isRemote == null) {
                throw new RuntimeException("Please provide env variable [remote]!");
            }

            // Specify the target server
            String targetServer = "http://localhost:4723";
            if (isRemote.equalsIgnoreCase("true")) {
                String hubIpAddress = System.getenv("hub");
                System.out.println("Getting hub from .getEnv");
                if (hubIpAddress == null) {
                    System.out.println("Getting hub from .getProperty");
                    hubIpAddress = System.getProperty("hub");
                }
                if (hubIpAddress == null) {
                    throw new RuntimeException("Please provide env variable [hub]!");
                }
                targetServer = "http://" + hubIpAddress + ":4444/wd/hub";
                System.out.println("Hub URL: " + targetServer);
            }

            try {
                appiumServer = new URL(targetServer);
            } catch (Exception e) {
                System.out.println("Thread.currentThread().getName(): " +Thread.currentThread().getName());
                e.printStackTrace();
            }
            if (appiumServer == null) {
                throw new RuntimeException("Can't construct the appium server URL");
            }

            DesiredCapabilities desiredCapabilities;
            switch (platform) {
                case ANDROID:
                    desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability(AndroidCapabilityType.AUTOMATION_NAME_OPTION,
                            "uiautomator2");
                    desiredCapabilities.setCapability(AndroidCapabilityType.PLATFORM_NAME, "Android");
                    desiredCapabilities.setCapability(AndroidCapabilityType.SYSTEM_PORT, systemPort);
                    desiredCapabilities.setCapability(AndroidCapabilityType.UDID_OPTION, udid);
                    desiredCapabilities.setCapability(AndroidCapabilityType.APP_PACKAGE_OPTION,
                            "com.wdiodemoapp");
                    desiredCapabilities.setCapability(AndroidCapabilityType.APP_ACTIVITY_OPTION,
                            "com.wdiodemoapp.MainActivity");
                    desiredCapabilities.setCapability("autoGrantPermissions", "true");
                    desiredCapabilities.setCapability("autoAcceptAlerts", "true");
//                    desiredCapabilities.setCapability("--session-override", true);
                    try {
                        appiumDriver = new AndroidDriver(appiumServer, desiredCapabilities);
                    } catch (Exception e) {
                        throw new RuntimeException("Thread.currentThread().getName()" +Thread.currentThread().getName() + " Can't create new driver: " + e.getMessage());
                    }

                    break;
                case IOS:
                    desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability(IOSCapabilityType.AUTOMATION_NAME_OPTION, "XCUITest");
                    desiredCapabilities.setCapability(IOSCapabilityType.DEVICE_NAME_OPTION, udid);
                    desiredCapabilities.setCapability(IOSCapabilityType.PLATFORM_VERSION_OPTION,
                            platformVersion);
                    desiredCapabilities.setCapability(IOSCapabilityType.BUNDLE_ID,
                            "org.reactjs.native.example.wdiodemoapp");
                    desiredCapabilities.setCapability(IOSCapabilityType.WDA_LOCAL_PORT_OPTION, systemPort);
                    appiumDriver = new IOSDriver(appiumServer, desiredCapabilities);
                    System.out.println("Create new driver: " + appiumDriver);
                    break;
            }

            // Need one more thing here that we will talk in next lesson
            // Global wait time applied for the WHOLE driver session - Implicit wait
            appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        }
        return appiumDriver;
    }

    public void quitAppiumDriver() {
        if (appiumDriver != null) {
            System.out.println("Device: " + appiumDriver.getCapabilities().getCapability("udid") + "quit appium driver: " + appiumDriver);
            appiumDriver.quit();
            appiumDriver = null;
        }
    }

}

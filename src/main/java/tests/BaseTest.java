package tests;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import models.components.Component;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    private static final List<DriverFactory> driverThreadList = Collections.synchronizedList(
            new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    protected String udid;
    private String systemPort;
    private String platformName;
    private String platformVersion;
    protected  AppiumDriver appiumDriver;


    protected AppiumDriver getDriver() {
        AppiumDriver appiumDriver = driverThread.get()
                .getDriver(Platform.valueOf(platformName), systemPort, udid, platformVersion);

        return appiumDriver;
    }

    @BeforeTest
    @Parameters({"systemPort", "udid", "platformName", "platformVersion"})
    public void initAppiumDriverSession(String systemPort, String udid, String platformName,
                                        @Optional("platformVersion") String platformVersion) {
        System.out.println("Device: " + udid + ": Before Test--------------");

        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.systemPort = systemPort;
        this.udid = udid;

        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverFactory = new DriverFactory();
            driverThreadList.add(driverFactory);
            //System.out.println("driverThreadList add driverThread: " + driverFactory.getDriver(Platform.valueOf(platformName), systemPort, udid, platformVersion));

            return driverFactory;
        });
        appiumDriver = getDriver();
//        printDriverThreadList();
        //System.out.println("Device: " + udid + ": Before Test appiumDriver: " + getDriver());
    }

//    public void printDriverThreadList() {
//        synchronized (driverThreadList) {
//            System.out.println("driverThreadList contains " + driverThreadList.size()+ " items:");
//            if (!driverThreadList.isEmpty()) {
//                for (DriverFactory driverFactory : driverThreadList) {
//                    System.out.println("DriverThreadList item: " + driverFactory.getDriver(Platform.valueOf(platformName), systemPort, udid, platformVersion));
//                }
//            }
//        }
//    }

    @BeforeClass
    @Parameters({"systemPort", "udid", "platformName", "platformVersion"})
    public void getTestParams(String systemPort, String udid, String platformName,
                              @Optional("platformVersion") String platformVersion) {


        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.systemPort = systemPort;
        this.udid = udid;
        appiumDriver = getDriver();

//        printDriverThreadList();

        //System.out.println("Device: " + udid + ": Before Class-------- " + getClass().getSimpleName() + "appiumDriver: " + getDriver());
        System.out.println("Device: " + udid + ": Before Class-------- " + getClass().getSimpleName() );

    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession() {

        driverThread.get().quitAppiumDriver();

    }


    @AfterMethod(description = "Capture screenshot when test is failed")
    public void captureScreenshot(ITestResult results) {
        boolean testIsFailed = results.getStatus() == ITestResult.FAILURE;
        if (testIsFailed) {
            String randomScreenshotName = generateScreenshotNameBasedOnTime(results);
            // Capture screenshot and attach to the report
            File screenshotBase64Data = getDriver().getScreenshotAs(OutputType.FILE);
            try {
                String screenshotLocation =
                        System.getProperty("user.dir") + "/screenshots/" + randomScreenshotName;
                FileUtils.copyFile(screenshotBase64Data,
                        new File(screenshotLocation));
                Path screenshotContentPath = Paths.get(screenshotLocation);
                InputStream inputStream = Files.newInputStream(screenshotContentPath);
                Allure.addAttachment(results.getName(), inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String generateScreenshotNameBasedOnTime(ITestResult results) {
        // 1. GEt the test method name
        String methodName = results.getName();

        // 2. Get current time | yyyy-mm-dd-hr-m-s
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hr = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        String takenTime = year + "-" + month + "-" + day + "-" + hr + "-" + min + "-" + sec;
        return methodName + "-" + takenTime + ".png";
    }

}

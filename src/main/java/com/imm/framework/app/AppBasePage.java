package com.imm.framework.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description: 移动端android基础封装
 * @Date: 2020/11/19
 */
public class AppBasePage {
    private AppiumDriver<MobileElement> driver;

    public AppBasePage() {
    }

    public AppBasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public AppBasePage(String udid, String packageName, String activityName) {
        startApp(udid, packageName, activityName);
    }

    public void startApp(String udid, String packageName, String activityName) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("udid", udid); //设备唯一标识符
//        desiredCapabilities.setCapability("deviceName", "phone");
//        desiredCapabilities.setCapability("avd", "Pixel_2_API_29"); //启动模拟器
        desiredCapabilities.setCapability("appPackage", packageName);
        desiredCapabilities.setCapability("appActivity", activityName);
        desiredCapabilities.setCapability("unicodeKeyboard", "true");
        desiredCapabilities.setCapability("resetKeyboard", "true");
        desiredCapabilities.setCapability("dontStopAppOnReset", "true"); //不重启app
        desiredCapabilities.setCapability("skipLogcatCapture", "true"); //跳过日志记录
        //高危操作，设置错误会清空聊天记录
        desiredCapabilities.setCapability("noReset", "true");
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public void click(By by) {
        driver.findElement(by).click();
    }

    public void click(String text) {
        findElement(By.xpath("//*[@text='" + text + "']")).click();
    }

    public void sendKeys(By by, String content) {
        findElement(by).sendKeys(content);
    }

    public void sendKeys(String text, String content) {
        findElement(By.xpath("//*[@text='" + text + "']")).sendKeys(content);
    }

    public void clear(By by) {
        findElement(by).clear();
    }

    public void clearAndType(By by, String content) {
        findElement(by).clear();
        findElement(by).sendKeys(content);
    }

    public void quit() {
        driver.quit();
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }
}

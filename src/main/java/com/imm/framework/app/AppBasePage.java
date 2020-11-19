package com.imm.framework.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description: 移动端android基础封装
 * @Date: 2020/11/19
 */
public class AppBasePage {
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    public AppBasePage() {
    }

    public AppBasePage(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
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
        wait = new WebDriverWait(driver, 10);
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void click(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='" + text + "']"))).click();
    }

    public void sendKeys(By by, String content) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(content);
    }

    public void sendKeys(String text,String content) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='" + text + "']"))).sendKeys(content);
    }

    public void quit() {
        driver.quit();
    }
}

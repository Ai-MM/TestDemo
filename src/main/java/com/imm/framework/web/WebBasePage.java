package com.imm.framework.web;

import com.imm.utils.FakerUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: iMM
 * @Description: Web自动化基础封装
 * @Date: 2020/11/14
 */
public class WebBasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public WebBasePage() {
        driver = new ChromeDriver(new ChromeOptions()
//                .addArguments("--headless")
//                .addArguments("--window-size=1552,840")
        );
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //设置隐式等待，10s
        wait = new WebDriverWait(driver, 10);
    }

    public WebBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(findElement(by))).click();
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void jsClickByCss(String cssSelector) {
        ((JavascriptExecutor) driver).executeScript("$(arguments[0]).click()", cssSelector);
//        ((JavascriptExecutor) driver).executeScript("document.querySelector(arguments[0]).click()", cssSelector);
    }

    public void jsClickByXpath(String xpath) {
        //只能定位第一个元素
        ((JavascriptExecutor) driver).executeScript("document.evaluate(arguments[0],document).iterateNext().click()", xpath);
    }

    public int jsElementsSize(String cssSelector) {
        return Integer.parseInt(((JavascriptExecutor) driver).executeScript("return $(arguments[0]).length", cssSelector).toString());
    }

    public void sendKeys(By by, String content) {
        findElement(by).sendKeys(content);
    }

    public String getText(By by) {
        return findElement(by).getText();
    }

    public void clear(By by) {
        findElement(by).clear();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void quit() {
        driver.close();
        driver.quit();
    }

    public void uploadFile(By by, String fileName) {
        //将格式设置为UTF-8，避免文件名含有中文时乱码（注：文件必须放在项目的resources目录下）
        String fileNameDecode = URLDecoder.decode(this.getClass().getResource("/" + fileName).getPath(), StandardCharsets.UTF_8).substring(1);
        findElement(by).sendKeys(fileNameDecode);
    }

    public void saveScreenshot() {
        try {
            FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
                    new File(new File("").getAbsolutePath() +
                            "/src/test/resources/screenshot/" + FakerUtil.currentTime() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }
}

package com.imm.framework.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description: Web自动化基础封装
 * @Date: 2020/11/14
 */
public class WebBasePage {
    private WebDriver driver;

    public WebBasePage() {
        // 启动指定浏览器，使用命令 [mvn test -Dtest=类名 -Dbrowser=浏览器名] 执行测试
//        if ("chrome".equals(System.getProperty("browser").toLowerCase())) {
//            driver = new ChromeDriver();
//        } else if ("firefox".equals(System.getProperty("browser").toLowerCase())) {
//            driver = new FirefoxDriver();
//        } else if ("edge".equals(System.getProperty("browser").toLowerCase())) {
//            driver = new EdgeDriver();
//        }
        driver = new ChromeDriver(new ChromeOptions().setExperimentalOption("debuggerAddress", "127.0.0.1:9999"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //设置隐式等待，10s
    }

    public WebBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By by) {
        driver.findElement(by).click();
    }

    public void click(By by, int index) {
        (driver.findElements(by).get(index)).click();
    }

    public void jsClickByCss(String cssSelector) {
        ((JavascriptExecutor) driver).executeScript("$(arguments[0]).click()", cssSelector);
//        ((JavascriptExecutor) driver).executeScript("document.querySelector(arguments[0]).click()", cssSelector);
    }

    public void jsClickByXpath(String xpath) {
//        ((JavascriptExecutor) driver).executeScript("$x(arguments[0]).click()", xpath); //这种写法不可用
        //只能定位第一个元素
        ((JavascriptExecutor) driver).executeScript("document.evaluate(arguments[0],document).iterateNext().click()", xpath);
    }

    public int jsElementSize(String cssSelector) {
        return Integer.parseInt(((JavascriptExecutor) driver).executeScript("return $(arguments[0]).length", cssSelector).toString());
//        return Integer.parseInt(((JavascriptExecutor) driver).executeScript("return document.querySelector(arguments[0]).length", cssSelector).toString());
    }

    public void sendKeys(By by, String content) {
        driver.findElement(by).click();
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(content);
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public void clear(By by) {
        driver.findElement(by).clear();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void quit() {
        driver.quit();
    }

    public void uploadFile(By by, String fileName) {
        //将格式设置为UTF-8，避免文件名含有中文时乱码
        String fileNameDecode = URLDecoder.decode(this.getClass().getResource("/" + fileName).getPath(), StandardCharsets.UTF_8).substring(1);
        driver.findElement(by).sendKeys(fileNameDecode);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}

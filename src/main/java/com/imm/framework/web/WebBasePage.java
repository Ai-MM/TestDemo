package com.imm.framework.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description: Web自动化基础封装
 * @Date: 2020/11/14
 */
public class WebBasePage {
    public WebDriver driver;
    public WebDriverWait wait;

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
        wait = new WebDriverWait(driver, 10); //设置显示等待，10s
    }

    public WebBasePage(WebDriver driver,WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click(); //显示等待某个元素可被点击，然后点击
    }

    public void click(By by, int index) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(by).get(index))).click();
    }

    public void sendKeys(By by, String content) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click(); //显示等待某个元素可被点击，然后点击
        driver.findElement(by).clear(); //清空输入框
        driver.findElement(by).sendKeys(content); //输入内容
    }

    public String getText(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText(); //显示等待某个元素可见，然后获取文本
    }

    public void clear(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).clear(); //显示等待某个元素可见，然后清空
    }

    public void refresh() {
        driver.navigate().refresh(); //刷新页面
    }

    public void quit() {
        driver.quit(); //退出driver
    }

    public void uploadFile(By by, String fileName) {
        //将格式设置为UTF-8，避免文件名含有中文时乱码
        String fileNameDecode = URLDecoder.decode(this.getClass().getResource("/" + fileName).getPath(), StandardCharsets.UTF_8).substring(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(fileNameDecode); //显示等待某个元素可见，然后输入
    }
}

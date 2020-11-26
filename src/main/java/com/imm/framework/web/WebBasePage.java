package com.imm.framework.web;

import com.imm.utils.FakerUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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
//                .addArguments("--user-data-dir=C:/Users/iMM/AppData/Local/Google/Chrome/User Data")
                .setExperimentalOption("debuggerAddress", "127.0.0.1:9999")
        );
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //设置隐式等待，10s
//        wait = new WebDriverWait(driver, 10);
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
        try {
            wait.until(ExpectedConditions.elementToBeClickable(findElement(by))).click();
        } catch (Exception e) {
            e.printStackTrace();
            closeAlter();
        }
    }

    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            e.printStackTrace();
            closeAlter();
        }
    }

    public void sendKeys(By by, String content) {
        try {
            findElement(by).sendKeys(content);
        } catch (Exception e) {
            e.printStackTrace();
            closeAlter();
        }
    }

    public void clearAndType(By by,String content) {
        findElement(by).clear();
        findElement(by).sendKeys(content);
    }

    public void moveToElement(By by) {
        new Actions(driver).moveToElement(findElement(by));
    }

    public String getText(By by) {
        try {
            return findElement(by).getText();
        } catch (Exception e) {
            e.printStackTrace();
            closeAlter();
            return "getText error";
        }
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
        driver.quit();
    }

    public void uploadFile(By by, String fileName) {
        //将格式设置为UTF-8，避免文件名含有中文时乱码（注: 文件必须放在项目的resources目录下）
        String fileNameDecode = URLDecoder.decode(new File("").getAbsolutePath() + "/src/main/resources/" + fileName, StandardCharsets.UTF_8);
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

    public boolean isAlterPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeAlter() {
        while (isAlterPresent()) {
            try {
                driver.switchTo().alert().dismiss();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    driver.switchTo().alert().accept();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void explicitWait(int seconds) {
        this.wait = new WebDriverWait(getDriver(), seconds);
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

    public void jsClickByCss(String cssSelector) {
        ((JavascriptExecutor) driver).executeScript("$(arguments[0]).click()", cssSelector);
//        ((JavascriptExecutor) driver).executeScript("document.querySelector(arguments[0]).click()", cssSelector);
    }

    public void jsClickByXpath(String xpath) {
        //只能定位第一个元素
        ((JavascriptExecutor) driver).executeScript("document.evaluate(arguments[0],document).iterateNext().click()", xpath);
    }
}

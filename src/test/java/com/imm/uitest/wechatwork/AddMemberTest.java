package com.imm.uitest.wechatwork;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/14
 */
public class AddMemberTest {
    static WebDriver driver;
    static WebDriverWait wait;
    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver(new ChromeOptions().setExperimentalOption("debuggerAddress", "127.0.0.1:9999"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    void addMemberTest() {
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
        driver.findElement(By.linkText("通讯录")).click();
//        driver.findElement(By.linkText("添加成员")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"ww_operationBar\"]/a[@class=\"qui_btn ww_btn js_add_member\"]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"ww_operationBar\"]/a[@class=\"qui_btn ww_btn js_add_member\"]"))).click();
//        driver.findElement(By.xpath("//div[@class=\"ww_operationBar\"]/a[@class=\"qui_btn ww_btn js_add_member\"]")).click();
        driver.findElement(By.name("username")).sendKeys("test");
        driver.findElement(By.name("acctid")).sendKeys("test");
        driver.findElement(By.name("mobile")).sendKeys("18883857944");
        driver.findElement(By.name("sendInvite")).click();
        driver.findElement(By.linkText("保存")).click();

        driver.findElement(By.id("memberSearchInput")).sendKeys("test");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"qui_btn ww_btn js_del_member\"]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class=\"qui_btn ww_btn js_del_member\"]"))).click();
    }

    @AfterAll
    static void afterAll() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}

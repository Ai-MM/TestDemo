package com.imm.uitest.wechatwork;

import com.imm.uitest.BaseTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/14
 */
public class AddMemberTest {
    static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver(new ChromeOptions().setExperimentalOption("debuggerAddress", "127.0.0.1:9999"));
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
    }

    @Test
    void addMemberTest() {
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
        driver.findElement(By.linkText("通讯录")).click();
        driver.findElement(By.linkText("添加成员")).click();

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

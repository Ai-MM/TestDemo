package com.imm;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020-11-11 20:30
 */
public class WebTest {
    static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
//        System.setProperty("webdriver.edge.driver", "E:\\Files\\WebDriver\\msedgedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void webTest() {
        driver.get("https://ceshiren.com/");
        driver.findElement(By.cssSelector("#search-button > svg")).click();
    }

    @AfterAll
    static void afterAll() {
        try {
            Thread.sleep(2000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}

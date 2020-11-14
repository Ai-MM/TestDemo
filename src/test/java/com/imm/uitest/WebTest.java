package com.imm.uitest;

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
 * @Date: 2020-11-11
 */
public class WebTest extends BaseTest2{

    @Test
    void webTest() {
        driver.get("https://ceshiren.com/");
        driver.findElement(By.cssSelector("#search-button > svg")).click();
    }
}

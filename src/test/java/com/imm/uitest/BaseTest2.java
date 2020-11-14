package com.imm.uitest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest2 {
    static WebDriver driver;

    @BeforeAll
    static void setUp() {
        // 启动指定浏览器，使用命令 [mvn test -Dtest=类名 -Dbrowser=浏览器名] 执行测试
        if ("chrome".equals(System.getProperty("browser").toLowerCase())) {
            System.setProperty("webdriver.chrome.driver", "D:/Files/chromedriver.exe");
            driver = new ChromeDriver();
        } else if ("firefox".equals(System.getProperty("browser").toLowerCase())) {
            System.setProperty("webdriver.gecko.driver", "D:/Files/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if ("edge".equals(System.getProperty("browser").toLowerCase())) {
            System.setProperty("webdriver.edge.driver", "D:/Files/msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
    static void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}

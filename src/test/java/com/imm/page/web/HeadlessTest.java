package com.imm.page.web;

import com.imm.utils.LoginUtil;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/22
 */
public class HeadlessTest {
    @Test
    void headless() {
        // 启动指定浏览器，使用命令 [mvn test -Dtest=类名 -Dbrowser=浏览器名] 执行测试
//        if ("chrome".equals(System.getProperty("browser").toLowerCase())) {
//            driver = new ChromeDriver();
//        } else if ("firefox".equals(System.getProperty("browser").toLowerCase())) {
//            driver = new FirefoxDriver();
//        } else if ("edge".equals(System.getProperty("browser").toLowerCase())) {
//            driver = new EdgeDriver();
//        }
        WebDriver driver = null;
        try {
            driver = new ChromeDriver(new ChromeOptions()
//                .addArguments("--headless")
//                .addArguments("--window-size=1552,840")
                    .addArguments("--start-maximized")
//                .addArguments("--user-data-dir=C:/Users/iMM/AppData/Local/Google/Chrome/User Data")
//                .setExperimentalOption("debuggerAddress", "127.0.0.1:9999")
            );
            driver.get("https://work.weixin.qq.com/wework_admin/frame#contacts");
            LoginUtil.loadCookies(driver);
            System.out.println(driver.manage().window().getSize());
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //设置隐式等待，10s
            driver.get("https://work.weixin.qq.com/wework_admin/frame#contacts");
            Thread.sleep(3000);
            System.out.println(driver.manage().getCookies());
            //截屏
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("test.png"));
            System.out.println(driver.getCurrentUrl());
        } catch (IOException | WebDriverException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
            driver.quit();
        }
    }
}

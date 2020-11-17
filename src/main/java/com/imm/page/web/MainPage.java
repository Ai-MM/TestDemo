package com.imm.page.web;

import com.imm.framework.web.WebBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description: 企业微信-主页面
 * @Date: 2020/11/14
 */
public class MainPage extends WebBasePage {
    public MainPage() {
        driver = new ChromeDriver(new ChromeOptions().setExperimentalOption("debuggerAddress", "127.0.0.1:9999"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index"); //进入首页
    }

    public ContactPage toContactPage() {
        click(By.id("menu_contacts")); //点击通讯录
        return new ContactPage(driver);
    }
}

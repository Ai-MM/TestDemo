package com.imm.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/29
 */
public class MainPage extends BasePage {

    public MainPage() {
        setDriver(new ChromeDriver());
        getDriver().get("https://ceshiren.com");
    }

    public void search() {
        click(By.id("search-button"));
        sendKeys(By.id("search-term"), "test");
    }

    public SearchPage toSearchPage() {
        return new SearchPage(getDriver());
    }
}

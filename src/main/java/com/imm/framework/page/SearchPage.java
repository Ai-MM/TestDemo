package com.imm.framework.page;

import com.imm.framework.web.WebBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/29
 */
public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void search() {
        click(By.id("search-button"));
        sendKeys(By.id("search-term"),"test");
    }
}

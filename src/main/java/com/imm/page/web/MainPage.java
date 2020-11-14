package com.imm.page.web;

import com.imm.framework.web.WebBasePage;
import org.openqa.selenium.By;

/**
 * @Author: iMM
 * @Description: 企业微信-主页面
 * @Date: 2020/11/14
 */
public class MainPage extends WebBasePage {
    public MainPage() {
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
    }

    public ContactPage toContactPage() {
        click(By.id("menu_contacts"));
        return new ContactPage();
    }
}

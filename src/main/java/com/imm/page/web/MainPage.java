package com.imm.page.web;

import com.imm.framework.web.WebBasePage;
import com.imm.utils.LoginUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

/**
 * @Author: iMM
 * @Description: 企业微信-主页面
 * @Date: 2020/11/14
 */
public class MainPage extends WebBasePage {
    //    @FindBy(css = "[id='menu_contacts']")
//    private WebElement contactButton; //联系人按钮
    private final By contactButton = By.cssSelector("[id=\"menu_contacts\"]"); //联系人按钮

    public MainPage() {
        String url = "https://work.weixin.qq.com/wework_admin/frame#index";
        getDriver().get(url); //进入首页
        try {
            LoginUtil.loadCookies(getDriver());
        } catch (IOException e) {
            e.printStackTrace();
        }
        getDriver().get(url);
        maximize();
//        PageFactory.initElements(getDriver(), this);
    }

    public ContactPage toContactPage() {
//        contactButton.click();
        click(contactButton); //点击通讯录
        return new ContactPage(getDriver());
    }
}

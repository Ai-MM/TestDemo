package com.imm.page.app;

import com.imm.framework.app.AppBasePage;

/**
 * @Author: iMM
 * @Description: 企业微信android端主页（消息）
 * @Date: 2020/11/19
 */
public class MainPage extends AppBasePage {
    public MainPage() {
        super("127.0.0.1:7555", "com.tencent.wework", ".launch.WwMainActivity");
    }

    public ContactPage toContactPage() {
        click("通讯录");
        return new ContactPage(driver, wait);
    }
}

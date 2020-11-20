package com.imm.page.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @Author: iMM
 * @Description: 企业微信-移动端 联系人页面
 * @Date: 2020/11/19
 */
public class ContactPageTest {
    private static ContactPage contactPage;

    @BeforeAll
    static void beforeAll() {
        MainPage mainPage = new MainPage();
        contactPage = mainPage.toContactPage();
        System.out.println(mainPage.getDriver());
        System.out.println(contactPage.getDriver());
    }

    @Test
    void addMember() {
        contactPage.addMember("工具人", "12345678910", "男").back();
        contactPage.deleteMember("工具人");
    }

//    @Test
    void deleteMember() {
        contactPage.deleteMember("工具人");
    }

    @AfterAll
    static void afterAll() {
        contactPage.quit();
    }
}

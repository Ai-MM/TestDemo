package com.imm.page.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/19
 */
public class ContactPageTest {
    static ContactPage contactPage;

    @BeforeAll
    static void beforeAll() {
        MainPage mainPage = new MainPage();
        contactPage = mainPage.toContactPage();
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
//        contactPage.quit();
    }
}

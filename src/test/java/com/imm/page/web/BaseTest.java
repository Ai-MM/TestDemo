package com.imm.page.web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/17
 */
public class BaseTest {
    private static MainPage mainPage;

    @BeforeAll
    static void beforeAll() {
        mainPage = new MainPage();
    }

    @AfterAll
    static void afterAll() {
        mainPage.quit();
    }

    public static MainPage getMainPage() {
        return mainPage;
    }

    public static void setMainPage(MainPage mainPage) {
        BaseTest.mainPage = mainPage;
    }
}

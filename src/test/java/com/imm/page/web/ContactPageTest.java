package com.imm.page.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author: iMM
 * @Description: 企业微信-联系人页面测试
 * @Date: 2020/11/15
 */
class ContactPageTest {
    @Test
    void addMemberTest() {
        ContactPage contactPage = new MainPage()
                .toContactPage()
                .addMember("test", "test", "18883857944")
                .search("test");
        String acctId = contactPage.getAcctId();
        contactPage.deleteMember();
        assertTrue(acctId.contains("test"));
    }
}
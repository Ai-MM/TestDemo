package com.imm.page.web;

import com.imm.framework.web.WebBasePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: iMM
 * @Description: 企业微信-联系人页面测试
 * @Date: 2020/11/15
 */
class ContactPageTest extends BaseTest {
    ContactPage contactPage = mainPage.toContactPage();

    @Test
    void addMemberTest() {
        contactPage.addMember("test", "test", "18883857944").search("test");
        String acctId = contactPage.getAcctId();
        contactPage.deleteMember();
        assertTrue(acctId.contains("test"));
    }

    @Test
    void addDepartmentTest() {
        String departmentName = contactPage.addDepartment("测试部门", "MZ").search("测试部门").getDepartmentName();
        assertEquals(departmentName, "测试部门");
    }
}
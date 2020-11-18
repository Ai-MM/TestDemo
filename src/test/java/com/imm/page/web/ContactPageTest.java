package com.imm.page.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author: iMM
 * @Description: 企业微信-联系人页面测试
 * @Date: 2020/11/15
 */
class ContactPageTest extends BaseTest {
    ContactPage contactPage = mainPage.toContactPage();

    @ParameterizedTest
    @CsvSource({
            "test1, test1, '11111111111'",
            "test2, test2, '11111111112'",
            "test3, test3, '11111111113'"
    })
    void addMemberTest(String userName, String acctId, String mobile) {
        contactPage.addMember(userName, acctId, mobile).search(userName);
        String actualAcctId = contactPage.getAcctId();
        contactPage.deleteMember();
        assertTrue(actualAcctId.contains(acctId));
    }

    @Test
    void addDepartmentTest() {
        String departmentName = contactPage.addDepartment("测试部门", "MZ").search("测试部门").getDepartmentName();
        System.out.println(departmentName);
        assertEquals("测试部门", departmentName);
        contactPage.clearSearch().deleteDepartment("测试部门");
    }
}
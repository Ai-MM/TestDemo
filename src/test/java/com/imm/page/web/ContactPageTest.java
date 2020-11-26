package com.imm.page.web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author: iMM
 * @Description: 企业微信-联系人页面测试
 * @Date: 2020/11/15
 */
//@Timeout(10)
class ContactPageTest extends BaseTest {
    private final ContactPage contactPage = getMainPage().toContactPage();

    @BeforeEach
    void beforeEach() {
        contactPage.deleteAllMember();
    }

    @ParameterizedTest
    @CsvSource({
            "test1, test1, 11111111111",
            "test2, test2, 11111111112",
            "test3, test3, 11111111113",
            "test4, test4, 11111111114",
            "test5, test5, 11111111115",
            "test6, test6, 11111111116"
    })
    void addMember(String userName, String acctId, String mobile) {
        String actualAcctId = contactPage.addMember(userName, acctId, mobile).search(mobile).getAcctId();
        contactPage.clearSearch();
        assertTrue(actualAcctId.contains(acctId));
    }

    @ParameterizedTest
    @CsvSource({
            "updateTest, 66666666666"
    })
    void updateMember(String username, String mobile) {
        contactPage.addMember("addMember", "addMember", "10000000000");
        String acctId = contactPage.search(mobile).updateMember("addMember", username, mobile).search(mobile).getAcctId();
        contactPage.clearSearch();
        assertTrue(acctId.contains("addMember"));
    }

    @Test
    void addDepartment() {
        String departmentName = contactPage.addDepartment("测试部门", "MZ").search("测试部门").getDepartmentName();
        assertEquals("测试部门", departmentName);
        contactPage.clearSearch().deleteDepartment("测试部门");
    }

    //    @Test
    void updateDepartment() {
        String departmentName = contactPage.updateDepartment("测试部门", "编辑部门测试").search("编辑部门测试").getDepartmentName();
        assertEquals("编辑部门测试", departmentName);
    }
}

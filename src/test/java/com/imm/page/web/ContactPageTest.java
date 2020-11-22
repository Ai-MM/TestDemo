package com.imm.page.web;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author: iMM
 * @Description: 企业微信-联系人页面测试
 * @Date: 2020/11/15
 */
class ContactPageTest extends BaseTest {
    private final ContactPage contactPage = getMainPage().toContactPage();

    @ParameterizedTest
    @CsvSource({
            "test1, test1, 11111111111",
            "test2, test2, 11111111112",
            "test3, test3, 11111111113",
            "test4, test4, 11111111114",
            "test5, test5, 11111111115",
            "test6, test6, 11111111116"
    })
    void addMemberTest(String userName, String acctId, String mobile) {
        contactPage.addMember(userName, acctId, mobile).search(userName);
        String actualAcctId = contactPage.getAcctId();
        System.out.println(actualAcctId);
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

//    @Test
    void test() throws IOException {
        File screenshot = ((TakesScreenshot) contactPage.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("test.png"));
    }
}

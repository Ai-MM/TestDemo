package com.imm.framework;

import com.imm.framework.web.TestCase;
import com.imm.framework.web.UITestCase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/28
 */
public class Demo1Test {
    @ParameterizedTest
    @MethodSource
    void search(TestCase testCase) {
//        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
//        driver.get("https://ceshiren.com/");
//        driver.findElement(By.id("search-button")).click();
//        driver.findElement(By.id("search-term")).sendKeys(content);
        testCase.runCase();
    }

//    @AfterAll
//    static void afterAll() {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.quit();
//    }

    static ArrayList<TestCase> search() throws IOException {
//        System.out.println(Demo1Test.class.getResource("/framework/ui_testcase.yaml"));
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("framework/ui_testcase.yaml"));
//        return UITestCase.loadTestCase("framework/ui_testcase.yaml").generateCases();
        return UITestCase.loadTestCase("framework/po_testcase.yaml").generateCases();
    }
}

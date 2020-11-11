package com.imm;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020-11-11 19:35
 */
public class TestNGTest {
    @Test
    void addTest() {
        SoftAssert assertion = new SoftAssert();
        assertion.assertEquals(Calculator.add(2, 2), 3, "message--->");
        assertion.assertEquals(Calculator.divide(1, 1), 2, "message--->");
        assertion.assertAll();
    }
}

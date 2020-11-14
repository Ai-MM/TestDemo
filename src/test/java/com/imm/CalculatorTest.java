package com.imm;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Allure Epic")
@Feature("Allure Feature")
public class CalculatorTest {
    @Test
    void addTest() {
        assertEquals(3, Calculator.add(1, 2), "message");
        assertAll("assertAll",
                () -> assertEquals(4, Calculator.add(2, 2)),
                () -> assertEquals(5, Calculator.add(3, 3)),
                () -> assertEquals(6, Calculator.add(4, 3)));
    }

    @Test
    void subtractTest() {
        assertEquals(-1, Calculator.subtract(1, 2));
    }

    @Test
    void multiplyTest() {
        assertEquals(2, Calculator.multiply(1, 2));
    }

    @Description("Allure Description")
    @Story("Allure Store")
    @DisplayName("Junit DisplayName")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("https://www.baidu.com")
    @Link(name = "Allure Link", type = "Link", url = "https://www.baidu.com")
    @Test
    void divideTest() {
        assertEquals(0, Calculator.divide(1, 2));
    }

//    @Description("Allure Description")
//    @Story("Allure Store")
//    @DisplayName("Junit DisplayName")
//    @Severity(SeverityLevel.BLOCKER)
//    @Issue("https://www.baidu.com")
//    @Link(name = "Allure Link", type = "Link", url = "https://www.baidu.com")
    @RepeatedTest(5)
//    @Execution(ExecutionMode.SAME_THREAD)
    void countTest() {
        int count = Calculator.count(1);
        System.out.println(Thread.currentThread().getId() + " ---> " + count + " ---> " + System.currentTimeMillis());
//        assertEquals(1, count);
    }

//    @BeforeEach
//    void beforeEach() {
//        Calculator.reset();
//        System.out.println("beforeEach: reset");
//    }
}

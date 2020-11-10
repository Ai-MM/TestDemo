package com.imm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void divideTest() {
        assertEquals(0, Calculator.divide(1, 2));
    }

//    @BeforeEach
//    void beforeEach() {
//        Calculator.reset();
//        System.out.println("beforeEach");
//    }

    @RepeatedTest(10)
//    @Execution(ExecutionMode.SAME_THREAD)
    void countTest() {
//        Calculator.count(2);
//        Calculator.count(2);
        int count = Calculator.synCount2(1);
        System.out.println(Thread.currentThread().getId() + "--->" + count + "--->" + System.currentTimeMillis());
//        assertEquals(6, count);
    }
}

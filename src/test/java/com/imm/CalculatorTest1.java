package com.imm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest1 {
    @Test
    void addTest() {
        assertEquals(3, Calculator.add(1, 2));
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

    @Test
    void countTest() {
        Calculator.count(2);
        Calculator.count(2);
        int count = Calculator.count(2);
        assertEquals(6, count);
    }
}

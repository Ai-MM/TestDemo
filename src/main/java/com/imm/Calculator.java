package com.imm;

public class Calculator {
    public static int result;
    public static int count;

    public static int add(int x, int y) {
        return result = x + y;
    }

    public static int subtract(int x, int y) {
        return result = x - y;
    }

    public static int multiply(int x, int y) {
        return result = x * y;
    }

    public static int divide(int x, int y) {
        return result = x / y;
    }

    public static int count(int x) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count += x;
    }

    public static void reset() {
        count = 0;
    }
}

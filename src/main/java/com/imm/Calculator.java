package com.imm;

public class Calculator {
    public static int result;

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
        int i = result;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = i + x;
        return result;
    }

    public synchronized static int synCount(int x) {
        int i = result;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = i + x;
        return result;
    }

    public static int count2(int x) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result += x;
    }

    public synchronized static int synCount2(int x) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result += x;
    }

    public static void reset() {
        result = 0;
    }
}

package com.imm.utils;

import java.util.Random;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/12
 */
public class FakerUtil {

    public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String threadTimeStamp() {
        return Thread.currentThread().getId() + "T" + System.currentTimeMillis();
    }

    public static String phoneNumber() {
        Random random = new Random();
        return "188" +
                random.nextInt(10) +
                String.valueOf(System.currentTimeMillis()).substring(7) +
                random.nextInt(10);
    }

    public static void main(String[] args) {
        System.out.println("timeStamp: " + timeStamp());
        System.out.println("threadTimeStamp: " + threadTimeStamp());
        System.out.println("phoneNumber: " + phoneNumber());
    }
}

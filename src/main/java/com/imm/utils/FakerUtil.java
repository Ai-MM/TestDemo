package com.imm.utils;

import java.util.Random;

/**
 * @Author: iMM
 * @Description: 13位时间戳、线程Id+T+时间戳、11位手机号
 * @Date: 2020/11/12
 */
public class FakerUtil {

    //13位时间戳
    public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    //线程Id+T+时间戳
    public static String threadTimeStamp() {
        return Thread.currentThread().getId() + "T" + System.currentTimeMillis();
    }

    //11位手机号
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
        System.out.println(FakerUtil.class.getResource("/junit-platform.properties").getFile().substring(1));
    }
}

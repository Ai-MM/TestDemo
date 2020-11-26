package com.imm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        return "111" +
                random.nextInt(10) +
                String.valueOf(System.currentTimeMillis()).substring(7) +
                random.nextInt(10);
    }

    public static String currentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        System.out.println("timeStamp: " + timeStamp());
        System.out.println("threadTimeStamp: " + threadTimeStamp());
        System.out.println("phoneNumber: " + phoneNumber());
        System.out.println("currentTime: "+ currentTime());

        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(FakerUtil.class.getResource("/").getPath());
        System.out.println(FakerUtil.class.getResource("").getPath());
        System.out.println(new File("").getAbsolutePath() + "\\src\\main\\resources");
        System.out.println(URLDecoder.decode(LoginUtil.class.getResource("/").getPath(), StandardCharsets.UTF_8).substring(1) + "cookies.yaml");
    }
}

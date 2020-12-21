package com.imm.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: iMM
 * @Description: 创建数据工具类
 * @Date: 2020/11/12
 */
public class FakerUtil {
    /**
     * @return 13位时间戳
     */
    public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * @return 线程id+T+时间戳
     */
    public static String threadTimeStamp() {
        return Thread.currentThread().getId() + "T" + System.currentTimeMillis();
    }

    /**
     * @return 11位手机号，111开头
     */
    public static String phoneNumber() {
        return "111" + randomInt(10, 99) + String.valueOf(System.currentTimeMillis()).substring(7);
    }

    /**
     * @return 当前格式化时间 yyyy-MM-dd HH-mm-ss
     */
    public static String currentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(System.currentTimeMillis());
    }

    /**
     * @param bound 边界值
     * @return 指定范围内的随机数 [0, bound]
     */
    public static int randomInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound + 1);
    }

    /**
     * @param min 最小值
     * @param max 最大值
     * @return 指定范围内的随机数 [min, max]
     */
    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * @param length 字符串的长度
     * @return 指定长度的随机字符串 [a-zA-Z0-9]
     */
    public static String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static void main(String[] args) {
        System.out.println("timeStamp: " + timeStamp());
        System.out.println("threadTimeStamp: " + threadTimeStamp());
        System.out.println("phoneNumber: " + phoneNumber());
        System.out.println("currentTime: " + currentTime());
        System.out.println("randomInt: " + randomInt(-1, 1));
        System.out.println("randomString: " + randomString(5));

//        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
//        System.out.println(FakerUtil.class.getResource("/").getPath());
//        System.out.println(FakerUtil.class.getResource("").getPath());
//        System.out.println(new File("").getAbsolutePath() + "\\src\\main\\resources");
//        System.out.println(URLDecoder.decode(LoginUtil.class.getResource("/").getPath(), StandardCharsets.UTF_8).substring(1) + "cookies.yaml");
    }
}

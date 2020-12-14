package com.imm.mock;

import org.junit.jupiter.api.Test;

import static spark.Spark.*;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/14
 */
public class SparkJavaTest {
    @Test
    void demo1() throws InterruptedException {
        get("/hello", (request, response) -> {
            System.out.println("Hello World! Hello SparkJava!");
            return "Hello World! Hello SparkJava!";
        });
        Thread.sleep(600000);
    }
}

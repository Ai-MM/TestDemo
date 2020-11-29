package com.imm.uitest;

import com.imm.utils.FakerUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static com.imm.utils.LoginUtil.saveCookies;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/27
 */
public class PathTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(PathTest.class.getResource(""));
        System.out.println(PathTest.class.getResource("/"));
    }

    @Test
    void test() {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(PathTest.class.getResource("../../.."));
        System.out.println(PathTest.class.getResource("/"));
    }
}

package com.imm.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/13
 */
public class LoginUtil {
    public static void saveCookies(WebDriver driver) throws IOException {
        new ObjectMapper(new YAMLFactory()).writeValue(new File("cookies.yaml"), driver.manage().getCookies());
    }

    public static void loadCookies(WebDriver driver) throws IOException {
        TypeReference<List<HashMap<String, Object>>> listTypeReference = new TypeReference<>() {
        };
        List<HashMap<String, Object>> cookies = new ObjectMapper(new YAMLFactory()).readValue(new File("cookies.yaml"), listTypeReference);
        cookies.forEach(cookie -> {
            driver.manage().addCookie(new Cookie(cookie.get("name").toString(), cookie.get("value").toString()));
        });
    }

    public static void saveLocalStorage(WebDriver driver) throws IOException {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        for (int i = 0; i < Integer.parseInt(jsDriver.executeScript("return window.localStorage.length").toString()); i++) {
            String key = jsDriver.executeScript("return window.localStorage.key(arguments[0])", i).toString();
            System.out.println(key);
            String value = jsDriver.executeScript("return window.localStorage.getItem(arguments[0])", key).toString();
            System.out.println(value);
//            new ObjectMapper(new YAMLFactory()).writeValue(new File("localStorage.yaml"), key + value);
        }
    }
}

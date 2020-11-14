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
import java.util.StringTokenizer;

/**
 * @Author: iMM
 * @Description: 保存、加载cookies、localStorage
 * @Date: 2020/11/13
 */
public class LoginUtil {
    //保存cookies到文件中
    public static void saveCookies(WebDriver driver) throws IOException {
        new ObjectMapper(new YAMLFactory()).writeValue(new File("cookies.yaml"), driver.manage().getCookies());
    }

    //从文件中读取并加载cookies到浏览器
    public static void loadCookies(WebDriver driver) throws IOException {
        TypeReference<List<HashMap<String, Object>>> listTypeReference = new TypeReference<>() {
        };
        List<HashMap<String, Object>> cookies = new ObjectMapper(new YAMLFactory()).readValue(new File("cookies.yaml"), listTypeReference);
        cookies.forEach(cookie -> {
            driver.manage().addCookie(new Cookie(cookie.get("name").toString(), cookie.get("value").toString()));
        });
    }

    //保存localStorage到文件中
    public static void saveLocalStorage(WebDriver driver) throws IOException {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("localstorage.txt"));
        for (int i = 0; i < Integer.parseInt(jsDriver.executeScript("return window.localStorage.length").toString()); i++) {
            String key = jsDriver.executeScript("return window.localStorage.key(arguments[0])", i).toString();
            String value = jsDriver.executeScript("return window.localStorage.getItem(arguments[0])", key).toString();
            bufferedWriter.write(key + ";" + value);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    //从文件中读取localStorage并加载到浏览器
    public static void loadLocalStorage(WebDriver driver) throws IOException {
        String line;
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("localstorage.txt"));
        while ((line = bufferedReader.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, ";");
            jsDriver.executeScript("window.localStorage.setItem(arguments[0],arguments[1])",
                    tokenizer.nextToken(), tokenizer.nextToken());
        }
        bufferedReader.close();
    }
}

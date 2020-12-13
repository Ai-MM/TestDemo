package com.imm.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/28
 */
public class BasePage {
    private WebDriver driver;
    static BasePage instance = null;
    public HashMap<String, BasePage> pages = new HashMap<>();

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static BasePage getInstance() {
        if (instance == null) {
            instance = new BasePage();
        }
        return instance;
    }

    public void initPage(String name, String className) {
        try {
            pages.put(name, (BasePage) Class.forName(className).getDeclaredConstructor().newInstance());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public BasePage getPage(String name) {
        return pages.get(name);
    }

    public void runMethod(String method) {
        try {
            Arrays.stream(this.getClass().getDeclaredMethods())
                    .filter(m -> m.getName().equals(method))
                    .findFirst().get()
                    .invoke(this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public void click(By by) {
        findElement(by).click();
    }

    public void sendKeys(By by, String keyWords) {
        findElement(by).sendKeys(keyWords);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}

package com.imm.framework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: iMM
 * @Description: UI测试用例模型
 * @Date: 2020/11/26
 */
public class UITestCase extends TestCase {
    private WebDriver driver;
    private WebElement element;

    /**
     * 根据steps中的步骤运行测试用例
     */
    public void runCase() {
        steps.forEach(step -> {
            if (step.containsKey("chrome")) {
                driver = new ChromeDriver();
            } else if (step.containsKey("implicitly_wait")) {
                driver.manage().timeouts().implicitlyWait(
                        Long.parseLong(getStepValue(step, "implicitly_wait").toString()), TimeUnit.SECONDS);
            } else if (step.containsKey("get")) {
                driver.get(getStepValue(step, "get").toString());
            } else if (step.containsKey("find")) {
                ArrayList<By> bys = new ArrayList<>();
                ((HashMap<String, String>) step.get("find")).forEach((key, value) -> {
                    if (key.contains("id")) {
                        bys.add(By.cssSelector("[id=" + value + "]"));
                    } else if (key.contains("name")) {
                        bys.add(By.cssSelector("[name=" + value + "]"));
                    } else if (key.contains("link_text")) {
                        bys.add(By.linkText(value));
                    } else if (key.contains("css_selector")) {
                        bys.add(By.cssSelector(value));
                    } else if (key.contains("xpath")) {
                        bys.add(By.xpath(value));
                    }
                });
                element = driver.findElement(bys.get(0));
            } else if (step.containsKey("click")) {
                element.click();
            } else if (step.containsKey("send_keys")) {
                element.sendKeys(getStepValue(step, "send_keys").toString());
            } else if (step.containsKey("sleep")) {
                try {
                    Thread.sleep(Long.parseLong(getStepValue(step, "sleep").toString()) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (step.containsKey("quit")) {
                driver.quit();
            }
        });
    }
}

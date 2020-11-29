package com.imm.framework.web;

import com.imm.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/28
 */
public class POTestCase extends TestCase {
//    private WebDriver driver;
//    private WebElement element;

    public void runCase() {
        steps.forEach(step -> {
            String key = step.keySet().iterator().next();
            if (step.containsKey("init")) {
                ArrayList<String> init = (ArrayList<String>) getStepValue(step, "init");
                BasePage.getInstance().initPage(init.get(0), init.get(1));
            } else if (key.contains(".")) {
                String[] objectMethod = key.split("\\.");
                BasePage.getInstance().getPage(objectMethod[0]).runMethod(objectMethod[1]);
            }
        });
    }
}

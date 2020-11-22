package com.imm.page.app;

import com.imm.framework.app.AppBasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/19
 */
public class ContactPage extends AppBasePage {
    public ContactPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ContactPage addMember(String userName, String mobile, String sex) {
        click("添加成员");
        click("手动输入添加");
        sendKeys("必填", userName);
        sendKeys("手机号", mobile);
        click("男");
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[@text='男']"))).click();
//        click("男");
        click("保存");
        return this;
    }

    public ContactPage deleteMember(String userName) {
        click(userName);
        click(By.xpath("//*[@resource-id='com.tencent.wework:id/hxm']"));
        click("编辑成员");
        click("删除成员");
        click("确定");
        return this;
    }

    public ContactPage back() {
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[@resource-id=\"com.tencent.wework:id/hxb\"]"))).click(); //点击返回
        return this;
    }
}

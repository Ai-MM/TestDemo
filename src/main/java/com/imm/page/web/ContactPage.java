package com.imm.page.web;

import com.imm.framework.web.WebBasePage;
import org.openqa.selenium.By;

/**
 * @Author: iMM
 * @Description: 企业微信-联系人页面
 * @Date: 2020/11/14
 */
public class ContactPage extends WebBasePage {

    public ContactPage addMember(String username, String acctid, String mobile) {
        click(By.linkText("通讯录")); //点击通讯录
        click(By.linkText("添加成员")); //点击添加成员
        sendKeys(By.cssSelector("[name=username]"), username); //输入姓名
        sendKeys(By.cssSelector("[name=acctid]"), acctid); //输入账号
        sendKeys(By.cssSelector("[name=mobile]"), mobile); //输入手机号
        click(By.cssSelector("[name=sendInvite]")); //取消发送邮件或短信
        click(By.linkText("保存")); //点击保存
        return this;
    }

    public ContactPage search(String content) {
        sendKeys(By.cssSelector("[id=memberSearchInput]"), content); //搜索姓名/账号/手机号，最好搜索账号（唯一）
        return this;
    }

    public ContactPage deleteMember() {
        click(By.cssSelector("[class='qui_btn ww_btn js_del_member']")); //点击删除
        click(By.linkText("确认")); //点击确认
        return this;
    }

    public String getAcctId() {
        return getText(By.cssSelector("[class='member_display_cover_detail'] div:nth-child(3)"));
    }
}

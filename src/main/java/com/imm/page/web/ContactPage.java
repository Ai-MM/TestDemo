package com.imm.page.web;

import com.imm.framework.web.WebBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Author: iMM
 * @Description: 企业微信-联系人页面
 * @Date: 2020/11/14
 */
public class ContactPage extends WebBasePage {

    private final By searchBox = By.cssSelector("[id=memberSearchInput]"); //搜索框

    public ContactPage(WebDriver driver) {
        super(driver);
        setWait(new WebDriverWait(driver, 10));
    }

    public ContactPage addMember(String username, String acctid, String mobile) {
        click(By.linkText("批量导入/导出"));
        click(By.linkText("批量导入/导出"));
        click(By.linkText("批量导入/导出"));
        click(By.linkText("批量导入/导出"));
        click(By.linkText("添加成员"));
        sendKeys(By.cssSelector("[name=username]"), username); //输入姓名
        sendKeys(By.cssSelector("[name=acctid]"), acctid); //输入账号
        sendKeys(By.cssSelector("[name=mobile]"), mobile); //输入手机号
        click(By.cssSelector("[name=sendInvite]")); //取消发送邮件或短信
        click(By.linkText("保存")); //点击保存
        return this;
    }

    public ContactPage deleteMember() {
        click(By.cssSelector("[class='qui_btn ww_btn js_del_member']")); //点击删除
        click(By.linkText("确认")); //点击确认
        return this;
    }

    public ContactPage addDepartment(String departmentName, String selectDepartment) {
        click(By.cssSelector("[class=member_colLeft_top_addBtn]")); //点击搜索框后的+
        click(By.cssSelector("[class=js_create_party]")); //点击添加部门
        sendKeys(By.cssSelector("[name=name]"), departmentName); //输入部门名称
        click(By.linkText("选择所属部门")); //点击选择所属部门
        click(findElements(By.linkText("MZ")).get(1)); //根据部门名称点击部门
        click(By.linkText("确定")); //点击确定
        return this;
    }

    public ContactPage deleteDepartment(String departmentName) {
        click(By.linkText(departmentName));
        click(By.xpath("//a[text()=\"" + departmentName + "\"]/span"));
        click(By.cssSelector("body > ul > li:nth-child(7) > a")); //点击删除
        click(By.linkText("确定")); //点击确定
        return this;
    }

    public ContactPage search(String content) {
        sendKeys(searchBox, content); //搜索姓名/账号/手机号，最好搜索账号（唯一）
        return this;
    }

    public ContactPage clearSearch() {
        click(By.cssSelector("[id=\"clearMemberSearchInput\"]")); //清空搜索框
        return this;
    }

    public String getAcctId() {
        return getText(By.cssSelector("[class='member_display_cover_detail'] div:nth-child(3)"));
    }

    public String getDepartmentName() {
        return getText(By.cssSelector("[class=ww_searchResult_item_Curr]"));
    }
}

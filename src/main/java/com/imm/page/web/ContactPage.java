package com.imm.page.web;

import com.imm.framework.web.WebBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @Author: iMM
 * @Description: 企业微信-联系人页面
 * @Date: 2020/11/14
 */
public class ContactPage extends WebBasePage {

    private final By searchBox = By.cssSelector("[id=memberSearchInput]"); //搜索框
    private final By importExportButton = By.linkText("批量导入/导出"); //批量导入/导出 按钮
    private final By updateButton = By.linkText("编辑"); //编辑 按钮
    //添加成员
    private final By addMemberButton = By.linkText("添加成员"); //添加成员 按钮
    private final By nameBox = By.cssSelector("[name=username]"); //姓名 字段
    private final By acctIdBox = By.cssSelector("[name=acctid]"); //账号 字段
    private final By mobileBox = By.cssSelector("[name=mobile]"); //手机号 字段
    private final By sendInviteButton = By.cssSelector("[name=sendInvite]"); //通过邮件或短信发送企业邀请 按钮
    private final By saveButton = By.linkText("保存"); //保存 按钮

    private final By deleteButton = By.linkText("删除"); //删除 按钮
    private final By confirmButton = By.linkText("确认"); //确认 按钮
    //添加部门
    private final By addButton = By.cssSelector("[class=member_colLeft_top_addBtn]"); //搜索框后的 + 按钮
    private final By addDepartmentButton = By.linkText("添加部门"); //添加部门 按钮
    private final By departmentNameBox = By.cssSelector("[name=name]"); //部门名称 字段
    private final By departmentMenu = By.linkText("选择所属部门"); //选择所属部门 下拉菜单
    private final By confirmButton2 = By.linkText("确定"); //确定 按钮

    public ContactPage(WebDriver driver) {
        super(driver);
        wait(10);
    }

    public ContactPage addMember(String username, String acctId, String mobile) {
        click(importExportButton);
        click(importExportButton);
        click(importExportButton);
        click(importExportButton); //点击批量导入/导出
        click(addMemberButton); //添加添加成员
        sendKeys(nameBox, username); //输入姓名
        sendKeys(acctIdBox, acctId); //输入账号
        sendKeys(mobileBox, mobile); //输入手机号
        click(sendInviteButton); //取消发送邮件或短信
        click(saveButton); //点击保存
        return this;
    }

    public ContactPage updateMember(String username, String updateUsername, String mobile) {
//        moveToElement(By.xpath("//*[@class=\"member_colRight_memberTable_td\"]//*[text()=" + username + "]"));
        search(username);
        click(updateButton);
        clear(nameBox);
        sendKeys(nameBox, updateUsername); //输入姓名
        clear(mobileBox);
        sendKeys(mobileBox, mobile); //输入手机号
        click(saveButton); //点击保存
        return this;
    }

    public ContactPage deleteMember() {
        click(deleteButton); //点击删除
        click(confirmButton); //点击确认
        return this;
    }

    public ContactPage addDepartment(String departmentName, String selectDepartment) {
        click(addButton); //点击搜索框后的+
        click(addDepartmentButton); //点击添加部门
        sendKeys(departmentNameBox, departmentName); //输入部门名称
        click(departmentMenu); //点击选择所属部门
        click(findElements(By.linkText(selectDepartment)).get(1)); //根据部门名称点击部门
        click(confirmButton2); //点击确定
        return this;
    }

    public ContactPage updateDepartment(String departmentName, String updateDepartmentName) {
        click(By.linkText(departmentName));
        click(By.xpath("//a[text()=\"" + departmentName + "\"]/span"));
        click(By.linkText("修改名称"));
        clear(departmentNameBox);
        sendKeys(departmentNameBox, updateDepartmentName);
        click(saveButton);
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
        clear(searchBox);
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

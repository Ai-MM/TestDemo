package com.imm.services.wechatwork.apiobject;

import com.imm.utils.FakerUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @Author: iMM
 * @Description: 企业微信-成员管理-接口对象
 * @Date: 2020/12/22
 */
public class MemberObject {

    //-----------------------------------------------------创建成员------------------------------------------------------
    public static Response creatMember(String userId, String name, String mobile, Object[] departmentList, String accessToken) {
        Map<String, Object> creatMemberBody = new HashMap<>();
        creatMemberBody.put("userid", userId); //成员ID
        creatMemberBody.put("name", name); //成员名称
        creatMemberBody.put("mobile", mobile); //手机号码，企业内必须唯一，mobile/email二者不能同时为空
        creatMemberBody.put("department", departmentList); //所属部门id列表,不超过100个
        creatMemberBody.put("to_invite", false); //是否邀请该成员使用企业微信，默认值为true
        return given().log().all().queryParam("access_token", accessToken)
                .body(creatMemberBody)
                .contentType("application/json;charset=UTF-8")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().body().extract().response();
    }

    public static void creatMember(String userId, String accessToken) {
        Map<String, Object> creatMemberBody = new HashMap<>();
        creatMemberBody.put("userid", userId); //成员ID
        creatMemberBody.put("name", "成员名称测试" + FakerUtil.threadTimeStamp()); //成员名称
        creatMemberBody.put("mobile", FakerUtil.phoneNumber()); //手机号码，企业内必须唯一，mobile/email二者不能同时为空
        creatMemberBody.put("department", new Object[]{1}); //所属部门id列表,不超过100个
        creatMemberBody.put("to_invite", false); //是否邀请该成员使用企业微信，默认值为true
        given().log().all().queryParam("access_token", accessToken)
                .body(creatMemberBody)
                .contentType("application/json;charset=UTF-8")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().body().extract().response();
    }

    public static void creatMember(String accessToken) {
        Map<String, Object> creatMemberBody = new HashMap<>();
        creatMemberBody.put("userid", FakerUtil.threadTimeStamp()); //成员ID
        creatMemberBody.put("name", "成员名称测试" + FakerUtil.threadTimeStamp()); //成员名称
        creatMemberBody.put("mobile", FakerUtil.phoneNumber()); //手机号码，企业内必须唯一，mobile/email二者不能同时为空
        creatMemberBody.put("department", new Object[]{1}); //所属部门id列表,不超过100个
        creatMemberBody.put("to_invite", false); //是否邀请该成员使用企业微信，默认值为true
        given().log().all().queryParam("access_token", accessToken)
                .body(creatMemberBody)
                .contentType("application/json;charset=UTF-8")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().body().extract().response();
    }

    //-----------------------------------------------------编辑成员------------------------------------------------------
    public static Response updateMember(String userId, String name, String accessToken) {
        HashMap<String, Object> updateBody = new HashMap<>();
        updateBody.put("userid", userId);
        updateBody.put("name", name);
        return given().log().all().queryParam("access_token", accessToken)
                .body(updateBody)
                .contentType("application/json;charset=UTF-8")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then().log().body().extract().response();
    }

    //-----------------------------------------------------读取成员------------------------------------------------------
    public static Response getMember(String userId, String accessToken) {
        return given().log().all().queryParam("access_token", accessToken)
                .queryParam("userid", userId)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().body().extract().response();
    }

    //----------------------------------------------------获取部门成员-----------------------------------------------------
    public static Response getDepartmentMember(String departmentId, String fetchChild, String accessToken) {
        return given().log().all().queryParam("access_token", accessToken)
                .queryParam("department_id", departmentId)
                .queryParam("fetch_child", fetchChild) //是否递归获取子部门下面的成员：1-递归获取，0-只获取本部门
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then().log().body().extract().response();
    }

    //---------------------------------------------------获取部门成员详情----------------------------------------------------
    public static Response getDepartmentMemberMsg(String departmentId, String fetchChild, String accessToken) {
        return given().log().all().queryParam("access_token", accessToken)
                .queryParam("department_id", departmentId)
                .queryParam("fetch_child", fetchChild)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/list")
                .then().log().body().extract().response();
    }

    //-----------------------------------------------------删除成员------------------------------------------------------
    public static Response deleteMember(String userId, String accessToken) {
        return given().log().all().queryParam("access_token", accessToken)
                .queryParam("userid", userId)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().body().extract().response();
    }

    //----------------------------------------------------批量删除成员-----------------------------------------------------
    public static Response batchDeleteMember(Object[] userIdList, String accessToken) {
        HashMap<String, Object> batchDeleteBody = new HashMap<>();
        batchDeleteBody.put("useridlist", userIdList);
        return given().log().all().queryParam("access_token", accessToken)
                .body(batchDeleteBody)
                .contentType("application/json;charset=UTF-8")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete")
                .then().log().body().extract().response();
    }
}

package com.imm.framework.services;

import com.imm.utils.PlaceholderUtil;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @Author: iMM
 * @Description: 接口动作对象
 * @Date: 2020/12/26
 */
//@JsonIgnoreProperties(ignoreUnknown = true) //出现UnrecognizedPropertyException: Unrecognized field解决方法
public class ApiActionModel {
    private String method = "get";
    private String url;
    private String get;
    private String post;
    private String put;
    private String delete;
    private String body;
    private String contentType;
    private HashMap<String, Object> queryParams;
    private HashMap<String, Object> headers;
    private ArrayList<String> formalParams;
    private HashMap<String, Object> actionVariables;
    private Response response;

    public Response run(ArrayList<String> actualParams) {
        String runBody = this.body;
        String runUrl = this.url;
        HashMap<String, Object> finalQuery = new HashMap<>();
        //确定请求方法和URL
        if (get != null) {
            runUrl = get;
        } else if (post != null) {
            runUrl = post;
            method = "post";
        } else if (put != null) {
            runUrl = put;
            method = "put";
        } else if (delete != null) {
            runUrl = delete;
            method = "delete";
        }
        //请求参数queryParams中全局变量的替换，需要占位符工具类PlaceholderUtil
        if (queryParams != null)
            finalQuery.putAll(PlaceholderUtil.resolveMap(queryParams, GlobalVariables.getGlobalVariables()));
        //body、URL中全局变量替换
        runBody = PlaceholderUtil.resolveString(runBody, GlobalVariables.getGlobalVariables());
        runUrl = PlaceholderUtil.resolveString(runUrl, GlobalVariables.getGlobalVariables());
        if (formalParams != null && formalParams.size() > 0 && actualParams != null && actualParams.size() > 0) {
            //根据形参formalParams和实参actualParams构件内部变量
            actionVariables = new HashMap<>();
            for (int index = 0; index < formalParams.size(); index++)
                actionVariables.put(formalParams.get(index), actualParams.get(index));
            //请求URL中内部变量替换
            if (queryParams != null)
                finalQuery.putAll(PlaceholderUtil.resolveMap(queryParams, actionVariables));
            //Body、URL中内部变量替换
            runBody = PlaceholderUtil.resolveString(runBody, actionVariables);
            runUrl = PlaceholderUtil.resolveString(runUrl, actionVariables);
        }
        //构造请求
        RequestSpecification request = given().log().all();
        if (contentType != null)
            request.contentType(contentType);
        if (headers != null)
            request.headers(headers);
        if (finalQuery.size() > 0)
            request.queryParams(finalQuery);
        if (runBody != null)
            request.body(runBody);
        return this.response = request.request(method, runUrl).then().log().all().extract().response();
    }
}

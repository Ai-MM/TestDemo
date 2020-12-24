package com.imm.services.wechatwork.apiobject;

import static io.restassured.RestAssured.given;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/22
 */
public class TokenHelper {
    public static String getAccessToken(String corpId, String corpSecret) {
        return given().log().all()
                .queryParam("corpid", corpId)
                .queryParam("corpsecret", corpSecret)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET")
                .then().log().body().extract().response().path("access_token");
    }

    public static String getAccessToken() {
        return given()
                .queryParam("corpid", "wwe38e48cfb5b7e661")
                .queryParam("corpsecret", "TfdFr84aKdOVQmoGGrLFvT2SYR4LMLPrXx3rxrOEY6c")
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET")
                .then().extract().response().path("access_token");
    }
}

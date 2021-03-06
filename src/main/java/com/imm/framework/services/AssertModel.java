package com.imm.framework.services;

import io.restassured.response.Response;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/26
 */
public class AssertModel {
    private String actual;
    private String matcher;
    private String expect;
    private String reason;

    /*********************************************** Getter And Setter ************************************************/
    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

package com.imm.framework.services;

import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/26
 */
public class StepResult {
    private ArrayList<Executable> assertList;
    private HashMap<String, Object> stepVariables;
    private Response response;

    /*********************************************** Getter And Setter ************************************************/
    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, Object> getStepVariables() {
        return stepVariables;
    }

    public void setStepVariables(HashMap<String, Object> stepVariables) {
        this.stepVariables = stepVariables;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

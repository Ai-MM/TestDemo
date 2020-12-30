package com.imm.framework.services;

import com.imm.utils.PlaceholderUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/26
 */
public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);

    private String api;
    private String action;
    private ArrayList<String> actualParams;
    private HashMap<String, Object> save;
    private HashMap<String, Object> saveGlobal;
    private ArrayList<AssertModel> asserts; //需定义AssertModel类
    private StepResult stepResult; //需定义StepResult类
    private ArrayList<String> finalActualParams = new ArrayList<>();
    private HashMap<String, Object> stepVariables = new HashMap<>();
    private ArrayList<Executable> assertList = new ArrayList<>();

    public StepResult run(HashMap<String, Object> testCaseVariables) {
        if (actualParams != null)
            finalActualParams.addAll(PlaceholderUtil.resolveList(actualParams, testCaseVariables));
        //根据用例中的api和action，取出并执行action
        Response response = ApiLoader.getAction(api, action).run(finalActualParams);
        //保存step变量
        if (save != null) {
            save.forEach((StepVariableName, path) -> {
                stepVariables.put(StepVariableName, response.path(path.toString()));
                logger.info("step变量更新: " + stepVariables);
            });
        }
        //保存全局变量
        if (saveGlobal != null) {
            saveGlobal.forEach((GlobalVariablesName, path) -> {
                GlobalVariables.getGlobalVariables().put(GlobalVariablesName, response.path(path.toString()));
                logger.info("全局变量更新: " + GlobalVariables.getGlobalVariables());
            });
        }
        //将断言存入一个列表，最后在用例中统一进行软断言
        if (asserts != null) {
            asserts.forEach(assertModel -> {
                assertList.add(() -> {
                    assertThat(assertModel.getReason(),
                            response.path(assertModel.getActual()), equalTo(assertModel.getExpect()));
                });
            });
        }
        //将响应和断言结果保存到stepResult对象中并返回
        stepResult.setAssertList(assertList);
        stepResult.setStepVariables(stepVariables);
        stepResult.setResponse(response);
        return stepResult;
    }

    /*********************************************** Getter And Setter ************************************************/
    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParams() {
        return actualParams;
    }

    public void setActualParams(ArrayList<String> actualParams) {
        this.actualParams = actualParams;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public HashMap<String, Object> getSave() {
        return save;
    }

    public void setSave(HashMap<String, Object> save) {
        this.save = save;
    }

    public HashMap<String, Object> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, Object> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

    public ArrayList<String> getFinalActualParams() {
        return finalActualParams;
    }

    public void setFinalActualParams(ArrayList<String> finalActualParams) {
        this.finalActualParams = finalActualParams;
    }

    public HashMap<String, Object> getStepVariables() {
        return stepVariables;
    }

    public void setStepVariables(HashMap<String, Object> stepVariables) {
        this.stepVariables = stepVariables;
    }

    public StepResult getStepResult() {
        return stepResult;
    }

    public void setStepResult(StepResult stepResult) {
        this.stepResult = stepResult;
    }

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }
}

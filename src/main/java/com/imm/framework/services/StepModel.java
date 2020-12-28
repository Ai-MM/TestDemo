package com.imm.framework.services;

import com.imm.utils.PlaceholderUtil;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

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
    private ArrayList<AssertModel> asserts;
    private HashMap<String, Object> save;
    private HashMap<String, Object> saveGlobal;

    private ArrayList<String> finalActualParam;
    private HashMap<String, Object> stepVariables;
    private ArrayList<Executable> assertList;

    public void run(HashMap<String, Object> testCaseVariables) {
        if (actualParams != null)
            finalActualParam.addAll(PlaceholderUtil.resolveList(actualParams, testCaseVariables));
    }
}

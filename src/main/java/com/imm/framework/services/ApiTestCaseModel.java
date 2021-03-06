package com.imm.framework.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.imm.utils.FakerUtil;
import com.imm.utils.FileUtil;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/26
 */
public class ApiTestCaseModel {
    private static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModel.class);

    private String name;
    private String description;
    private ArrayList<StepModel> steps;
    private ArrayList<Executable> assertList = new ArrayList<>();
    private HashMap<String, Object> testCaseVariables = new HashMap<>();

    /**
     * 加载单一文件为ApiTestCaseModel对象
     *
     * @param filePath 文件，默认根路径为resources
     * @return 接口测试用例对象ApiTestCaseModel
     */
    public static ApiTestCaseModel load(String filePath) {
        try {
            return new ObjectMapper(new YAMLFactory())
                    .readValue(FileUtil.getResourceAsStream(filePath), ApiTestCaseModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("从" + filePath + "加载ApiTestCaseModel对象失败: " + e);
            return null;
        }
    }

    public void run() {
        //加载用例层关键字变量
        testCaseVariables.put("timeStamp", FakerUtil.timeStamp());
        testCaseVariables.put("phoneNumber", FakerUtil.phoneNumber());
        logger.info("接口测试用例变量更新: " + testCaseVariables);
        //遍历step并执行
        steps.forEach(step -> {
            StepResult stepResult = step.run(testCaseVariables);
            //处理step保存的变量，在接口之间传递
            HashMap<String, Object> stepVariables = stepResult.getStepVariables();
            if (stepVariables.size() > 0) {
                testCaseVariables.putAll(stepVariables);
                logger.info("用例变量更新: " + testCaseVariables);
            }
            //处理断言列表，最后进行统一软断言
            ArrayList<Executable> assertList = stepResult.getAssertList();
            if (assertList.size() > 0) {
                this.assertList.addAll(assertList);
            }
        });
        assertAll(assertList);
    }

    /*********************************************** Getter And Setter ************************************************/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, Object> getTestCaseVariables() {
        return testCaseVariables;
    }

    public void setTestCaseVariables(HashMap<String, Object> testCaseVariables) {
        this.testCaseVariables = testCaseVariables;
    }
}

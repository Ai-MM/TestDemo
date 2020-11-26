package com.imm.framework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: iMM
 * @Description: UI测试用例模型
 * @Date: 2020/11/26
 */
public class UITestCaseModel {
    public String name; //用例名称
    public String description; //用例描述
    public List<HashMap<String, Object>> steps; //测试步骤

    public static UITestCaseModel loadTestCase(String fileName) {
        try {
            return new ObjectMapper(new YAMLFactory())
                    .readValue(UITestCaseModel.class.getResourceAsStream(fileName), UITestCaseModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

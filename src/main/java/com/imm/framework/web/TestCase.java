package com.imm.framework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/28
 */
public class TestCase {
//    public String name; //用例名称
//    public String description; //用例描述
    public List<String> data; //测试数据
    public List<HashMap<String, Object>> steps; //测试步骤
    public int index = 0;

    /**
     * 读取yaml文件填充数据
     *
     * @param fileName yaml文件（resources目录下）
     * @return 测试用例对象
     */
    public static POTestCase loadTestCase(String fileName) {
        try {
            return new ObjectMapper(new YAMLFactory())
                    .readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), POTestCase.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new POTestCase();
        }
    }

    /**
     * 测试用例裂变（根据data中的参数个数，自动生成多个测试用例）
     *
     * @return 测试用例列表
     */
    public ArrayList<TestCase> generateCases() {
        ArrayList<TestCase> testCases = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            POTestCase testCase = new POTestCase();
            testCase.data = data;
            testCase.steps = steps;
            testCase.index = i;
            testCases.add(testCase);
        }
        return testCases;
    }

    /**
     * 替换yaml文件中的变量
     *
     * @param step 测试步骤
     * @param key  测试步骤的关键字
     * @return 替换后的值
     */
    public Object getStepValue(HashMap<String, Object> step, String key) {
        //todo 复杂结构需要用递归
        Object value = step.get(key);
        if (value instanceof String) {
            return value.toString().replace("${data}", data.get(index));
        }
        return value;
    }

    public void runCase() {
    }
}

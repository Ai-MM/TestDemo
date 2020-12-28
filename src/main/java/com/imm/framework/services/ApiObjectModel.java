package com.imm.framework.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.imm.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: iMM
 * @Description: 接口对象
 * @Date: 2020/12/26
 */
public class ApiObjectModel {
    private static final Logger logger = LoggerFactory.getLogger(ApiObjectModel.class);

    private String name;
    private HashMap<String, ApiActionModel> actions;
    private HashMap<String, Object> objectVariables;

    /**
     * 加载单一文件为ApiObjectModel对象
     *
     * @param filePath 文件，默认根路径为resources
     * @return 接口对象ApiObjectModel
     */
    public static ApiObjectModel load(String filePath) {
        try {
            return new ObjectMapper(new YAMLFactory())
                    .readValue(FileUtil.getResourceAsStream(filePath), ApiObjectModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("从" + filePath + "加载ApiObjectModel对象失败: " + e);
            return null;
        }
    }

    /*********************************************** Getter And Setter ************************************************/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ApiActionModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, ApiActionModel> actions) {
        this.actions = actions;
    }

    public HashMap<String, Object> getObjectVariables() {
        return objectVariables;
    }

    public void setObjectVariables(HashMap<String, Object> objectVariables) {
        this.objectVariables = objectVariables;
    }
}

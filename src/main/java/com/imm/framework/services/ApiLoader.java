package com.imm.framework.services;

import com.imm.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @Author: iMM
 * @Description: 接口对象加载器
 * @Date: 2020/12/26
 */
public class ApiLoader {
    private static final Logger logger = LoggerFactory.getLogger(ApiLoader.class);

    private static ArrayList<ApiObjectModel> apis = new ArrayList<>();

    /**
     * 加载文件夹下所有包含_api的文件为ApiObjectModel对象，并添加到列表中
     *
     * @param dir 文件夹路径
     */
    public static void load(String dir) {
        try {
            Stream.of(new File(FileUtil.getResource(dir).toURI()).list((dir1, name) -> name.contains("_api")))
                    .forEach(fileName -> {
                        apis.add(ApiObjectModel.load(dir + "/" + fileName));
                    });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过接口对象名称和接口动作对象名称，获取接口动作对象，未找到则返回null
     *
     * @param apiName    接口对象名称
     * @param actionName 接口动作对象名称
     * @return 接口动作对象
     */
    public static ApiActionModel getAction(String apiName, String actionName) {
        ApiActionModel[] apiActionModels = {new ApiActionModel()};
        apis.stream().filter(api -> api.getName().equals(apiName)).forEach(api -> {
            apiActionModels[0] = api.getActions().get(actionName);
        });
        if (apiActionModels[0] == null) logger.info("未找到接口对象: " + apiName + "中的action: " + actionName);
        return apiActionModels[0];
    }
}

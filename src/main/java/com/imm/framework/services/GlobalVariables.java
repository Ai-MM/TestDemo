package com.imm.framework.services;

import java.util.HashMap;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/26
 */
public class GlobalVariables {
    private static HashMap<String, Object> globalVariables;

    /*********************************************** Getter And Setter ************************************************/
    public static HashMap<String, Object> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, Object> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }
}

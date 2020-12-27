package com.imm.framework.services;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/26
 */
public class StepModel {
    private String api;
    private String action;
    private ArrayList<String> actualParam;
    private ArrayList<AssertModel> asserts;
    private HashMap<String, Object> save;
    private HashMap<String, Object> saveGlobal;
}

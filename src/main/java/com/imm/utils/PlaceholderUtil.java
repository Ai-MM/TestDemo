package com.imm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: iMM
 * @Description: 占位符工具类，进行变量替换
 * @Date: 2020/12/26
 */
public class PlaceholderUtil {
    private static final Logger logger = LoggerFactory.getLogger(PlaceholderUtil.class);
    private static final String PREFIX = "${"; //定义占位符的前缀
    private static final String SUFFIX = "}"; //定义占位符的后缀

    public static String resolveString(String text, Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty() || text == null || text.isEmpty() || !text.contains(PREFIX))
            return text;
        StringBuilder builder = new StringBuilder(text);
        int startIndex = builder.indexOf(PREFIX); //前缀的索引，未匹配到则返回-1
        do {
            int endIndex = builder.indexOf(SUFFIX, startIndex); //后缀的索引（从前缀索引开始匹配），未匹配到则返回-1
            if (endIndex == -1) break; //后缀未匹配到时直接跳出循环
            String placeholder = builder.substring(startIndex + PREFIX.length(), endIndex); //截取前后缀中的占位符
            int nextIndex = endIndex + SUFFIX.length(); //下一次循环时开始匹配的索引
            String value = parameter.get(placeholder).toString();
            if (value != null) {
                builder.replace(startIndex, nextIndex, value); //用实际值替换占位符
                nextIndex = startIndex + value.length(); //下一次循环时开始匹配的索引
            } else {
                logger.info("Could not resolve placeholder \"" + placeholder + "\" in: " + text);
            }
            startIndex = builder.indexOf(PREFIX, nextIndex); //下一次循环时前缀的索引
        } while (startIndex != -1);
        return builder.toString(); //替换后的字符串
    }

    public static ArrayList<String> resolveList(ArrayList<String> list, Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty() || list == null || list.isEmpty()) return list;
        ArrayList<String> arrayList = new ArrayList<>();
        list.forEach(str -> {
            arrayList.add(resolveString(str, parameter));
        });
        return arrayList;
    }

    public static HashMap<String, Object> resolveMap(HashMap<String, Object> hashMap, Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty() || hashMap == null || hashMap.isEmpty()) return hashMap;
        HashMap<String, Object> newHashMap = new HashMap<>();
        hashMap.forEach((key, value) -> {
            newHashMap.put(key, resolveString(value.toString(), parameter));
        });
        return newHashMap;
    }

    public static void main(String[] args) {
        StringBuilder stringBuffer = new StringBuilder("12345");
        System.out.println(stringBuffer.indexOf("23"));
        System.out.println(stringBuffer.substring(3));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("a", "123");
        System.out.println(hashMap.get("1"));
    }
}

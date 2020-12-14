package com.imm.utils;

import java.io.InputStream;
import java.net.URL;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/14
 */
public class FileUtil {
    public static URL getResource(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResource(fileName);
    }

    public static InputStream getResourceAsStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }
}

package com.imm.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.imm.framework.services.ApiLoader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/29
 */
public class ApiLoaderTest {
    @Test
    void load() {
        ApiLoader.load("services/resources/api");
    }

    @Test
    void loadYam() throws IOException {
        new ObjectMapper(new YAMLFactory())
                .readValue(new File("src/test/resources/services/resources/api/test.yaml"), YamlTest.class);
    }
}

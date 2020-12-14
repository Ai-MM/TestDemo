package com.imm.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.imm.utils.FileUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/13
 */
public class WireMockTest {
    static WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer(options().port(8888));
        wireMockServer.start();
        configureFor(8888);
    }

    //    @Test
    void demo1() throws InterruptedException {
        stubFor(get(urlEqualTo("/c/job/31"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain;charset=UTF-8")
                        .withBody("Hello World! Hello WireMockServer!测试")));
        Thread.sleep(600000);
    }

    @Test
    void demo2() throws IOException, InterruptedException {
        stubFor(get(urlMatching(".*")).atPriority(10)
                .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));
        stubFor(get(urlMatching("/c/job/31/l/latest.json.*")).atPriority(1)
                .willReturn(aResponse()
                        .withBody(FileUtil.getResourceAsStream("wiremock_response.json").readAllBytes())));
        Thread.sleep(600000);
    }
}

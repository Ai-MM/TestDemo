package com.imm.mock;

import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.Test;

import static spark.Spark.*;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/14
 */
public class BrowserMobProxyTest {
    @Test
    void demo1() throws InterruptedException {
        BrowserMobProxyServer proxy = new BrowserMobProxyServer();
        proxy.start(8888);
        proxy.addResponseFilter((response, messageContents, messageInfo) -> {
            if (messageInfo.getOriginalUrl().contains(".json")) {
                //todo: json -> hashmap -> recursion -> hashmap -> json
                String newContents = messageContents.getTextContents().replaceAll(":\"[^\"]*\"", ":null");
                messageContents.setTextContents(newContents);
                System.out.println(newContents);
            }
        });
        proxy.addRequestFilter((request, messageContents, messageInfo) -> {
            request.setUri("/");
            return null;
        });
        Thread.sleep(600000);
    }

    @Test
    void demo2() {
        get("/hello", (req, res) -> "Hello World!");
        get("/proxy",((request, response) -> {
            BrowserMobProxyServer proxy = new BrowserMobProxyServer();
            proxy.start(request.port());
            return null;
        }));
    }
}

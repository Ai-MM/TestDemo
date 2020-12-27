package com.imm.services.wechatwork.member;

import com.imm.services.wechatwork.apiobject.MemberObject;
import com.imm.services.wechatwork.apiobject.TokenHelper;
import com.imm.services.wechatwork.task.EnvClearTask;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/12/25
 */
public class ConcurrentTest {
    private static String accessToken;
    @BeforeAll
    static void beforeAll() {
        accessToken = TokenHelper.getAccessToken();
        EnvClearTask.clearMember(accessToken);
    }

    @RepeatedTest(10)
    @DisplayName("创建成员并发测试")
    void creatMember() {
        Response creatRes = MemberObject
                .creatMember("1", "创建成员并发测试", "18883857944", new Object[]{1}, accessToken);
        assertAll("创建成员接口断言", () -> {
            assertEquals("0", creatRes.path("errcode").toString());
//            assertEquals("created", creatRes.path("errmsg"));
        });
    }
}

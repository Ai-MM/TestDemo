package com.imm.services.wechatwork.member;

import com.imm.services.wechatwork.apiobject.MemberObject;
import com.imm.services.wechatwork.apiobject.TokenHelper;
import com.imm.services.wechatwork.task.EnvClearTask;
import com.imm.utils.FakerUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: iMM
 * @Description: 企业微信-成员管理-接口测试
 * @Date: 2020/12/22
 */
public class MemberTest {
    private static final Logger logger = LoggerFactory.getLogger(MemberTest.class);
    static String accessToken;

    @BeforeAll
    static void beforeAll() {
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);
    }

    @BeforeEach
    void beforeEach() {
        EnvClearTask.clearMember(accessToken);
    }

    @ParameterizedTest
    @DisplayName("创建成员")
    @CsvFileSource(resources = "/services/creat_member.csv", numLinesToSkip = 1)
    void creatMember(String userId, String name, String mobile, String department, String errCode) {
        Response creatRes = MemberObject
                .creatMember(userId, name, mobile, new Object[]{department}, accessToken);
        assertAll("创建成员接口断言", () -> {
            assertEquals(errCode, creatRes.path("errcode").toString());
//            assertEquals("created", creatRes.path("errmsg"));
        });
    }

    @Test
    @DisplayName("读取成员")
    void getMember() {
        String userId = FakerUtil.threadTimeStamp();
        MemberObject.creatMember(userId, accessToken);
        Response getMemberRes = MemberObject.getMember(userId, accessToken);
        assertAll("读取成员接口断言", () -> {
            assertEquals("0", getMemberRes.path("errcode").toString());
            assertEquals(userId, getMemberRes.path("userid"));
        });
    }

    @Test
    @DisplayName("编辑成员")
    void updateMember() {
        String userId = FakerUtil.threadTimeStamp();
        MemberObject.creatMember(userId, accessToken);
        Response updateRes = MemberObject.updateMember(userId, "编辑成员测试", accessToken);
        assertAll("编辑成员接口断言", () -> {
            assertEquals("0", updateRes.path("errcode").toString());
            assertEquals("updated", updateRes.path("errmsg"));
        });
    }

    @Test
    @DisplayName("获取部门成员")
    void getDepartmentMember() {
        String userId = FakerUtil.threadTimeStamp();
        MemberObject.creatMember(userId, accessToken);
        Response getDepartmentMemberRes = MemberObject.getDepartmentMember("1", "1", accessToken);
        assertAll("获取部门成员接口断言", () -> {
            assertEquals("0", getDepartmentMemberRes.path("errcode").toString());
            assertThat(getDepartmentMemberRes.path("userlist.userid"), hasItem(userId));
        });
    }

    @Test
    @DisplayName("获取部门成员详情")
    void getDepartmentMemberMsg() {
        String userId = FakerUtil.threadTimeStamp();
        MemberObject.creatMember(userId, accessToken);
        Response departmentMemberMsgRes = MemberObject.getDepartmentMemberMsg("1", "1", accessToken);
        assertAll("获取部门详情接口断言", () -> {
            assertEquals("0", departmentMemberMsgRes.path("errcode").toString());
            assertThat(departmentMemberMsgRes.path("userlist.userid"), hasItem(userId));
        });
    }

    @Test
    @DisplayName("删除成员")
    void deleteMember() {
        String userId = FakerUtil.threadTimeStamp();
        MemberObject.creatMember(userId, accessToken);
        Response deleteRes = MemberObject.deleteMember(userId, accessToken);
        assertAll("删除成员接口断言", () -> {
            assertEquals("0", deleteRes.path("errcode").toString());
            assertEquals("deleted", deleteRes.path("errmsg"));
        });
    }

    @Test
    @DisplayName("批量删除成员")
    void batchDeleteMember() {
        String userId1 = FakerUtil.threadTimeStamp();
        MemberObject.creatMember(userId1, accessToken);
        String userId2 = FakerUtil.threadTimeStamp();
        MemberObject.creatMember(userId2, accessToken);
        Response batchDeleteRes = MemberObject.batchDeleteMember(new Object[]{userId1, userId2}, accessToken);
        assertAll("批量删除成员接口断言", () -> {
            assertEquals("0", batchDeleteRes.path("errcode").toString());
            assertEquals("deleted", batchDeleteRes.path("errmsg"));
        });
    }
}

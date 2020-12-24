package com.imm.services.wechatwork.task;

import com.imm.services.wechatwork.apiobject.MemberObject;

import java.util.ArrayList;

/**
 * @Author: iMM
 * @Description: 企业微信-环境/数据清理任务
 * @Date: 2020/12/22
 */
public class EnvClearTask {
    public static void clearMember(String accessToken) {
        ArrayList<String> userIdList = MemberObject.getDepartmentMember("1", "1", accessToken).path("userlist.userid");
        for (String userId : userIdList) {
            if (userId.equals("ZhouChenJie")) continue;
            MemberObject.deleteMember(userId, accessToken);
        }
    }
}

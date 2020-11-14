package com.imm.uitest;

import com.imm.utils.LoginUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author: iMM
 * @Description:
 * @Date: 2020/11/13
 */
public class WeChatWork extends BaseTest {
    @Test
    void saveCookiesTest() {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        try {
            Thread.sleep(40000);
            LoginUtil.saveCookies(driver);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginTest() {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        try {
            LoginUtil.loadCookies(driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
    }
}

package com.cq.loginAuth;

import lombok.Data;

/**
 * @ClassName : AuthEmum
 * @Description : 接口权限枚举
 * @Author : WXD
 * @Date: 2020-09-24 11:45
 */

public enum AuthEmum {
    CONTROLLER_GLOBAL("GLOBAL", "接口全局权限");

    private String authCode;
    private String authMessage;

    AuthEmum(String authCode, String authMessage) {
        this.authCode = authCode;
        this.authMessage = authMessage;
    }

    public String getAuthCode() {
        return authCode;
    }

   /* public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }*/

    public String getAuthMessage() {
        return authMessage;
    }

  /*  public void setAuthMessage(String authMessage) {
        this.authMessage = authMessage;
    }*/
}

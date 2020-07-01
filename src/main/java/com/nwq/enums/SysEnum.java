package com.nwq.enums;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/6/29 10:59
 * @Version: 1.0
 */
public enum SysEnum {
    COOKIE_LOGIN_NAME("cookieLoginName"),
    SESSION_LOGIN_CODE("sessionLoginCode");

    private String value;

    SysEnum() {
    }

    SysEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

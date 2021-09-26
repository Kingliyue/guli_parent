package com.atliyue.ucenter.vo;

import lombok.Data;

@Data
public class Ucenter {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;

    private String name;
}

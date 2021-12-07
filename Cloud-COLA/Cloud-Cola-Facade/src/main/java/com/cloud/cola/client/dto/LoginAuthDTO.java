package com.cloud.cola.client.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author 赵参谋
 * @version 1.0$
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：登录认证
 * @date 2021/11/27 10:49
 **/
@Data
public class LoginAuthDTO {

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 短信
     */
    private String validMsg;

    /**
     * 扫码标识
     */
    private String scanCode;

    /**
     * 登录类型
     */
    @Min(value = 1,message = "登录类型（1账号密码，2短信,3扫码）")
    private int loginType;
}

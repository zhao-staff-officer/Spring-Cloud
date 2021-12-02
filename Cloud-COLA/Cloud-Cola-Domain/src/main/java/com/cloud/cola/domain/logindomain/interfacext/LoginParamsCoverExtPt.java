package com.cloud.cola.domain.logindomain.interfacext;

import com.alibaba.cola.extension.ExtensionPointI;
import com.cloud.cola.client.dto.LoginAuthDTO;

/**
 * @author 赵参谋
 * @version 1.0$
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：登录对象转换参数
 * @date 2021/11/27 11:21
 **/
public interface LoginParamsCoverExtPt extends ExtensionPointI {

    <T> T LogonCover(LoginAuthDTO loginAuthDTO);
}

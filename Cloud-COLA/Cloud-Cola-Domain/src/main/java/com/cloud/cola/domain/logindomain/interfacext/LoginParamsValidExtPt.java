package com.cloud.cola.domain.logindomain.interfacext;

import com.alibaba.cola.extension.ExtensionPointI;
import com.cloud.cola.domain.logindomain.po.LoginPo;

/**
 * @author 赵参谋
 * @version 1.0$
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：登录参数校验
 * @date 2021/11/27 13:20
 **/
public interface LoginParamsValidExtPt<T extends LoginPo> extends ExtensionPointI {

     boolean validLoginParams(T t);
}

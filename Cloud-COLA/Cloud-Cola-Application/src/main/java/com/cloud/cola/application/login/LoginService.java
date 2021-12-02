package com.cloud.cola.application.login;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import com.cloud.cola.client.constant.ConstantBizId;
import com.cloud.cola.client.dto.LoginAuthDTO;
import com.cloud.cola.client.facade.LoginFacade;
import com.cloud.cola.domain.logindomain.interfacext.LoginParamsCoverExtPt;
import com.cloud.cola.domain.logindomain.interfacext.LoginParamsValidExtPt;
import com.cloud.cola.domain.logindomain.po.LoginPo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：登录处理
 * @date 2021/11/27 10:52
 **/
@Service
@RequiredArgsConstructor
public class LoginService  implements LoginFacade {

    private final ExtensionExecutor extensionExecutor;

    @Override
    public boolean loginAuth(LoginAuthDTO loginAuthDTO) {
        BizScenario bizScenario = BizScenario.valueOf(ConstantBizId.login,String.valueOf(loginAuthDTO.getLoginType()));
        LoginPo loginPo =extensionExecutor.execute(LoginParamsCoverExtPt.class,bizScenario, action->action.LogonCover(loginAuthDTO));
        extensionExecutor.execute(LoginParamsValidExtPt.class,bizScenario, action->action.validLoginParams(loginPo));
        return false;
    }
}

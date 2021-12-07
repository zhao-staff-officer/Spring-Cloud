#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.login;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import ${package}.client.constant.ConstantBizId;
import ${package}.client.dto.LoginAuthDTO;
import ${package}.client.facade.LoginFacade;
import ${package}.domain.logindomain.interfacext.LoginParamsCoverExtPt;
import ${package}.domain.logindomain.interfacext.LoginParamsValidExtPt;
import ${package}.domain.logindomain.po.LoginPo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 赵参谋
 * @version ${symbol_dollar}
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

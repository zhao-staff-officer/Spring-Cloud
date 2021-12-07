#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.logindomain.po;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.cola.extension.Extension;
import ${package}.client.constant.ConstantBizId;
import ${package}.client.constant.ConstantCase;
import ${package}.client.constant.ConstantScenario;
import ${package}.client.dto.LoginAuthDTO;
import ${package}.domain.logindomain.interfacext.LoginParamsCoverExtPt;
import ${package}.domain.logindomain.interfacext.LoginParamsValidExtPt;
import lombok.Data;

/**
 * @author 赵参谋
 * @version 1.0${symbol_dollar}
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：账号密码登录
 * @date 2021/11/27 11:08
 **/
@Data
@Extension(bizId = ConstantBizId.login,useCase = ConstantCase.loginPwd)
public class LoginPwdPo extends LoginPo implements LoginParamsCoverExtPt, LoginParamsValidExtPt<LoginPwdPo> {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;


    /**
     * 对象转换
     * @param loginAuthDTO
     * @return
     */
    @Override
    public LoginPwdPo LogonCover(LoginAuthDTO loginAuthDTO) {
        System.out.println("对象转换");
        return BeanUtil.toBean(loginAuthDTO, LoginPwdPo.class);
    }

    /**
     * 参数校验
     * @param loginPwdPo
     * @return
     */
    @Override
    public  boolean validLoginParams(LoginPwdPo loginPwdPo) {
        System.out.println("对象校验");
        Assert.notEmpty(loginPwdPo.getUserName(),"用户名不为空");
        Assert.notEmpty(loginPwdPo.getPwd(),"密码不为空");
        return false;
    }

}

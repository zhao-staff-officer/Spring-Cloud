#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.facade;

import ${package}.client.dto.LoginAuthDTO;

/**
 * @author 赵参谋
 * @version 1.0${symbol_dollar}
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：登录认证
 * @date 2021/11/27 10:46
 **/
public interface LoginFacade {

    /**
     * 登录认证
     * @return
     */
    boolean loginAuth(LoginAuthDTO loginAuthDTO);



}

package com.cloud.cola.adapter;

import com.cloud.cola.application.login.LoginService;
import com.cloud.cola.client.dto.LoginAuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 赵参谋
 * @version 1.0$
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：登录
 * @date 2021/11/27 10:54
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/auth")
    public boolean login(@Valid @RequestBody LoginAuthDTO loginAuthDTO){
        return loginService.loginAuth(loginAuthDTO);
    }
}

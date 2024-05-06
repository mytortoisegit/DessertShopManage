package com.jia.sweetshop.controller;

import com.jia.sweetshop.model.domain.ApiResponse;
import com.jia.sweetshop.model.dto.UserDTO;
import com.jia.sweetshop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


/**
 * 登录认证接口
 */

@RestController
@RequestMapping("api/v1/element/user")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("login")
    public ApiResponse<HashMap<String, String>> login(@RequestBody UserDTO user) {
        System.out.println(user);
        return loginService.login(user);
    }

    @PostMapping("/test")
    // Security提供权限校验方式
//    @PreAuthorize("hasAuthority('system:user:manage')")
    // 自定义权限校验方式
    @PreAuthorize("@ex.hasAuthority('system:user:manage')")
    public ApiResponse<String> loginTest(@RequestBody UserDTO user) {
        System.out.println(user);
        return  ApiResponse.success("成功");
    }

}

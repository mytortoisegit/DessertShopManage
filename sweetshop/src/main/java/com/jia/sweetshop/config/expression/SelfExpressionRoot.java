package com.jia.sweetshop.config.expression;

import com.jia.sweetshop.model.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义 方法权限校验
 */
@Component("ex")
public class SelfExpressionRoot {

    public boolean hasAuthority(String authority) {
        // 获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        // 判断当前用户是否存在 传入参数 authority权限
       return permissions.contains(authority);
    }
}

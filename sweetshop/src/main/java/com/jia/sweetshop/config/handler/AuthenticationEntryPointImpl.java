package com.jia.sweetshop.config.handler;

import com.alibaba.fastjson.JSON;
import com.jia.sweetshop.config.handler.utils.WebUtils;
import com.jia.sweetshop.model.domain.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义认证统一异常处理类
 */
@Component
public class AuthenticationEntryPointImpl  implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ApiResponse apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),"用户认证失败请查询登录","");
        String str = JSON.toJSONString(apiResponse);

        WebUtils.setResponseMessage(response,str);
    }
}

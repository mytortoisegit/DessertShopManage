package com.jia.sweetshop.config.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jia.sweetshop.config.handler.utils.WebUtils;
import com.jia.sweetshop.model.domain.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义授权失败统一处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {



    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ApiResponse apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(),"您的权限不足","");
        String str = JSON.toJSONString(apiResponse);
        WebUtils.setResponseMessage(response,str);
    }
}

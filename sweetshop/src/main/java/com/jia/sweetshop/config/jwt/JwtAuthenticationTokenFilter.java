package com.jia.sweetshop.config.jwt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/***
 * 定义JWT过滤器 验证用户token
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 从请求中获取 Token
            String token = tokenProvider.resolveToken(request);
            // 验证token
            if (token != null && tokenProvider.validateToken(token)) {
                // Token 有效，从 Token 中解析用户信息并设置为认证用户
                SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(token));
            }
        } catch (Exception ex) {
            logger.error("Failed to set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }
}

package com.jia.sweetshop.config.handler.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public class WebUtils {

    /**
     * 设置响应的状态码和消息
     * @param response HttpServletResponse 对象
     * @param message 响应消息
     */
    public static void setResponseMessage(HttpServletResponse response,  String message) {
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(message);
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加 Cookie 到响应中
     * @param response HttpServletResponse 对象
     * @param cookieName Cookie 名称
     * @param cookieValue Cookie 值
     * @param maxAge Cookie 最大存活时间（秒）
     * @param path Cookie 路径
     */
    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAge, String path) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 删除指定名称的 Cookie
     * @param response HttpServletResponse 对象
     * @param cookieName 要删除的 Cookie 名称
     * @param path Cookie 路径
     */
    public static void deleteCookie(HttpServletResponse response, String cookieName, String path) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    // 其他常用的 Web 操作可以在此添加...

}

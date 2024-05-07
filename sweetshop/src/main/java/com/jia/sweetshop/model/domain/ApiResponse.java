package com.jia.sweetshop.model.domain;

import lombok.Data;
import lombok.Getter;

/*
*  定义统一响应类
 */


@Getter
@Data
public class ApiResponse<T> {
    // Getters and Setters
    private int code;
    private String msg;
    private T data;

    public ApiResponse() {
    }
    public ApiResponse(int code) {
        this.code = code;
    }
    public ApiResponse(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(200, "success", null);
    }

    public static ApiResponse<Void> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public void setData(T data) {
        this.data = data;
    }

}

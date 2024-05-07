package com.jia.sweetshop.service;

import com.jia.sweetshop.model.domain.ApiResponse;
import com.jia.sweetshop.model.dto.UserDTO;

import java.util.HashMap;

public interface LoginService {

    ApiResponse<HashMap<String, String>> login(UserDTO user);

    /**
     * 查询用户信息
     * @return
     */
    ApiResponse<HashMap<String, String>> info();

    ApiResponse<HashMap<String, String>> logout();

}

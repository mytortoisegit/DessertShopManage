package com.jia.sweetshop.service;

import com.jia.sweetshop.model.domain.ApiResponse;
import com.jia.sweetshop.model.dto.UserDTO;

import java.util.HashMap;

public interface LoginService {

    ApiResponse<HashMap<String, String>> login(UserDTO user);
}

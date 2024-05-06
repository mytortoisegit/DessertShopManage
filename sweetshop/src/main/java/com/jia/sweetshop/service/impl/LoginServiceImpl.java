package com.jia.sweetshop.service.impl;

import com.jia.sweetshop.config.jwt.utils.JWTUtil;
import com.jia.sweetshop.config.redis.utils.RedisCache;
import com.jia.sweetshop.model.domain.ApiResponse;
import com.jia.sweetshop.model.domain.LoginUser;
import com.jia.sweetshop.model.dto.UserDTO;
import com.jia.sweetshop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ApiResponse<HashMap<String, String>> login(UserDTO user) {

        //获取容器中 AuthenticationManager  用户认证
        // 将用户登录信息放入 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // 认证比较
        Authentication authenticate = authenticationManager.authenticate(userToken);
        //如果认证没通过 给出提示
        if(Objects.isNull(authenticate)) {
            System.out.println("登录失败");
            throw  new RuntimeException("登录失败");
        }
        //认证通过，使用userId 生成JWT 放入ApiResponse 返回
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getSysUser().getId().toString();
        //将用户完整信息存入redis 中 userId作为key
        redisCache.setCacheObject("login:" + userId, loginUser,60*60*1000, TimeUnit.SECONDS);
        //生成JWT 登录信息
        String jwtToken = JWTUtil.generateToken(userId);
        HashMap<String, String> res = new HashMap<>();
        res.put("token", jwtToken);
        return new  ApiResponse(200,"登录成功",res);


    }
}

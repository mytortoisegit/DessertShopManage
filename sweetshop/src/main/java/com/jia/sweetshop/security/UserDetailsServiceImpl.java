package com.jia.sweetshop.security;

import com.jia.sweetshop.mapper.UserMapper;
import com.jia.sweetshop.model.converter.UserConverter;
import com.jia.sweetshop.model.dto.UserDTO;
import com.jia.sweetshop.model.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 提供一个实现了 UserDetailsService 接口的类，
 * 用于从数据库或其他持久化存储中获取用户信息。
 * 这个类应该根据用户名返回一个 UserDetails 对象，包含用户名、密码和用户的权限信息。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserPO userPO = userRepository.findByUsername(username);
        UserDTO userDTO = UserConverter.poToDto(userPO);
        if (userPO == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.builder()
                .username(userDTO.getUserName())
                .password(userDTO.getPassword())
                .roles(userDTO.getRole().toArray(new String[0]))
                .build();
    }
}

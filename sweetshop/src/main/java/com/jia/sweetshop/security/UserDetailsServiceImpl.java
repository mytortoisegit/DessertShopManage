package com.jia.sweetshop.security;

import com.jia.sweetshop.mappers.SysMenuMapper;
import com.jia.sweetshop.mappers.SysUserMapper;
import com.jia.sweetshop.mappers.UserMapper;
import com.jia.sweetshop.model.bean.SysUser;
import com.jia.sweetshop.model.domain.LoginUser;
import com.jia.sweetshop.model.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 提供一个实现了 UserDetailsService 接口的类，
 * 用于从数据库或其他持久化存储中获取用户信息。
 * 这个类应该根据用户名返回一个 UserDetails 对象，包含用户名、密码和用户的权限信息。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysUser sysUser = sysUserMapper.findByUsername(username);

        if (Objects.isNull(sysUser)) {
            System.out.println("用户名不正确");
            throw new UsernameNotFoundException("用户名不正确: " + username);
        }
        // 查询用户菜单权限信息
        List<String> perms = sysMenuMapper.selectPermsByUserId(sysUser.getId());

        return new LoginUser(sysUser,perms);
    }
}

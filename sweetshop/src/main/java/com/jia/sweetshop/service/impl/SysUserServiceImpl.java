package com.jia.sweetshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.sweetshop.model.bean.SysUser;
import com.jia.sweetshop.mappers.SysUserMapper;
import com.jia.sweetshop.model.dto.SysUserDTO;
import com.jia.sweetshop.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenjunwen
 * @since 2024-05-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<SysUser> selectList(SysUserDTO sysUser) {
        Page<SysUser> page = new Page(sysUser.getPageNum(), sysUser.getPageSize());

        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();


        if(StringUtils.isNotBlank(sysUser.getUsername())){
            userQueryWrapper.like("username", sysUser.getUsername());
        }
        if(StringUtils.isNotBlank(sysUser.getPhone())){
            userQueryWrapper.eq("phone", sysUser.getPhone());
        }
        if (StringUtils.isNotBlank(sysUser.getUserType())){
            userQueryWrapper.eq("user_type", sysUser.getUserType());
        }
       return   sysUserMapper.selectPage(page, userQueryWrapper);
    }

    @Override
    public void saveUser(SysUser sysUser) {
        // 对用户密码加密
        String encode = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encode);
        sysUserMapper.insert(sysUser);
    }




    @Override
    public void del(SysUserDTO sysUser) {
        sysUserMapper.deleteById(sysUser.getId());
    }
}

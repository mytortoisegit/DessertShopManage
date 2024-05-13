package com.jia.sweetshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.sweetshop.model.bean.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jia.sweetshop.model.dto.SysUserDTO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chenjunwen
 * @since 2024-05-06
 */
public interface SysUserService extends IService<SysUser> {

    Page<SysUser> selectList(SysUserDTO sysUser);

    void saveUser(SysUser sysUser);


    void del(SysUserDTO sysUser);

}

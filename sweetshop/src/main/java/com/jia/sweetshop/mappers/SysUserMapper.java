package com.jia.sweetshop.mappers;

import com.jia.sweetshop.model.bean.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jia.sweetshop.model.po.UserPO;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chenjunwen
 * @since 2024-05-06
 */
public interface SysUserMapper extends BaseMapper<SysUser> {



    SysUser findByUsername(String username);
}

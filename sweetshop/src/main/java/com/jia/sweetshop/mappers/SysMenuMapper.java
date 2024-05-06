package com.jia.sweetshop.mappers;

import com.jia.sweetshop.model.bean.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限菜单表 Mapper 接口
 * </p>
 *
 * @author chenjunwen
 * @since 2024-05-06
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户id 查询用户有哪些菜单权限
     * 要求角色状态为正常状态
     *     菜单权限状态为正常状态
     * @param userId
     * @return
     */
    List<String> selectPermsByUserId(Long userId);
}

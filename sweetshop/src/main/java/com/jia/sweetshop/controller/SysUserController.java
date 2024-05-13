package com.jia.sweetshop.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.sweetshop.model.bean.SysUser;
import com.jia.sweetshop.model.domain.ApiResponse;
import com.jia.sweetshop.model.dto.SysUserDTO;
import com.jia.sweetshop.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author chenjunwen
 * @since 2024-05-06
 */
@RestController
@RequestMapping("api/sysUser")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    // 查询用户列表
    @GetMapping("list")
    public ApiResponse list(SysUserDTO sysUser) {
      Page<SysUser> list= sysUserService.selectList(sysUser);
        return ApiResponse.success(list);
    }

    // 新增用户
    @PostMapping("add")
    public ApiResponse add(@RequestBody SysUser sysUser) {
            sysUserService.saveUser(sysUser);
        return  ApiResponse.success(sysUser);
    }

    // 修改用户
    @PutMapping("update")
    public ApiResponse update(@RequestBody SysUser sysUser) {
            sysUserService.updateById(sysUser);
        return  ApiResponse.success(sysUser);
    }

    //删除用户
    @DeleteMapping("del")
    public ApiResponse delete(SysUserDTO sysUser) {
        sysUserService.del(sysUser);
        return  ApiResponse.success(sysUser);
    }
}


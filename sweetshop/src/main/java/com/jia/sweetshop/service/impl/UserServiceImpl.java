package com.jia.sweetshop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.sweetshop.mappers.UserMapper;
import com.jia.sweetshop.model.dto.UserDTO;
import com.jia.sweetshop.model.po.UserPO;
import com.jia.sweetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询用户信息
     * @param dto
     * @return
     */
    @Override
    public IPage<UserPO> getUserListWithPagination(UserDTO dto) {
        // 构造分页查询条件
        Page<UserPO> page= new Page<>(dto.getPageNum(), dto.getPageSize());
        // 执行查询，这里会自动进行分页
        return   userMapper.selectUserPage(page, dto);
    }
}

package com.jia.sweetshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.sweetshop.mapper.UserMapper;
import com.jia.sweetshop.model.po.UserPO;
import com.jia.sweetshop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
}

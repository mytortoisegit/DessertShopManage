package com.jia.sweetshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jia.sweetshop.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {


     UserPO findByUsername(String username);
}

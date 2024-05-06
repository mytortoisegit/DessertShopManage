package com.jia.sweetshop.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.sweetshop.model.dto.UserDTO;
import com.jia.sweetshop.model.po.UserPO;
import org.apache.ibatis.annotations.Param;


public interface UserMapper extends BaseMapper<UserPO> {


     UserPO findByUsername(String username);

     IPage<UserPO> selectUserPage(Page<UserPO> page, @Param("dto") UserDTO dto);

}

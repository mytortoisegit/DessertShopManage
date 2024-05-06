package com.jia.sweetshop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jia.sweetshop.model.dto.UserDTO;
import com.jia.sweetshop.model.po.UserPO;

public interface UserService extends IService<UserPO> {

    IPage<UserPO> getUserListWithPagination(UserDTO dto);
}

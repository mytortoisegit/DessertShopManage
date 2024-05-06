package com.jia.sweetshop.model.converter;

import com.jia.sweetshop.model.dto.UserDTO;
import com.jia.sweetshop.model.po.UserPO;
import com.jia.sweetshop.model.vo.UserVO;

public class UserConverter {

    // 将UserPO转换为UserDTO
    public static UserDTO poToDto(UserPO userPO) {
        if (userPO == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userPO.getId());
        userDTO.setUsername(userPO.getUserName());
        return userDTO;
    }

    // 将UserPO转换为UserVO
    public static UserVO poToVo(UserPO userPO) {
        if (userPO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setUserName(userPO.getUserName());
        userVO.setEmail(userPO.getEmail());
        return userVO;
    }

    // 将UserVO转换为UserDTO（如果需要的话）
    public static UserDTO voToDto(UserVO userVO) {
        if (userVO == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        // 可以根据需要进行其他属性的映射
        userDTO.setUsername(userVO.getUserName());
        return userDTO;
    }
}

package com.jia.sweetshop.model.dto;

import lombok.Data;


@Data
public class SysUserDTO extends BaseDTO{

    private Integer id;

    private String userType;

    private String username;

    private String phone;

}

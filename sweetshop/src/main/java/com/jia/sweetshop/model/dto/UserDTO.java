package com.jia.sweetshop.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data

public class UserDTO  extends BaseDTO{
    private Integer id;

    private String username;
    private String email;
    private String password;

    /**
     * 职位
     */
    private String position;
    /**
     * 薪资
     */
    private String salary;

    private Date createTime;

    private Date updateTime;
    @TableField("roles_id")
    private String  rolesId ;

    private ArrayList<String> role;

}

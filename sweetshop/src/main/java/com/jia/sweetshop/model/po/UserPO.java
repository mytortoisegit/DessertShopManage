package com.jia.sweetshop.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class UserPO {

    @TableField("id")
    private Integer id;
    @TableField("user_name")
    private String userName;
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

}

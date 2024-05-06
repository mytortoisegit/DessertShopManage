package com.jia.sweetshop.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_user")
public class UserPO implements Serializable {

    private static final long serialVersionUID = -10356785423868312L;

    @TableId(value = "id", type = IdType.AUTO)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

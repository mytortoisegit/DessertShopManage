package com.jia.sweetshop.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品实体
 */
@Data
public class Good  implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;
    /**
     * 描述
     */
    private String region;
    /**
     * 价格
     */
    private String price;
    /**
     * 分类
     */
    private String cate;
    /**
     * 是否热门
     */
    private Boolean hot;
    /**
     * 图片
     */
    private String img;
    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标记（0正常 1删除）
     */
    private Integer delFlag;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}

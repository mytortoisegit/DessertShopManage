package com.jia.sweetshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.sweetshop.model.bean.Good;
import com.jia.sweetshop.model.dto.GoodDTO;

import java.util.List;

public interface GoodService {
    /**
     * 新增商品
     * @param info
     */
    void insert(Good info);

    Page<Good> selectList(GoodDTO req);

    public Good selectById(GoodDTO info);

    void update(Good info);

    void delById(GoodDTO req);
}

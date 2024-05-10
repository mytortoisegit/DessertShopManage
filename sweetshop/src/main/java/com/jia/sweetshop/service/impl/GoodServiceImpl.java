package com.jia.sweetshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.sweetshop.mappers.GoodMapper;
import com.jia.sweetshop.model.bean.Good;
import com.jia.sweetshop.model.dto.GoodDTO;
import com.jia.sweetshop.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl  implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Override
    public void insert(Good info) {
        goodMapper.insert(info);
    }


    @Override
    public Good selectById(GoodDTO info) {
        return goodMapper.selectById(info.getId());
    }

    @Override
    public void update(Good info) {
        goodMapper.updateById(info);
    }

    @Override
    public void delById(GoodDTO req) {
        goodMapper.deleteById(req.getId());
    }

    @Override
    public Page<Good> selectList(GoodDTO req) {
        // 构造分页查询条件
            Page<Good> page= new Page<>(req.getPageNum(), req.getPageSize());
             // 创建查询条件构造器
             QueryWrapper<Good> queryWrapper = new QueryWrapper<>();
        // 如果有关键字参数，设置模糊查询条件
        if (StringUtils.isNotBlank(req.getName())) {
            queryWrapper.like("name", req.getName());
        }
        if(StringUtils.isNotBlank(req.getCate())){
            queryWrapper.eq("cate", req.getCate());
        }
       return  goodMapper.selectPage(page, queryWrapper);
    }


}

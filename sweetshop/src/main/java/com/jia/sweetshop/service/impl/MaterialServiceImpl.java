package com.jia.sweetshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.sweetshop.mapper.MaterialMapper;
import com.jia.sweetshop.model.po.Material;
import com.jia.sweetshop.service.MaterialService;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {
    // 可以在这里扩展业务逻辑
}

package com.jia.sweetshop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.sweetshop.model.bean.Good;
import com.jia.sweetshop.model.domain.ApiResponse;
import com.jia.sweetshop.model.dto.GoodDTO;
import com.jia.sweetshop.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/good")
public class GoodsController {

    @Autowired
    private GoodService goodService;

    @GetMapping("list")
    public ApiResponse list(GoodDTO req){
        Page<Good> page= goodService.selectList(req);
        return ApiResponse.success(page);
    }


    @GetMapping("info")
    public ApiResponse info(GoodDTO req){
          Good good= goodService.selectById(req);
        return ApiResponse.success(good);
    }


    @PostMapping("del")
    public ApiResponse del(GoodDTO req){
         goodService.delById(req);
        return ApiResponse.success();
    }

    @PostMapping("update")
    public ApiResponse updateGood(@RequestBody Good info){
        HashMap<String, Object> res = new HashMap<String, Object>();

        if(info.getId() == null){
            goodService.insert(info);
        }else{
            goodService.update(info);
        }
        res.put("info", info);
        return ApiResponse.success(res);
    }
}

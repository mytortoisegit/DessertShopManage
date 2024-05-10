package com.jia.sweetshop.controller;

import com.jia.sweetshop.model.domain.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController()
@RequestMapping("/api/cate")
public class CateController {

    /**
     *
     * @return
     */
    @GetMapping("all")
    public ApiResponse getAllCates(){
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        extracted(list);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        return ApiResponse.success(map);
    }

    // 假数据
    private static void extracted(ArrayList<HashMap<String, Object>> list) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 6512552);
        map.put("cate","hot");
        map.put("cate_zh","热门商品");
        map.put("pid","0");

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("id", 7512552);
        map1.put("cate","mob");
        map1.put("cate_zh","手机数码");
        map1.put("pid","0");
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id", 8512552);
        map2.put("cate","jia");
        map2.put("cate_zh","家用电器");
        map2.put("pid","0");
        list.add(map);
        list.add(map1);
        list.add(map2);
    }
}

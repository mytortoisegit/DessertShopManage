package com.jia.sweetshop.controller;

import com.jia.sweetshop.model.domain.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 文件上传
 */
@RestController
@RequestMapping("api/upload")
public class UploadController {

    @PostMapping("img")
    public ApiResponse upload(MultipartFile good) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("img", "images/sunxinzhe.jpg");
        System.out.println(good);
        return ApiResponse.success(map);
    }
}

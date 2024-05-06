package com.jia.sweetshop;

import com.jia.sweetshop.mappers.SysMenuMapper;
import com.jia.sweetshop.mappers.UserMapper;
import com.jia.sweetshop.model.po.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SweetshopApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    void contextLoads() {
        List<UserPO> userPOS = userMapper.selectList(null);
        System.out.println(userPOS);
    }


    @Test
    void sysMenu() {
        List<String> strings = sysMenuMapper.selectPermsByUserId(1L);
        System.out.println(strings.size());
    }

}

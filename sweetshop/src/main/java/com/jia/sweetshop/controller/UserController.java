package com.jia.sweetshop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jia.sweetshop.model.dto.UserDTO;
import com.jia.sweetshop.model.po.UserPO;
import com.jia.sweetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
//    @ResponseBody
    public String listUsers(Model model, UserDTO dto) {
        IPage<UserPO> page = userService.getUserListWithPagination(dto);
        System.out.println(page.getTotal());
        model.addAttribute("users", page.getRecords());
//        return JSONObject.toJSONString(page);
        return "user/list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new UserPO());
        return "user/form";
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserPO userPO) {
        System.out.println(userPO);
        userService.save(userPO);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        UserPO userPO = userService.getById(id);
        model.addAttribute("user", userPO);
        return "user/form";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, @ModelAttribute UserPO userPO) {
        userPO.setId(id);
        userService.updateById(userPO);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeById(id);
        return "redirect:/users";
    }
}

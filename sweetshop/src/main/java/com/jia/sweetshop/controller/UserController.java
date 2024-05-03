package com.jia.sweetshop.controller;

import com.jia.sweetshop.model.po.UserPO;
import com.jia.sweetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<UserPO> userPOS = userService.list();
        model.addAttribute("users", userPOS);
        return "user/list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new UserPO());
        return "user/form";
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserPO userPO) {
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

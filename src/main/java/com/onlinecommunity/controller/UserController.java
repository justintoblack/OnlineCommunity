package com.onlinecommunity.controller;

import com.onlinecommunity.pojo.User;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result register(@Validated User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@Validated User user) {
        return userService.login(user);
    }
}

package com.onlinecommunity.controller;

import com.onlinecommunity.pojo.User;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
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

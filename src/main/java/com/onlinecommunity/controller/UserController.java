package com.onlinecommunity.controller;

import com.onlinecommunity.pojo.User;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import com.onlinecommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        return userService.register(username, password);
    }

    @PostMapping("/login")
    public Result login(String username, String password){
        return userService.login(username, password);
    }
}

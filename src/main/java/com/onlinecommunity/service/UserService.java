package com.onlinecommunity.service;

import com.onlinecommunity.mapper.UserMapper;
import com.onlinecommunity.pojo.User;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import com.onlinecommunity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;



    /**
     *
     * @param user 要注册的封装的User对象
     * @return Result
     */
    public Result register(User user) {
        if ("".equals(user.getUsername())) {
            return Result.failure(ResultCode.NULL_USERNAME);
        }
        if ("".equals(user.getPassword())) {
            return Result.failure(ResultCode.NULL_PASSWORD);
        }

        Integer uid = userMapper.getUidByUsername(user.getUsername());
        if (uid != null) {
            return Result.failure(ResultCode.EXIST_USERNAME);
        }
        userMapper.saveUser(user);
        return Result.success();
    }

    /**
     *
     * @param user 要登录的封装的User对象
     * @return Result
     */
    public Result login(User user) {
        Integer uid = userMapper.getUidByUsername(user.getUsername());
        if (uid != null) {
            String existUserPwd = userMapper.getPasswordByUid(uid);
            if (existUserPwd.equals(user.getPassword())) {
                String token = JwtUtil.sign(user.getUsername());
                return Result.success(token);
            } else {
                return Result.failure(ResultCode.WRONG_PASSWORD);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_USERNAME);
        }
    }
}

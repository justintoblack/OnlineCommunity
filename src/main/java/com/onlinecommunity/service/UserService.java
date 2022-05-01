package com.onlinecommunity.service;

import com.onlinecommunity.mapper.UserMapper;
import com.onlinecommunity.pojo.User;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * @param username
     * @param password
     * @return
     */
    public Result register(String username, String password) {
        if ("".equals(username)) {
            return Result.failure(ResultCode.NULL_USERNAME);
        }
        if ("".equals(password)) {
            return Result.failure(ResultCode.NULL_PASSWORD);
        }

        Integer uid = userMapper.findUidByUsername(username);
        if (uid != null) {
            return Result.failure(ResultCode.ALREADY_REGISTERED);
        }
        return Result.success();
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Result login(String username, String password) {
        Integer uid = userMapper.findUidByUsername(username);
        if (uid != null) {
            String existUserPwd = userMapper.findPasswordByUid(uid);
            if (existUserPwd.equals(password)) {
                return Result.success();
            } else {
                return Result.failure(ResultCode.WRONG_PASSWORD);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_USERNAME);
        }
    }
}

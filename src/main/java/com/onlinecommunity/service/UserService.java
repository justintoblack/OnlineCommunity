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
     *
     * @param user
     * @return
     */
    public Result register(User user) {
        if ("".equals(user.getUsername())) {
            return Result.failure(ResultCode.NULL_USERNAME);
        }
        if ("".equals(user.getPassword())) {
            return Result.failure(ResultCode.NULL_PASSWORD);
        }

        Integer uid = userMapper.findUidByUsername(user.getUsername());
        if (uid != null) {
            return Result.failure(ResultCode.ALREADY_REGISTERED);
        }
        userMapper.saveUser(user);
        return Result.success();
    }

    /**
     *
     * @param user
     * @return
     */
    public Result login(User user) {
        Integer uid = userMapper.findUidByUsername(user.getUsername());
        if (uid != null) {
            String existUserPwd = userMapper.findPasswordByUid(uid);
            if (existUserPwd.equals(user.getPassword())) {
                return Result.success();
            } else {
                return Result.failure(ResultCode.WRONG_PASSWORD);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_USERNAME);
        }
    }
}

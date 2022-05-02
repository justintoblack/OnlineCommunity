package com.onlinecommunity.service;

import com.onlinecommunity.mapper.MomentMapper;
import com.onlinecommunity.mapper.UserMapper;
import com.onlinecommunity.pojo.Moment;
import com.onlinecommunity.pojo.User;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MomentService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MomentMapper momentMapper;

    public Result post(Moment moment) {
        Integer uid = moment.getUid();
        User user = userMapper.findUserByUid(uid);
        if (user != null) {
            if (moment.getMid() == null) {
                momentMapper.saveMoment(moment);
                return Result.success();
            } else {
                return Result.failure(ResultCode.EXIST_MID);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_UID);
        }
    }

}

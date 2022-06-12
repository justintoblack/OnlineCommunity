package com.onlinecommunity;

import com.onlinecommunity.mapper.UserInfoMapper;
import com.onlinecommunity.mapper.UserMapper;
import com.onlinecommunity.pojo.User;
import com.onlinecommunity.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @create 2022/4/22-21:00
 */

@SpringBootTest
@Slf4j
public class UserInfoMapperTest {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void testListAll(){
        List<UserInfo> users = userInfoMapper.getAllUserInfo();
        for(UserInfo user : users){
            System.out.println(user.toString());
        }
    }



    @Test
    public void testFindUser(){

        UserInfo user1 = userInfoMapper.getUserInfoByUid(1);
        System.out.println("findUserByUid User1: "+ user1);

        UserInfo user2 = userInfoMapper.getUserInfoByUsername(user1.getUsername());
        System.out.println("findUserByUid User1: "+ user2);

        Integer count = userInfoMapper.getUserInfoCount();
        System.out.println("UserInfoCount: " + count);
    }
}

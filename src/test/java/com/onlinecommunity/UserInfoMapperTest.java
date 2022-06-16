package com.onlinecommunity;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlinecommunity.mapper.UserInfoMapper;
import com.onlinecommunity.mapper.UserMapper;
import com.onlinecommunity.pojo.Page;
import com.onlinecommunity.pojo.User;
import com.onlinecommunity.pojo.UserInfo;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.util.List;

/**
 * @create 2022/4/22-21:00
 */

@SpringBootTest
@Slf4j
public class UserInfoMapperTest {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserService userService;

    @Test
    public void testListAll(){
        PageHelper.startPage(2,2);
        List<UserInfo> users = userInfoMapper.getAllUserInfo(1);
        PageInfo<UserInfo> pageInfo=new PageInfo<UserInfo>(users,3);
        System.out.println(pageInfo);

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

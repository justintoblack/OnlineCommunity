package com.onlinecommunity;

import com.onlinecommunity.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.onlinecommunity.mapper.UserMapper;

import java.util.List;

/**
 * @create 2022/4/22-21:00
 */

@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testListAll(){
        List<User> users = userMapper.getAllUser();
        for(User user : users){
            System.out.println(user.toString());
        }
    }

    @Test
    public void testSetPassword(){

        User user = new User();
        user.setUid(1);
        user.setPassword("123456--");
        userMapper.setPassword(user);
        System.out.println(userMapper.getPasswordByUid(user.getUid()));

    }

    @Test
    public void testFindUser(){

        User user1 = userMapper.getUserByUid(1);
        System.out.println("findUserByUid User1: "+ user1);

        User user2 = userMapper.getUserByUsername(user1.getUsername());
        System.out.println("findUserByUsername User2: " + user2);
    }
}

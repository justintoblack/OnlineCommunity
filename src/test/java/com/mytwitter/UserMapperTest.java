package com.mytwitter;

import com.mytwitter.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.mytwitter.mapper.UserMapper;

import java.util.List;

/**
 * @create 2022/4/22-21:00
 */

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testListAll(){
        List<User> users = userMapper.listAll();
        for(User user : users){
            System.out.println(user.toString());
        }
    }
}

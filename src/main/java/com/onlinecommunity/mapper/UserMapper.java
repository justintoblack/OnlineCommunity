package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {


    List<User> listAll();

    User findUserByUid(Integer uid);

    User findUserByUsername(String username);

    Integer saveUser(@Param("user") User user);

    String findUsernameByUid(Integer uid);

    String findPasswordByUid(Integer uid);

    String findPasswordByUsername(String username);

    Integer findUidByUsername(String username);

    void setPassword(@Param("user") User user);
}

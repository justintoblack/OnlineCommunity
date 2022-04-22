package com.mytwitter.mapper;


import com.mytwitter.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {


    List<User> listAll();

}

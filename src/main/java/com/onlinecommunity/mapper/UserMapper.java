package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


    List<User> listAll();

}

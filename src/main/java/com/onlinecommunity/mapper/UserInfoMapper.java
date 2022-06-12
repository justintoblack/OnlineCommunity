package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    List<UserInfo> getAllUserInfo();

    UserInfo getUserInfoByUid(Integer uid);

    UserInfo getUserInfoByUsername(String username);

    Integer saveUserInfo(@Param("userInfo") UserInfo userInfo);

    Integer getUserInfoCount();

    Integer updateUserInfo(@Param("userInfo") UserInfo userInfo);
}

package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    List<UserInfo> getAllUserInfo();

    UserInfo getUserInfoByUid(Integer uid);

    UserInfo getUserInfoByUsername(String username);

    Integer saveUserInfo(@Param("userInfo") UserInfo userInfo);

    Integer getUserInfoCount();

    Integer updateUserInfo(@Param("userInfo") UserInfo userInfo);

    @Select("SELECT * FROM online_community.user_info where INSTR(username, #{str})>0 and uid != #{uid}")
    List<UserInfo> getUserInfoBySearch(@Param("uid") Integer uid, @Param("str") String str);
}

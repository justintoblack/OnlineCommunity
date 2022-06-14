package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.Following;
import com.onlinecommunity.pojo.Like;
import com.onlinecommunity.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FollowingMapper {

    @Select("select * from online_community.user_info as a " +
            "left join online_community.following as b on a.uid = b.uid " +
            "where b.following_uid = #{followingUid}")
    List<UserInfo> getAllFollowersByUid(Integer followingUid);

    @Select("select * from online_community.user_info as a " +
            "left join online_community.following as b on a.uid = b.following_uid  " +
            "where b.uid = #{uid}")
    List<UserInfo> getAllFollowingByUid(Integer uid);

    @Insert("insert into online_community.following (uid, following_uid) " +
            "values (#{uid}, #{followingUid})")
    Integer saveFollowing(@Param("uid") Integer uid, @Param("followingUid") Integer followingUid);

    @Delete("delete from  online_community.following  " +
            "where uid = #{uid} and following_uid = #{followingUid}")
    Integer deleteFollowing(@Param("uid") Integer uid, @Param("followingUid") Integer followingUid);

}

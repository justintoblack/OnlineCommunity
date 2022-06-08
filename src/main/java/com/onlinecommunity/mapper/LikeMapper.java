package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.Like;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {

    @Select("select * from online_community.like where like_uid = #{likeUid}")
    List<Like> getAllLikesByUid(Integer likeUid);

    @Select("select * from online_community.like where like_id = #{likeId}")
    Like getOneLikeByLikeId(Integer likeId);

    @Insert("insert into online_community.like (like_id, moment_id, like_uid, moment_uid, like_time) " +
            "values (#{like.likeId}, #{like.momentId}, #{like.likeUid}, #{like.momentUid}, #{like.likeTime})")
    @Options(useGeneratedKeys = true, keyProperty = "likeId")
    Integer saveLike(@Param("like") Like like);

    @Delete("delete from online_community.like where like_id = #{like.likeId}  ")
    Integer deleteLikeByLikeId(@Param("like") Like like);

    @Select("select * from online_community.like where like_uid=#{likeUid} and moment_id = #{momentId}")
    Like getOneLikeByLikeUidMomentId(@Param("likeUid") Integer likeUid, @Param("momentId") Integer momentId);
}

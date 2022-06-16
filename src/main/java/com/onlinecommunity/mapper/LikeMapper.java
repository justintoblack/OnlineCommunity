package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.Like;
import com.onlinecommunity.pojo.Moment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {

    @Select("select * from online_community.moment as a left join online_community.`like` as b " +
            "on a.moment_id = b.moment_id " +
            "where b.like_uid = #{likeUid}")
    List<Moment> getAllLikesByUid(Integer likeUid);

    @Select("select like_id as likeId, moment_id as momentId, like_uid as likeUid, moment_uid as momentUid, like_time as likeTime " +
            "from online_community.like where like_id = #{likeId}")
    Like getOneLikeByLikeId(Integer likeId);

    @Insert("insert into online_community.like (like_id, moment_id, like_uid, moment_uid, like_time) " +
            "values (#{like.likeId}, #{like.momentId}, #{like.likeUid}, #{like.momentUid}, #{like.likeTime})")
    @Options(useGeneratedKeys = true, keyProperty = "likeId")
    Integer saveLike(@Param("like") Like like);

    @Delete("delete from online_community.like where like_id = #{likeId}  ")
    Integer deleteLikeByLikeId(Integer likeId);

    @Select("select like_id as likeId, moment_id as momentId, like_uid as likeUid, moment_uid as momentUid, like_time as likeTime " +
            "  from online_community.like where like_uid=#{likeUid} and moment_id = #{momentId}")
    Like getOneLikeByLikeUidMomentId(@Param("likeUid") Integer likeUid, @Param("momentId") Integer momentId);
}

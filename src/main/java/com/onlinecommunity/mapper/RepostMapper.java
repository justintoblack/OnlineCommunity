package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.Repost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RepostMapper {

    @Select("select * from online_community.repost where repost_uid=${repostUid}")
    List<Repost> getAllRepostsByUid(Integer repostUid);

    @Select("select * from online_community.repost where repost_id=${repostId}")
    Repost getOneRepostByRepostId(Integer repostId);

    @Insert("insert into online_community.repost (repost_id, moment_id, repost_uid, moment_uid, repost_time) " +
            "values (#{repost.repostId}, #{repost.momentId}, #{repost.repostUid}, #{repost.momentUid}, #{repost.repostTime})")
    @Options(useGeneratedKeys = true, keyProperty = "repostId")
    Integer saveRepost(@Param("repost") Repost repost);

    @Delete("delete from online_community.repost where repost_id = #{repostId}")
    Integer deleteRepostByRepostId(Integer repostId);


}

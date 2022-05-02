package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.Like;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {

    @Select("select * from online_community.like where luid = #{luid}")
    List<Like> getAllLikesByLuid(Integer luid);

    @Select("select * from online_community.like where lid = #{lid}")
    Like getOneLikeByLid(Integer lid);

    @Insert("insert into online_community.like (lid, mid, luid, muid) values (#{like.lid}, #{like.mid}, #{like.luid}, #{like.muid})")
    @Options(keyProperty = "lid")
    Integer likeMoment(@Param("like") Like like);

    @Delete("delete from online_community.like where lid = #{like.lid}  ")
    Integer unlikeMoment(@Param("like") Like like);

    @Select("select * from online_community.like where luid=#{luid} and mid = #{mid}")
    Like getOneLikeByLuidMid(@Param("luid") Integer luid, @Param("mid") Integer mid);
}

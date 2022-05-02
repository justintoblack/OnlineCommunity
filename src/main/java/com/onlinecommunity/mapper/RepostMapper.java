package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.Repost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RepostMapper {

    @Select("select * from online_community.repost where ruid=${ruid}")
    List<Repost> getAllRespostsByRuid(Integer ruid);

    @Select("select * from online_community.repost where rid=${rid}")
    Repost getOneRepostByRid(Integer rid);

    @Insert("insert into online_community.repost (rid, mid, ruid, muid) values (#{repost.rid}, #{repost.mid}, #{repost.ruid}, #{repost.muid})")
    @Options(keyProperty = "rid")
    Integer repost(@Param("repost") Repost repost);

    @Delete("delete from online_community.repost where rid = #{rid}")
    Integer deleteRepostByRid(Integer rid);


}

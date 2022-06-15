package com.onlinecommunity.mapper;





import com.onlinecommunity.pojo.Star;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StarMapper {

    @Select("select star_id , moment_id , star_uid , moment_uid , star_time  " +
            "from online_community.star where star_uid = #{starUid}")
    List<Star> getAllStarsByUid(Integer starUid);

    @Select("select star_id , moment_id , star_uid , moment_uid , star_time " +
            "from online_community.star where star_id = #{starId}")
    Star getOneStarByStarId(Integer starId);

    @Insert("insert into online_community.star (star_id, moment_id, star_uid, moment_uid, star_time) " +
            "values (#{star.starId}, #{star.momentId}, #{star.starUid}, #{star.momentUid}, #{star.starTime})")
    @Options(useGeneratedKeys = true, keyProperty = "starId")
    Integer saveStar(@Param("star") Star star);

    @Delete("delete from online_community.star where star_id = #{star.starId}  ")
    Integer deleteStarByStarId(@Param("star") Star star);

    @Select("select star_id , moment_id , star_uid , moment_uid , star_time " +
            "  from online_community.star where star_uid=#{starUid} and moment_id = #{momentId}")
    Star getOneStarByStarUidMomentId(@Param("starUid") Integer starUid, @Param("momentId") Integer momentId);
}

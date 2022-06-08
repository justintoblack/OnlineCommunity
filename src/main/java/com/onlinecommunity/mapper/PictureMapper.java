package com.onlinecommunity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @create 2022/5/30-14:39
 */

@Mapper
public interface PictureMapper {

    List<String> getPicUrlsByMomentId(Integer momentId);

    /**
     *
     * @param urlList 图片URL列表
     * @param momentId 动态ID
     * @return 插入的条数
     */
    Integer insertPicturesUrl(@Param("urlList") List<String> urlList, @Param("momentId") Integer momentId);

    Integer updatePicturesUrl(@Param("urlList") List<String> urlList, @Param("momentId") Integer momentId);

}

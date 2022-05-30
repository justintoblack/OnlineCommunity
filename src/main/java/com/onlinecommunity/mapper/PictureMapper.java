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

    List<String> getPicUrlsByMid(Integer mid);

    Integer savePicturesUrl(@Param("urlList") List<String> urlList, @Param("mid") Integer mid);
}

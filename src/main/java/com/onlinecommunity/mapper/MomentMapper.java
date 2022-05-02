package com.onlinecommunity.mapper;

import com.onlinecommunity.pojo.Moment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MomentMapper {
    List<Moment> findAllMomentByUid(Integer uid);

    Moment findOneMomentByMid(Integer mid);

    Integer saveMoment(@Param("moment") Moment moment);

    Integer deleteMomentByMid(Integer mid);

    Integer getMomentCountByUid(Integer uid);


}

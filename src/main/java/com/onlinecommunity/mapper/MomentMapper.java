package com.onlinecommunity.mapper;

import com.onlinecommunity.pojo.Moment;
import com.onlinecommunity.pojo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MomentMapper {

    List<Moment> listAllMoments();

    List<Moment> getAllMomentsByUid(Integer uid);

    List<Moment> getActiveMomentsByPage(@Param("page") Page page);

    Moment getOneMomentByMomentId(Integer momentId);

    Integer saveMoment(@Param("moment") Moment moment);

    Integer deleteMomentByMomentId(Integer momentId);

    Integer getActiveMomentCountByUid(Integer uid);

    /**
     *
     * @param uid 用户ID
     * @return 返回该用户发过的动态数量（包括已经标记为删除的动态）
     */
    Integer getAllMomentCountByUid(Integer uid);

    Integer getAllMomentCount();

}

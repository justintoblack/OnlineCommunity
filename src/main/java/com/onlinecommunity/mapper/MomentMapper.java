package com.onlinecommunity.mapper;

import com.onlinecommunity.pojo.Moment;
import com.onlinecommunity.pojo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MomentMapper {

    List<Moment> listAllMoments();

    List<Moment> getAllMomentsByUid(Integer uid);

    /**
     *
     * @param page 分页对象，lastId, pageSize
     * @param uid 用户ID
     * @return 返回用户自己的动态列表
     */
    List<Moment> getActiveSelfMomentsByPage(@Param("page") Page page, @Param("uid") Integer uid);

    /**
     *
     * @param page 分页对象，lastId, pageSize
     * @param uid 用户ID
     * @return 返回用户主页的动态列表，即包含用户关注的人与用户自己的动态
     */
    List<Moment> getActiveHomeMomentsByPage(@Param("page") Page page, @Param("uid") Integer uid);

    Moment getOneMomentByMomentId(Integer momentId);

    Integer saveMoment(@Param("moment") Moment moment);

    Integer updateMoment(@Param("moment") Moment moment);

    Integer deleteMomentByMomentId(Integer momentId);

    Integer getActiveMomentCountByUid(Integer uid);

    @Select("SELECT * FROM online_community.moment where INSTR(moment.content, #{str})>0 ")
    List<Moment> getMomentBySearch(String str);
    /**
     *
     * @param uid 用户ID
     * @return 返回该用户发过的动态数量（包括已经标记为删除的动态）
     */
    Integer getAllMomentCountByUid(Integer uid);

    Integer getAllMomentCount();

}

package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.Comment;
import com.onlinecommunity.pojo.Like;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from online_community.comment where cuid = #{cuid}")
    List<Comment> getAllCommentsByCuid(Integer cuid);

    @Select("select * from online_community.comment where cid = #{cid}")
    Comment getOneCommentByCid(Integer cid);

    @Insert("insert into online_community.comment (cid, ccontent, mid, cuid, muid, clikecount) " +
            "values(#{comment.cid}, #{comment.ccontent},#{comment.mid},#{comment.cuid}, #{comment.muid}, #{comment.clikecount})")
    @Options(useGeneratedKeys = true, keyProperty = "cid")
    Integer comment(@Param("comment") Comment comment);

    @Delete("delete from online_community.comment where cid=${cid}")
    Integer deleteCommentByCid(Integer cid);

    @Update("update online_community.comment set ccontent=#{comment.ccontent},mid=#{comment.mid},cuid=#{comment.cuid}, " +
            "muid=#{comment.muid},clikecount=#{comment.clikecount} " +
            "where cid = #{comment.cid} ")
    Integer updateComment(@Param("comment") Comment comment);

    @Update("update online_community.comment set clikecount = #{comment.clikecount} where cid = #{comment.cid}")
    void updateCommentLikeCount(@Param("comment") Comment comment);

}

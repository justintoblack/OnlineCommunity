package com.onlinecommunity.mapper;


import com.onlinecommunity.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from online_community.comment where moment_id = #{momentId}")
    List<Comment> getAllCommentsByMid(Integer momentId);

    @Select("select * from online_community.comment where comment_id = #{commentId}")
    Comment getOneCommentByCommentId(Integer commentId);

    @Select("select * " +
            "  from online_community.comment where comment_uid=#{commentUid} and moment_id = #{momentId}")
    Comment getOneCommentByCommentUidMomentId(@Param("commentUid") Integer commentUid, @Param("momentId") Integer momentId);

    @Insert("insert into online_community.comment (comment_id, content, moment_id, comment_uid, moment_uid, like_count, username, avatar_url, comment_time) " +
            "values(#{comment.commentId}, #{comment.content},#{comment.momentId},#{comment.commentUid}, #{comment.momentUid}, #{comment.likeCount}, #{comment.username}, #{comment.avatarUrl}, #{comment.commentTime})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId")
    Integer comment(@Param("comment") Comment comment);

    @Delete("delete from online_community.comment where comment_id=${comment_id}")
    Integer deleteCommentByCid(Integer comment_id);

    @Update("update online_community.comment set content=#{comment.content},moment_id=#{comment.momentId},comment_uid=#{comment.commentUid}, " +
            "moment_uid=#{comment.momentUid},like_count=#{comment.likeCount}, username=#{comment.username}, avatar_url=#{comment.avatarUrl}, comment_time=#{comment.commentTime} " +
            "where comment_id = #{comment.commentId} ")
    Integer updateComment(@Param("comment") Comment comment);

    @Update("update online_community.comment set like_count = #{comment.likeCount} where comment_id = #{comment.commentId}")
    void updateCommentLikeCount(@Param("comment") Comment comment);

}

package com.onlinecommunity.pojo;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:17
 */
public class Comment {
    private Integer commentId;
    @NotBlank(message = "评论内容不能为空")
    private String content;
    private Integer momentId;
    private Integer commentUid;
    private Integer momentUid;
    private Integer likeCount = 0;
    private Timestamp commentTime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getCommentUid() {
        return commentUid;
    }

    public void setCommentUid(Integer commentUid) {
        this.commentUid = commentUid;
    }

    public Integer getMomentUid() {
        return momentUid;
    }

    public void setMomentUid(Integer momentUid) {
        this.momentUid = momentUid;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", momentId=" + momentId +
                ", commentUid=" + commentUid +
                ", momentUid=" + momentUid +
                ", likeCount=" + likeCount +
                ", commentTime=" + commentTime +
                '}';
    }
}

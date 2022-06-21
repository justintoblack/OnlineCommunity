package com.onlinecommunity.pojo;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * @create 2022/4/22-22:36
 */
public class Moment {
    private Integer momentId;

    @NotNull(message = "uid不能为空")
    @Digits(integer = 10, fraction = 0, message = "uid最多10位")
    @Max(value = 2147483647, message = "uid最大为2147483647")
    @Positive(message = "uid非负")
    private Integer uid;

    @NotBlank(message = "正文内容不能为空")
    private String content;

    private Timestamp momentTime;
    private Boolean isActive = true;
    private Timestamp deletedAt = null;
    private Boolean isStar = false;
    private Boolean isLike = false;
    private Boolean isRepost = false;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getStar() {
        return isStar;
    }

    public void setStar(Boolean star) {
        isStar = star;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public Boolean getRepost() {
        return isRepost;
    }

    public void setRepost(Boolean repost) {
        isRepost = repost;
    }

    private Integer pictureCount = 0;
    private Integer likeCount = 0;
    private Integer commentCount = 0;
    private Integer repostCount = 0;

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getMomentTime() {
        return momentTime;
    }

    public void setMomentTime(Timestamp momentTime) {
        this.momentTime = momentTime;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getPictureCount() {
        return pictureCount;
    }

    public void setPictureCount(Integer pictureCount) {
        this.pictureCount = pictureCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getRepostCount() {
        return repostCount;
    }

    public void setRepostCount(Integer repostCount) {
        this.repostCount = repostCount;
    }


    @Override
    public String toString() {
        return "Moment{" +
                "momentId=" + momentId +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", momentTime=" + momentTime +
                ", isActive=" + isActive +
                ", deletedAt=" + deletedAt +
                ", isStar=" + isStar +
                ", isLike=" + isLike +
                ", isRepost=" + isRepost +
                ", pictureCount=" + pictureCount +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", repostCount=" + repostCount +
                '}';
    }
}
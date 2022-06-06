package com.onlinecommunity.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:18
 */
public class Like {
    private Integer likeId;
    private Integer momentId;
    private Integer likeUid;
    private Integer momentUid;
    private Timestamp likeTime;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getLikeUid() {
        return likeUid;
    }

    public void setLikeUid(Integer likeUid) {
        this.likeUid = likeUid;
    }

    public Integer getMomentUid() {
        return momentUid;
    }

    public void setMomentUid(Integer momentUid) {
        this.momentUid = momentUid;
    }

    public Timestamp getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Timestamp likeTime) {
        this.likeTime = likeTime;
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", momentId=" + momentId +
                ", likeUid=" + likeUid +
                ", momentUid=" + momentUid +
                ", likeTime=" + likeTime +
                '}';
    }
}

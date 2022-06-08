package com.onlinecommunity.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:18
 */
public class Repost {
    private Integer repostId;
    private Integer momentId;
    private Integer repostUid;
    private Integer momentUid;
    private Timestamp repostTime;

    public Integer getRepostId() {
        return repostId;
    }

    public void setRepostId(Integer repostId) {
        this.repostId = repostId;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getRepostUid() {
        return repostUid;
    }

    public void setRepostUid(Integer repostUid) {
        this.repostUid = repostUid;
    }

    public Integer getMomentUid() {
        return momentUid;
    }

    public void setMomentUid(Integer momentUid) {
        this.momentUid = momentUid;
    }

    public Timestamp getRepostTime() {
        return repostTime;
    }

    public void setRepostTime(Timestamp repostTime) {
        this.repostTime = repostTime;
    }

    @Override
    public String toString() {
        return "Repost{" +
                "repostId=" + repostId +
                ", momentId=" + momentId +
                ", repostUid=" + repostUid +
                ", momentUid=" + momentUid +
                ", repostTime=" + repostTime +
                '}';
    }
}

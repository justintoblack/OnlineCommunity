package com.onlinecommunity.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:18
 */
public class Star {

    private Integer starId;
    private Integer momentId;
    private Integer starUid;
    private Timestamp starTime;

    public Integer getStarId() {
        return starId;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getStarUid() {
        return starUid;
    }

    public void setStarUid(Integer starUid) {
        this.starUid = starUid;
    }

    public Timestamp getStarTime() {
        return starTime;
    }

    public void setStarTime(Timestamp starTime) {
        this.starTime = starTime;
    }

    @Override
    public String toString() {
        return "Star{" +
                "starId=" + starId +
                ", momentId=" + momentId +
                ", starUid=" + starUid +
                ", starTime=" + starTime +
                '}';
    }
}

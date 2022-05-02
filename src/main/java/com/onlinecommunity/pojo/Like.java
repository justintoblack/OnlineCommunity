package com.onlinecommunity.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:18
 */
public class Like {
    private Integer lid;
    private Integer mid;
    private Integer luid;
    private Integer muid;
    private Timestamp ltime;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getLuid() {
        return luid;
    }

    public void setLuid(Integer luid) {
        this.luid = luid;
    }

    public Integer getMuid() {
        return muid;
    }

    public void setMuid(Integer muid) {
        this.muid = muid;
    }

    public Timestamp getLtime() {
        return ltime;
    }

    public void setLtime(Timestamp ltime) {
        this.ltime = ltime;
    }

    @Override
    public String toString() {
        return "Like{" +
                "lid=" + lid +
                ", mid=" + mid +
                ", luid=" + luid +
                ", muid=" + muid +
                ", ltime=" + ltime +
                '}';
    }
}

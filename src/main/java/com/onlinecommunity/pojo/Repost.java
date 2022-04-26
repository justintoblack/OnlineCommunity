package com.onlinecommunity.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:18
 */
public class Repost {
    private Integer rid;
    private Integer mid;
    private Integer ruid;
    private Integer muid;
    private Timestamp rtime;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
    }

    public Integer getMuid() {
        return muid;
    }

    public void setMuid(Integer muid) {
        this.muid = muid;
    }

    public Timestamp getRtime() {
        return rtime;
    }

    public void setRtime(Timestamp rtime) {
        this.rtime = rtime;
    }
}

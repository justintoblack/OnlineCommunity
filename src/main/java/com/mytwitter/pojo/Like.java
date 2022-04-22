package com.mytwitter.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:18
 */
public class Like {
    private int lid;
    private int tid;
    private int luid;
    private int tuid;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getLuid() {
        return luid;
    }

    public void setLuid(int luid) {
        this.luid = luid;
    }

    public int getTuid() {
        return tuid;
    }

    public void setTuid(int tuid) {
        this.tuid = tuid;
    }

    public Timestamp getStime() {
        return stime;
    }

    public void setStime(Timestamp stime) {
        this.stime = stime;
    }

    private Timestamp stime;
}

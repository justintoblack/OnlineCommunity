package com.onlinecommunity.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:18
 */
public class Star {

    private Integer sid;
    private Integer mid;
    private Integer suid;
    private Timestamp stime;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public Timestamp getStime() {
        return stime;
    }

    public void setStime(Timestamp stime) {
        this.stime = stime;
    }


}

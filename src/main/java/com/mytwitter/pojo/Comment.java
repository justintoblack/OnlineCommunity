package com.mytwitter.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:17
 */
public class Comment {
    private int cid;
    private String ccontent;
    private int tid;
    private int cuid;
    private int tuid;
    private int clikecount;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getCuid() {
        return cuid;
    }

    public void setCuid(int cuid) {
        this.cuid = cuid;
    }

    public int getTuid() {
        return tuid;
    }

    public void setTuid(int tuid) {
        this.tuid = tuid;
    }

    public int getClikecount() {
        return clikecount;
    }

    public void setClikecount(int clikecount) {
        this.clikecount = clikecount;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    private Timestamp ctime;

}

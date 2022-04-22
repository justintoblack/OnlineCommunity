package com.mytwitter.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-22:36
 */
public class Tweet {
    private int tid;
    private int uid;
    private String tcontent;
    private String tpicurl;
    private Timestamp ttime;
    private int tlikecount;
    private int tcommentcount;
    private int tretweetcount;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTcontent() {
        return tcontent;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }

    public String getTpicurl() {
        return tpicurl;
    }

    public void setTpicurl(String tpicurl) {
        this.tpicurl = tpicurl;
    }

    public Timestamp getTtime() {
        return ttime;
    }

    public void setTtime(Timestamp ttime) {
        this.ttime = ttime;
    }

    public int getTlikecount() {
        return tlikecount;
    }

    public void setTlikecount(int tlikecount) {
        this.tlikecount = tlikecount;
    }

    public int getTcommentcount() {
        return tcommentcount;
    }

    public void setTcommentcount(int tcommentcount) {
        this.tcommentcount = tcommentcount;
    }

    public int getTretweetcount() {
        return tretweetcount;
    }

    public void setTretweetcount(int tretweetcount) {
        this.tretweetcount = tretweetcount;
    }
}
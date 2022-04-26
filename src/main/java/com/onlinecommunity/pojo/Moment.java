package com.onlinecommunity.pojo;

import java.sql.Timestamp;

/**
 * @create 2022/4/22-22:36
 */
public class Moment {
    private Integer mid;
    private Integer uid;
    private String mcontent;
    private String mpicurl;
    private Timestamp mtime;
    private Integer mlikecount;
    private Integer mcommentcount;
    private Integer mrepostcount;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public String getMpicurl() {
        return mpicurl;
    }

    public void setMpicurl(String mpicurl) {
        this.mpicurl = mpicurl;
    }

    public Timestamp getMtime() {
        return mtime;
    }

    public void setMtime(Timestamp mtime) {
        this.mtime = mtime;
    }

    public Integer getMlikecount() {
        return mlikecount;
    }

    public void setMlikecount(Integer mlikecount) {
        this.mlikecount = mlikecount;
    }

    public Integer getMcommentcount() {
        return mcommentcount;
    }

    public void setMcommentcount(Integer mcommentcount) {
        this.mcommentcount = mcommentcount;
    }

    public Integer getMrepostcount() {
        return mrepostcount;
    }

    public void setMrepostcount(Integer mrepostcount) {
        this.mrepostcount = mrepostcount;
    }
}
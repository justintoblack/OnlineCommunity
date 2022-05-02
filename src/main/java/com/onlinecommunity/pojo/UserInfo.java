package com.onlinecommunity.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @create 2022/4/22-22:31
 */
public class UserInfo {
    private Integer uid;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, max = 30, message = "用户名长度在1到30位")
    private String username;

    @Size(min = 1, max = 30, message = "真实姓名长度在1到30位")
    private String urealname;

    private String uavatarurl;

    @Size(max = 255, message = "用户简介最长255位")
    private String uabout;

    private String ubirthday;
    private Integer ufollowing;
    private Integer ufollowers;
    private Integer umomentcount;
    private Integer ulikecount;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrealname() {
        return urealname;
    }

    public void setUrealname(String urealname) {
        this.urealname = urealname;
    }

    public String getUavatarurl() {
        return uavatarurl;
    }

    public void setUavatarurl(String uavatarurl) {
        this.uavatarurl = uavatarurl;
    }

    public String getUabout() {
        return uabout;
    }

    public void setUabout(String uabout) {
        this.uabout = uabout;
    }

    public String getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(String ubirthday) {
        this.ubirthday = ubirthday;
    }

    public Integer getUfollowing() {
        return ufollowing;
    }

    public void setUfollowing(Integer ufollowing) {
        this.ufollowing = ufollowing;
    }

    public Integer getUfollowers() {
        return ufollowers;
    }

    public void setUfollowers(Integer ufollowers) {
        this.ufollowers = ufollowers;
    }

    public Integer getUmomentcount() {
        return umomentcount;
    }

    public void setUmomentcount(Integer umomentcount) {
        this.umomentcount = umomentcount;
    }

    public Integer getUlikecount() {
        return ulikecount;
    }

    public void setUlikecount(Integer ulikecount) {
        this.ulikecount = ulikecount;
    }

    public Integer getUstarcount() {
        return ustarcount;
    }

    public void setUstarcount(Integer ustarcount) {
        this.ustarcount = ustarcount;
    }

    private Integer ustarcount;

    }

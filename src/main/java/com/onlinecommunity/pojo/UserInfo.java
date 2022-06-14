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
    private String realName;

    private String avatarUrl;

    @Size(max = 255, message = "用户简介最长255位")
    private String about;

    private String phone;
    private String email;

    private String birthday;
    private Integer following = 0;
    private Integer followers = 0;
    private Integer momentCount = 0;
    private Integer likeCount = 0;
    private Integer starCount = 0;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getMomentCount() {
        return momentCount;
    }

    public void setMomentCount(Integer momentCount) {
        this.momentCount = momentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", about='" + about + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", following=" + following +
                ", followers=" + followers +
                ", momentCount=" + momentCount +
                ", likeCount=" + likeCount +
                ", starCount=" + starCount +
                '}';
    }
}

package com.onlinecommunity.pojo;

public class Following {

    private Integer uid;
    private Integer followingUid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFollowingUid() {
        return followingUid;
    }

    public void setFollowingUid(Integer followingUid) {
        this.followingUid = followingUid;
    }

    @Override
    public String toString() {
        return "Following{" +
                "uid=" + uid +
                ", followingUid=" + followingUid +
                '}';
    }
}

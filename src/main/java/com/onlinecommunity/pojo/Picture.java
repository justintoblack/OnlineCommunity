package com.onlinecommunity.pojo;


public class Picture {
    private Integer momentId;
    private Integer idx;
    private String url;

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "momentId=" + momentId +
                ", idx=" + idx +
                ", url='" + url + '\'' +
                '}';
    }
}

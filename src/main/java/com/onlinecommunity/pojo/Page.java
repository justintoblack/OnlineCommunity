package com.onlinecommunity.pojo;

/**
 * @create 2022/5/31-18:55
 */
public class Page {

    private Integer nextMomentId;
    private Integer previousMomentId;
    private Integer pageSize;

    public Integer getNextMomentId() {
        return nextMomentId;
    }

    public void setNextMomentId(Integer nextMomentId) {
        this.nextMomentId = nextMomentId;
    }

    public Integer getPreviousMomentId() {
        return previousMomentId;
    }

    public void setPreviousMomentId(Integer previousMomentId) {
        this.previousMomentId = previousMomentId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "nextMomentId=" + nextMomentId +
                ", previousMomentId=" + previousMomentId +
                ", pageSize=" + pageSize +
                '}';
    }
}

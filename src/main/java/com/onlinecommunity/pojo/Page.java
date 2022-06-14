package com.onlinecommunity.pojo;

/**
 * @create 2022/5/31-18:55
 */
public class Page {

    private Integer lastId = Integer.MAX_VALUE;
    private Integer pageSize = 10;
    private Integer currentPage;

    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastMomentId) {
        this.lastId = lastMomentId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
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
                "lastId=" + lastId +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}


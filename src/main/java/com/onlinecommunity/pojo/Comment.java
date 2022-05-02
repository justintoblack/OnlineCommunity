package com.onlinecommunity.pojo;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @create 2022/4/22-23:17
 */
public class Comment {
    private Integer cid;
    @NotBlank(message = "评论内容不能为空")
    private String ccontent;
    private Integer mid;
    private Integer cuid;
    private Integer muid;
    private Integer clikecount = 0;
    private Timestamp ctime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public Integer getMuid() {
        return muid;
    }

    public void setMuid(Integer muid) {
        this.muid = muid;
    }

    public Integer getClikecount() {
        return clikecount;
    }

    public void setClikecount(Integer clikecount) {
        this.clikecount = clikecount;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", ccontent='" + ccontent + '\'' +
                ", mid=" + mid +
                ", cuid=" + cuid +
                ", muid=" + muid +
                ", clikecount=" + clikecount +
                ", ctime=" + ctime +
                '}';
    }
}

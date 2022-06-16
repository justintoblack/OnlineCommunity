package com.onlinecommunity.result;

public enum ResultCode {
    SUCCESS(200, "success"),
    EXIST_USERNAME(200, "已存在该用户名"),
    NULL_USERNAME(200, "用户名为空"),
    NULL_PASSWORD(200, "密码为空"),
    NULL_UID(200, "用户ID为空"),
    NULL_MID(200, "动态ID为空"),
    NULL_CID(200, "评论ID为空"),
    NULL_REALNAME(200,"真实姓名为空"),
    NULL_AVATARURL(200,"头像地址为空"),
    NULL_PHONE(200,"电话为空"),
    NULL_EMAIL(200,"邮箱为空"),
    NULL_BIRTHDAY(200,"生日为空"),
    NULL_ABOUT(200,"简介为空"),
    NULL_LIKE(200, "没有点过赞"),
    NULL_STAR(200, "没有收藏过"),
    NULL_COMMENT(200, "没有评论过"),
    NULL_REPOST(200, "没有转发过"),
    NONEXISTENT_USERNAME(200, "不存在的用户名"),
    NONEXISTENT_UID(200, "不存在的用户ID"),
    NONEXISTENT_MID(200, "不存在的动态ID"),
    NONEXISTENT_CID(200, "不存在的评论ID"),
    NONEXISTENT_SID(200, "不存在的收藏ID"),
    NONEXISTENT_LID(200, "不存在的点赞ID"),
    NONEXISTENT_RID(200, "不存在的转发ID"),
    WRONG_PASSWORD(200,"密码错误"),
    WRONG_ADDFOLLOWING(200,"关注失败"),
    WRONG_DELETEFOLLOWING(200,"取关失败"),
    CANNOT_DELETE_OTHERS_MOMENT(200, "无法删除其他人的动态"),
    CANNOT_DELETE_OTHERS_COMMENT(200, "无法删除其他人的评论"),
    CANNOT_DELETE_OTHERS_STARMOMENT(200, "无法删除其他人的收藏动态"),
    CANNOT_DELETE_OTHERS_LIKEMOMENT(200, "无法删除其他人的点赞动态"),
    CANNOT_DELETE_OTHERS_REPOSTMOMENT(200, "无法删除其他人的转发动态"),
    CANNOT_DELETE_OTHERS_COMMENTMOMENT(200, "无法删除其他人的评论动态"),
    REPEATED_LIKE(200, "已经点过赞"),
    REPEATED_STAR(200, "已经收藏过"),
    EXIST_MID(200, "存在的动态ID"),
    EMPTY_UPLOAD_FILE(200,"上传的文件为空"),
    EXCEED_MAX_PIC_SIZE(200,"图片最大为10M");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.msg;
    }

}
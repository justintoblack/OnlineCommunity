package com.onlinecommunity.result;

public enum ResultCode {
    SUCCESS(200, "success"),
    EXIST_USERNAME(200, "已存在该用户名"),
    NULL_USERNAME(200, "用户名为空"),
    NULL_PASSWORD(200, "密码为空"),
    NULL_UID(200, "用户ID为空"),
    NULL_MID(200, "动态ID为空"),
    NULL_REALNAME(200,"真实姓名为空"),
    NULL_AVATARURL(200,"头像地址为空"),
    NULL_PHONE(200,"电话为空"),
    NULL_EMAIL(200,"邮箱为空"),
    NULL_BIRTHDAY(200,"生日为空"),
    NULL_ABOUT(200,"简介为空"),
    NONEXISTENT_USERNAME(200, "不存在的用户名"),
    NONEXISTENT_UID(200, "不存在的用户ID"),
    NONEXISTENT_MID(200, "不存在的动态ID"),
    WRONG_PASSWORD(200,"密码错误"),
    WRONG_ADDFOLLOWING(200,"关注失败"),
    WRONG_DELETEFOLLOWING(200,"取关失败"),
    CANNOT_DELETE_OTHERS_MOMENT(200, "无法删除其他人的动态"),
    REPEATED_LIKE(200, "已经点过赞"),
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
package com.onlinecommunity.result;

public enum ResultCode {
    SUCCESS(200, "success"),
    ALREADY_REGISTERED(200, "USERNAME ALREADY EXISTS"),
    NULL_USERNAME(200, "NULL USERNAME"),
    NULL_PASSWORD(200, "NULL PASSWORD"),
    NONEXISTENT_USERNAME(200, "NONEXISTENT_USERNAME"),
    WRONG_PASSWORD(200,"WRONG PASSWORD");
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
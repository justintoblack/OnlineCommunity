package com.onlinecommunity.result;

public enum ResultCode {
    SUCCESS(200, "success"),
    EXIST_USERNAME(200, "USERNAME ALREADY EXISTS"),
    NULL_USERNAME(200, "NULL USERNAME"),
    NULL_PASSWORD(200, "NULL PASSWORD"),
    NULL_UID(200, "NULL USER ID"),
    NULL_MID(200, "NULL MOMENT ID"),
    NONEXISTENT_USERNAME(200, "NONEXISTENT USERNAME"),
    NONEXISTENT_UID(200, "NONEXISTENT USER ID"),
    NONEXISTENT_MID(200, "NONEXISTENT MOMENT ID"),
    WRONG_PASSWORD(200,"WRONG PASSWORD"),
    CANNOT_DELETE_OTHERS_MOMENT(200, "CANNOT DELETE OTHERS' MOMENT"),
    REPEATED_LIKE(200, "REPEATED LIKE"),
    EXIST_MID(200, "EXIST MOMENT ID");
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
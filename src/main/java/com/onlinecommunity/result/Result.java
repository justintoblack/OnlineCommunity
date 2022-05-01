package com.onlinecommunity.result;


import java.io.Serializable;

public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    private Result() {
    }

    private Result(ResultCode resultCode, Object data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    public static Result failure(ResultCode resultCode) {
        return new Result(resultCode, null);
    }

    public static Result failure(String message) {
        Result result = new Result();
        result.setCode(400);
        result.setMsg(message);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }
}



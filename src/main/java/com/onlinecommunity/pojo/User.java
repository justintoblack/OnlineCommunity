package com.onlinecommunity.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
    private Integer uid;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, max = 30, message = "用户名长度在1到30位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度在6到20位")
    private String password;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

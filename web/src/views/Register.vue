<template>
  <v-app>
    <v-app-bar color="blue lighten-1" app dense flat>
      <v-app-bar-title>
        <div class="char_style">{{ title }}</div>
      </v-app-bar-title>
      <div class="text_center char_style div_maxwidth">注册一个新账号!</div>
    </v-app-bar>

    <div class="bg">
      <div class="login text_center">
        <div style="display: flex">
          <div class="div_center char_style">账号:&ensp;&ensp;</div>
          <v-text-field
            v-model="username"
            :rules="[rules.required_user, rules.user]"
            class="div_center char_style"
            label="请输入账号"
          ></v-text-field>
        </div>

        <p class="margin_vertical"></p>

        <div style="display: flex">
          <div class="div_center char_style">密码:&ensp;&ensp;</div>
          <v-text-field
            v-model="password0"
            :append-icon="showpass0 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showpass0 ? 'text' : 'password'"
            :rules="[rules.required_pass, rules.pass]"
            @click:append="showpass0 = !showpass0"
            class="div_center char_style"
            label="请输入密码"
          ></v-text-field>
        </div>

        <p class="margin_vertical"></p>

        <div style="display: flex">
          <div class="div_center char_style">确认密码:&ensp;&ensp;</div>
          <v-text-field
            v-model="password1"
            :append-icon="showpass1 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showpass1 ? 'text' : 'password'"
            @click:append="showpass1 = !showpass1"
            :rules="[
              rules.required_pass,
              rules.pass,
              rules.check_pass(this.password0, this.password1),
            ]"
            class="div_center char_style"
            label="请重复密码"
          ></v-text-field>
        </div>
        <div>
          <v-row>
            <v-col>
              <v-btn @click="register()">注册</v-btn>
            </v-col>
          </v-row>
        </div>
      </div>
    </div>
  </v-app>
</template>
<script >
import axios from "axios";
export default {
  data() {
    return {
      bgUrl: require("../assets/bg01.png"),
      showpass0: false, //显示密码
      showpass1: false, //显示密码
      username: "",
      password0: "",
      password1: "",
      rules: {
        required_pass: (value) => !!value || "密码不能为空",
        required_user: (value) => !!value || "账户不能为空",
        pass: (v) => (v.length >= 6 && v.length <= 20) || "密码长度为6-20",
        user: (v) => (v.length >= 1 && v.length <= 30) || "密码长度为1-30",
        check_pass: (v0, v1) => v0 == v1 || "两次密码不一致",
      },
      title: "分享交流社区",
    };
  },
  methods: {
    //注册请求
    register() {
      if (this.username.length == 0 || this.password1.length == 0) {
        alert("用户名或密码为空");
        return;
      }
      if (this.password0 != this.password1) {
        alert("密码不一致");
        return;
      }
      if (this.password1.length < 6 || this.password1.length > 20) {
        alert("密码长度不符合");
        return;
      }
      if (this.username.length < 1 || this.username.length > 30) {
        alert("账号长度不符合");
        return;
      }
      axios
        .post(
          "/onlinecommunity/register",
          {
            username: this.username,
            password: this.password1,
          },
          {
            headers: { "Content-Type": "multipart/form-data" },
          }
        )
        .then((res) => {
          if (res.data.msg == "success") {
            alert('注册成功，跳转至登录界面');
            this.$router.push("/");
          } else {
            alert(res.data.msg);
          }
        });
    },
  },
};
</script>
<style>
.login {
  width: 30%;
  height: 30%;
  margin-left: 35%;
  margin-top: 15%;
}
.bg {
  height: 100%;
  width: 100%;
  /* background-image: url("../assets/bg01.png"); */
  background-repeat: no-repeat;
  background-size: 100% 100%;
  background-color: #bbdefb;
}
</style>
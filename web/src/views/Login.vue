<template>
  <v-app>
    <v-app-bar color="blue lighten-2" app dense flat>
      <div>
        <v-app-bar-title>
          <div class="char_style">{{ title }}</div>
        </v-app-bar-title>
      </div>
      <div class="title_content">欢迎来到登录!</div>
    </v-app-bar>

    <div class="bg">
      <div class="login text_center">
        <div style="display: flex">
          <div class="div_center char_style">账号:&ensp;&ensp;</div>
          <v-text-field
            v-model="username"
            :rules="[rules.required_user]"
            class="div_center char_style"
            label="请输入账号"
          ></v-text-field>
        </div>

        <p class="margin_vertical"></p>

        <div style="display: flex">
          <div class="div_center char_style">密码:&ensp;&ensp;</div>
          <v-text-field
            v-model="password"
            :append-icon="showpass ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showpass ? 'text' : 'password'"
            :rules="[rules.required_pass]"
            @click:append="showpass = !showpass"
            class="div_center char_style"
            label="请输入密码"
          ></v-text-field>
        </div>

        <p class="margin_vertical"></p>

        <div>
          <v-row>
            <v-col>
              <v-btn @click="register()">注册</v-btn>
            </v-col>
            <v-col>
              <v-btn @click="login()">登录</v-btn>
            </v-col>
          </v-row>
        </div>
      </div>
    </div>
  </v-app>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      bgUrl: require("../assets/bg01.png"),
      showpass: false,
      password: "",
      username: "",
      rules: {
        required_user: (value) => !!value || "账户不能为空",
        required_pass: (value) => !!value || "密码不能为空",
      },
      title: "分享交流社区",
    };
  },
  methods: {
    login() {
      if (this.password.length == 0 || this.username.length == 0) {
        alert("用户名或密码为空");
        return;
      }
      axios
        .post(
          "/api/login",
          {
            username: this.username,
            password: this.password,
          },
          {
            headers: { "Content-Type": "multipart/form-data" },
          }
        )
        .then((res) => {
          console.log(res.data);
          localStorage.token = res.data.data.token;
          localStorage.uid = res.data.data.uid;
          localStorage.toUid = res.data.data.uid;
          if (res.data.msg == "success") this.$router.push("/main/home");
          else alert(res.data.msg);
        });
    },
    register() {
      this.$router.push("/register");
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
  background-image: url("../assets/bg01.png");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  /* background-color: #bbdefb; */
}
</style>
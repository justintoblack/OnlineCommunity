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
            class="div_center char_style"
            label="请输入账号"
          ></v-text-field>
        </div>

        <p class="margin_vertical"></p>

        <div style="display: flex">
          <div class="div_center char_style">密码:&ensp;&ensp;</div>
          <v-text-field
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
<script >
export default {
  data() {
    return {
      bgUrl: require("../assets/bg01.png"),
      title: "分享交流社区",
    };
  },
  methods: {
    login() {
      this.$router.push("/main/home");
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
  background-image: url("../assets/bg04.png");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  /* background-color: #bbdefb; */
}
</style>
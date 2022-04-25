<template>
  <v-app>
    <v-btn @click="login()">登录</v-btn>
  </v-app>
</template>
<script >
export default {
  data() {
    return {
      str: "登录界面",
    };
  },
  methods: {
    login() {
      this.$router.push("/main/home");
    },
  },
};
</script>
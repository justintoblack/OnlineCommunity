<template>
  <v-navigation-drawer permanent absolute color="rgb(151, 194, 226)">
    <v-container>
      <div style="display: flex; justify-content: center">
        <v-img
          :src="imgUrl"
          min-width="200px"
          max-width="200px"
          min-height="200px"
          max-height="200px"
          class="img_broder"
          @click="toUserInfo()"
        />
      </div>
      <div class="user_name">menherachan</div>
    </v-container>
    <v-divider></v-divider>

    <v-list dense nav>
      <v-list-item
        v-for="item in items"
        :key="item.title"
        link
        @click="gotoView(item.id)"
      >
        <v-list-item-content class="text-center justify-center text-h6">
          {{ item.title }}
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>
<script>
export default {
  data() {
    return {
      imgUrl: "",
      items: [
        { title: "  首页", id: 0 },
        { title: "我的关注", id: 1 },
        { title: "我的粉丝", id: 2 },
        { title: "我的收藏", id: 3 },
        { title: "我的点赞", id: 4 },
        { title: "退出登录", id: 5 },
      ],
    };
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    //获取用户信息
    getUserInfo() {
      this.imgUrl = require("../assets/hutao.png");
    },
    //跳转
    toUserInfo() {
      this.$router.push("/main/user").catch((err) => err);
    },
    //跳转
    gotoView(id) {
      console.log("跳转到" + id);
      switch (id) {
        case 0:
          this.$router.push("/main/home").catch((err) => err);
          break;
        case 1:
          this.$router.push("/main/follow").catch((err) => err);
          break;
        case 2:
          this.$router.push("/main/fans").catch((err) => err);
          break;
        case 3:
          this.$router.push("/main/star").catch((err) => err);
          break;
        case 4:
          this.$router.push("/main/like").catch((err) => err);
          break;
        case 5:
          this.$router.push("/").catch((err) => err);
          break;
      }
    },
  },
};
</script>
<style>
.user_name {
  margin-top: 20px;
  font-size: 24px;
  text-align: center;
}

.nav {
  width: 100%;
  height: 100%;
}
</style>
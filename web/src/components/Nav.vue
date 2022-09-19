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
      <div  class="user_name">{{username}}</div>
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
import axios from 'axios'
export default {
  data() {
    return {
      imgUrl: "",
      username:"",
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
      axios.get('/onlinecommunity/get_self_info',{
        params:{
          uid : localStorage.uid,
          infoUid:localStorage.uid,
        },
        headers:{
          token :localStorage.token
        }
      }).then(res=>{
        console.log('用户信息');
        console.log(res.data);
        this.username = res.data.data.username;
        this.imgUrl = res.data.data.avatarUrl;
        axios.get("/onlinecommunity/static/"+this.imgUrl,{
                headers:{
                  'token' : localStorage.token
                },
                responseType : "blob"
              }).then(res=>{
                var blob = new Blob([res.data]);
               this.$data.imgUrl = URL.createObjectURL(blob);
          })
      })
    },
    //跳转
    toUserInfo() {
      localStorage.toUid = localStorage.uid;
      this.$router.push("/main/user").catch((err) => err);
    },
    //跳转
    gotoView(id) {
      console.log("跳转到" + id);
      switch (id) {
        case 0:
          this.$router.push("/main/home").catch((err) => err);
          this.$emit("update:change", "首页");
          break;
        case 1:
          this.$router.push("/main/follow").catch((err) => err);
          this.$emit("update:change", "我的关注");
          break;
        case 2:
          this.$router.push("/main/fans").catch((err) => err);
          this.$emit("update:change", "我的粉丝");
          break;
        case 3:
          this.$router.push("/main/star").catch((err) => err);
          this.$emit("update:change", "我的收藏");
          break;
        case 4:
          this.$router.push("/main/like").catch((err) => err);
          this.$emit("update:change", "我的点赞");
          break;
        case 5:
          this.$router.push("/").catch((err) => err);
          this.$emit("update:change", "欢迎来到登录界面");
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
<template>
  <v-list>
    <v-list-item v-for="item in items" :key="item.id">
      <v-list-item-content>
        <div style="display: flex">
          <div>
            <v-img
              :src="item.imgUrl"
              min-width="150px"
              max-width="150px"
              min-height="150px"
              max-height="150px"
              class="img_broder"
              @click="toUserInfo(item.id)"
            />
          </div>
          <div class="char_style margin_left">{{ item.name }}</div>
          <div class="char_style div_maxwidth margin_left">
            个性签名 : {{ item.sign }}
          </div>
          <v-btn @click="clickFollow(item.id)">{{ item.follow_text }}</v-btn>
        </div>
        <v-divider></v-divider>
      </v-list-item-content>
    </v-list-item>
  </v-list>
</template>
<script >
export default {
  data() {
    return {
      items: [],
    };
  },
  mounted() {
    this.imitation();
  },
  methods: {
    // 模拟数据
    imitation() {
      for (let i = 0; i < 4; i++) {
        let item = {
          imgUrl: require("../assets/hutao" + i + ".png"),
          name: "hutao" + i,
          id: i,
          sign: "啦啦啦啦啦lalalalal",
          follow: true,
          follow_text: "关注",
        };
        if (item.follow) item.follow_text = "已关注";
        else item.follow_text = "未关注";
        this.items.push(item);
      }
    },
    // 获取关注列表
    getFollowList() {},
    //点击关注按钮
    clickFollow(id) {
      alert("点击了" + id);
    },
    //查看该用户个人信息
    toUserInfo(id) {
      this.$router.push("/main/user").catch((err) => err);
    },
  },
};
</script>
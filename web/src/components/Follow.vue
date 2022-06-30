<template>
  <div>
  <v-list>
    <v-list-item v-for="item in items" :key="item.id">
      <v-list-item-content>
        <div style="display: flex">
          <div>
            <v-img
              :src="item.imgSrc"
              min-width="150px"
              max-width="150px"
              min-height="150px"
              max-height="150px"
              class="img_broder"
              @click="toUserInfo(item.id)"
            />
          </div>
          <div class="char_style margin_left">{{ item.username }}</div>
          <div class="char_style div_maxwidth margin_left">
            个性签名 : {{ item.about }}
          </div>
          <v-btn :color="(item.isFollowing?'#64B5F6': 'white')" @click="clickFollow(item.id)">{{ item.isFollowing?'已关注':'未关注' }}</v-btn>
        </div>
        <v-divider></v-divider>
      </v-list-item-content>
    </v-list-item>
  </v-list>
  <!-- 下一页 首页 上一页 -->
    <div id="page" class="text_center">
      <div>
      <v-btn color="#64B5F6"  @click="changePage(-1)">上一页</v-btn>
      <v-btn color="#64B5F6" class="margin_left" @click="changePage(0)">首页</v-btn>
      <v-btn color="#64B5F6" class="margin_left" @click="changePage(1)" >下一页</v-btn>
      </div>
    </div>
  </div>
</template>
<script >
import axios from 'axios'
export default {
  data() {
    return {
      page: 1,
      hasPrePage : false,
      hasNextPage : false,
      items: [],
    };
  },
  mounted() {
    //this.imitation();
    this.getFollowList();
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
    getFollowList() {
      axios.get('/api/get_followings_list',{
        params:{
          'currentPage' : this.page,
          'uid':localStorage.uid
        },
        headers:{
          'token':localStorage.getItem('token')
        }
      }).then(res=>{
        console.log(res.data);
        if(res.data.msg!='success')return;
        let lists = res.data.data.list;
        this.items = [];//清空数据
        //设置数据
        for(let i = 0 ;i < lists.length;i++)
        {
          let userInfo = {
            id : i,
            uid : lists[i].uid,
            username : lists[i].username,
            about : lists[i].about,
            isFollowing : lists[i].isFollowing,
            imgUrl : lists[i].avatarUrl,
            imgSrc : '',
          }
           axios.get("/api/static/"+userInfo.imgUrl,{
                headers:{
                  'token' : this.token
                },
                responseType : "blob"
              }).then(res=>{
                var blob = new Blob([res.data]);
                userInfo.imgSrc = URL.createObjectURL(blob);
          });
          this.items.push(userInfo);
        }
      })
    },
    //切页
    changePage(page)
    {
      switch(page)
      {
        case -1:
          if(this.hasPrePage){
            page--;
            }
          else {
            alert('已经是第一页');
            return;
          }
          break;

        case 0: 
          page =1;
          break;

        case 1:
          if(this.hasNextPage){
            page++;
          }
          else{
            alert('已经是最后一页');
            return;
          }
          break;
      }
      this.getFollowList();
    },
    //点击关注按钮
    clickFollow(id) {
      console.log("点击了" + id);
      axios.post('/api/follow',
          {
            'cuid': localStorage.uid,
            'uid': this.items[id].uid,
            'isFollowing' : this.items[id].isFollowing
          },
          {
            headers: {
              'token' : localStorage.token,
              "Content-Type": "multipart/form-data" 
              },
          }
      ).then(res =>{
        if(res.data.msg == 'success')
          this.items[id].isFollowing = !this.items[id].isFollowing;
      })
    },
    //查看该用户个人信息
    toUserInfo(id) {
      localStorage.toUid = this.$data.items[id].uid;
      this.$router.push("/main/user").catch((err) => err);
    },
  },
};
</script>
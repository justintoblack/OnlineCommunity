<template>
   <v-container>
  <v-row>
      <v-col cols="2"></v-col>
      <!-- 头像 -->
      <v-col cols="6">
         <v-img
          :src="imgUrl"
          min-width="200px"
          max-width="200px"
          min-height="200px"
          max-height="200px"
          class="img_broder"
        />
      </v-col >
      <!-- 关注 -->
      <v-col cols="1">
        <!-- 隐藏上传文件按钮 -->
          <input 
              style="display:none"
              type="file"
              id = "selectPic"
              accept="image/*"
              @change="postAvatar($event)"/>
        <v-btn v-if="showBtn" color="#64B5F6" @click="modifyPhoto()">修改头像</v-btn>
        <v-btn v-if="!showBtn" :color="(user.isFollowing?'#64B5F6': 'white')" @click="clickFollow()">{{ user.isFollowing?'已关注':'未关注' }}</v-btn>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>

   <v-row>
    <v-col cols="1"></v-col>
    <!-- 用户名 -->
    <v-col cols="4">
      <div style="display:flex">
      <div class="div_center char_style">用户名:&ensp;</div>
      <v-text-field v-model="user.name" :readonly="readonly"></v-text-field>
      </div>
    </v-col >
    <v-col cols="1"></v-col>
    <!-- 手机号 -->
    <v-col cols="4">
      <div style="display:flex">
      <div class="div_center char_style">手机号:&ensp;</div>
      <v-text-field v-model="user.phone" :readonly="readonly"></v-text-field>
      </div>
    </v-col>
    <v-col cols="2"></v-col>
  </v-row>
     <v-row>
    <v-col cols="1"></v-col>
    <!-- 用户名 -->
    <v-col cols="4">
      <div style="display:flex">
      <div class="div_center char_style">邮箱:&ensp;</div>
      <v-text-field v-model="user.email" :readonly="readonly"></v-text-field>
      </div>
    </v-col >
    <v-col cols="1"></v-col>
    <!-- 手机号 -->
    <v-col cols="4">
      <div style="display:flex">
      <div class="div_center char_style">生日:&ensp;</div>
      <v-text-field v-model="user.birthday" :readonly="readonly"></v-text-field>
      </div>
    </v-col>
    <v-col cols="2"></v-col>
  </v-row>
      <v-row>
    <v-col cols="1"></v-col>
    <!-- 个性签名 -->
    <v-col cols="9">
      <div style="display:flex">
      <div class="div_center char_style">个性签名:&ensp;</div>
      <v-text-field v-model="user.about" :readonly="readonly"></v-text-field>
      </div>
    </v-col >
    <v-col cols="2"></v-col>
  </v-row>
  <v-row>
      <v-col cols="1"></v-col>
      <!-- 粉丝数 -->
      <v-col cols="4">
        <div style="display:flex">
        <div class="div_center char_style">粉丝数:&ensp;{{user.followers}}</div>
        </div>
      </v-col >
      <v-col cols="1"></v-col>
      <!-- 关注数 -->
      <v-col cols="4">
        <div style="display:flex">
        <div class="div_center char_style">关注数:&ensp;{{user.followings}}</div>
        </div>
      </v-col>
      <v-col cols="2"></v-col>
      </v-row>
        <v-row>
        <v-col cols="1"></v-col>
        <!-- 点赞数 -->
        <v-col cols="4">
          <div style="display:flex">
          <div class="div_center char_style">点赞数:&ensp;{{user.likeCount}}</div>
          </div>
        </v-col >
        <v-col cols="1"></v-col>
        <!-- 帖子数 -->
        <v-col cols="4">
          <div style="display:flex">
          <div class="div_center char_style">帖子数:&ensp;{{user.momentCount}}</div>
          </div>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
      <p></p>
      <!-- 进入自身用户界面才显示 -->
      <div class="right_btn" v-if="showBtn">
        <v-btn class="margin_right" color="rgb(46, 196, 246)" @click="modify()">修改</v-btn>
        <v-btn class="margin_right" color="rgb(46, 196, 246)" @click="postInfo()">提交</v-btn>
      </div>
      <p></p>
    <v-divider></v-divider>
  </v-container>
</template>
<script >
import axios from 'axios'
export default {
  data() {
    return {
      //账户信息
      user :{
        name : '',
        phone : '',
        email : '',
        brithday : '',
        about : '',
        followers : '',
        followings : '',
        likeCount : '',
        momentCount : '',
        isFollowing : false,
        url : '',
      },
      imgUrl : '',
      //修改标签
      readonly : true,

      uid: localStorage.uid,
      toUid : localStorage.toUid,
      token : localStorage.token,

      //显示修改信息按钮
      showBtn : false,
    };
  },
  mounted(){
    this.getUserInfo();
    if(this.toUid==this.uid) this.showBtn = true;
  },
  methods:{
    //获取个人信息
    getUserInfo() {
      axios.get('/api/get_self_info',{
        params:{
          'uid' : this.uid,
          'infoUid': this.toUid,
        },
        headers:{
          'token' :this.token
        }
      }).then(res=>{
        if(res.data.code == '200')
        {
          console.log('用户信息');
          console.log(res.data);
          this.user.name = res.data.data.username;
          this.user.phone = res.data.data.phone;
          this.user.email = res.data.data.email;
          this.user.birthday = res.data.data.birthday;
          this.user.about = res.data.data.about;
          this.user.followers = res.data.data.followers;
          this.user.followings = res.data.data.followings;
          this.user.likeCount = res.data.data.likeCount;
          this.user.momentCount = res.data.data.momentCount;
          this.user.isFollowing = res.data.data.isFollowing;
          this.user.url = res.data.data.avatarUrl;
        }
        axios.get("/api/static/"+this.user.url,{
                headers:{
                  'token' : this.token
                },
                responseType : "blob"
              }).then(res=>{
                var blob = new Blob([res.data]);
               this.$data.imgUrl = URL.createObjectURL(blob);
          })
      })

    },
    //修改数据
    modify(){
      this.readonly = !this.readonly;
    },
    //修改个人信息
    postInfo(){
      console.log(this.$data.user.name);
      axios.post('/api/modify_self_info',
        {
          'uid' : localStorage.uid,
          'username' : this.user.name,
          'phone' : this.user.phone==""?" ":this.user.phone,
          'email' : this.user.email==""?" ":this.user.email,
          'birthday' : this.user.birthday==""?" ":"2000-01-01",
          'about' : this.user.about==""?" ":this.user.about,
        }
      ,
      {
        headers:{
          'token':localStorage.getItem('token'),
          "Content-Type": "multipart/form-data"
        },
      })
      .then(res=>{
        if(res.data.code=='200')
          alert('提交成功')
        else
          alert('提交失败')
      })
    },
    //修改头像
    modifyPhoto()
    {
      let inputBtn =  document.querySelector("#selectPic");
      inputBtn.click();
    },
    //上传头像
    postAvatar(e)
    {
      var imgFiles = (e.target.files[0]);
      var forms = new FormData();
      forms.append('pictures',imgFiles);
      forms.append('uid',localStorage.uid);
      var config = {
        headers:{
          token : localStorage.token
        }
      };
      axios.post('/api/modify_avatarUrl',forms,config)
      .then(res=>{
        if(res.data.code=='200')
        {
          alert('修改成功');
          this.imgUrl = window.URL.createObjectURL(imgFiles);
        }else{
          alert('修改失败');
        }
      })
    },
    //点击关注
    clickFollow() {
      axios.post('/api/follow',
          {
            'cuid': this.uid,
            'uid': this.toUid,
            'isFollowing' : user.isFollowing
          },
          {
            headers: {
              'token' : localStorage.token,
              "Content-Type": "multipart/form-data" 
              },
          }
      ).then(res =>{
        if(res.data.msg == 'success')
          user.isFollowing = !user.isFollowing;
      })
    }
  },
};
</script>
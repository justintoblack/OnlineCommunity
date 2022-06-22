<template>
   <v-container>
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

  <!-- 进入自身用户界面才显示 -->
  <div class="right_btn" v-if="showBtn">
    <v-btn class="margin_right" color="rgb(46, 196, 246)" @click="modify()">修改</v-btn>
    <v-btn class="margin_right" color="rgb(46, 196, 246)" @click="postInfo()">提交</v-btn>
  </div>
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
      },
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
        }
      })
    },
    //修改数据
    modify(){
      this.readonly = !this.readonly;
    },
    //提交
    postInfo(){
      console.log(this.$data.user.name);
      axios.post('/api/modify_self_info',
        {
          'uid' : localStorage.uid,
          'username' : this.user.name,
          'phone' : this.user.phone,
          'email' : this.user.email,
          'birthday' : this.user.birthday,
          'about' : this.user.about,
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
    }
  },
};
</script>
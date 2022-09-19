<template>
  <v-container >


    <v-divider></v-divider>
    <!-- 动态发布 -->
    <div>
      <v-container>
        <!-- 文本框 -->
        <h4>发布动态</h4>
        <p class="margin_vertical"></p>
        <v-textarea
          class="margin_left char_style"
          outlined
          label="分享你的想法"
          v-model="inputText"
        ></v-textarea>
        <!-- 图片 -->
             <div>
              <div style="display: flex" v-if="imgSrc.length > 0">
                <v-img
                  :src="imgSrc[0]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="imgSrc[1]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="imgSrc[2]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
              <div style="display: flex" v-if="imgSrc.length > 3">
                <v-img
                  :src="imgSrc[3]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="imgSrc[4]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="imgSrc[5]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
              <div style="display: flex" v-if="imgSrc.length > 6">
                <v-img
                  :src="imgSrc[6]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="imgSrc[7]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="imgSrc[8]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
            </div> 
        <!-- 按钮 -->
        <div class="right_btn">
          <!-- 隐藏上传文件按钮 -->
          <input 
              style="display:none"
              type="file"
              id = "selectPic"
              accept="image/*"
              @change="getPicture($event)"/>
          <v-btn class="margin_right" @click="clearText()">清空文本</v-btn> 
          <v-btn class="margin_right" @click="clearPic()">清空图片</v-btn>    
          <v-btn class="margin_right" @click="selectPic()">上传图片</v-btn>
          <v-btn @click="post_moment()">发布</v-btn>
        </div>
      </v-container>
    </div>
    <div></div>

    <v-divider></v-divider>
    <!-- 动态列表 -->
    <v-container>
      <h4>关注动态</h4>
      <v-list>
        <v-list-item id="lists" v-for="item in moment_items" :key="item.id">
          <v-list-item-content >
            <!-- 头部 -->
            <div style="display: flex">
              <v-img
                class="img_broder"
                :src="item.avatarUrl"
                max-height="50px"
                min-height="50px"
                max-width="50px"
                min-width="50px"
              ></v-img>
              <div class="margin_left char_style">{{ item.username }}</div>
              <div class="margin_left char_style">{{ item.momentTime }}</div>
            </div>

            <p class="margin_vertical"></p>

            <!-- 推文内容 -->
            <v-textarea
              readonly
              outlined
              auto-grow
              :value="item.content"
            ></v-textarea>

            <!-- 图片 -->
             <div>
              <div style="display: flex" v-if="item.pictureCount > 0">
                <v-img
                  :src="item.pic[0]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.pic[1]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.pic[2]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
              <div style="display: flex" v-if="item.pictureCount > 3">
                <v-img
                  :src="item.pic[3]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.pic[4]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.pic[5]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
              <div style="display: flex" v-if="item.pictureCount > 6">
                <v-img
                  :src="item.pic[6]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.pic[7]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.pic[8]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
            </div> 
            <!-- 操作按钮 -->
            <div name="operation" class="right_btn">
              <v-btn :color="(item.isStar?'#26A69A': 'white')" class="margin_right" @click="op_star(item.id)">收藏</v-btn>
              <v-btn :color="(item.isRepost?'#64B5F6': 'white')" class="margin_right" @click="op_repost(item.id)">转发{{ item.repostCount }}</v-btn>
              <v-btn   class="margin_right" @click="open_comment(item.id)">评论{{
                item.commentCount
              }}</v-btn>
              <v-btn  :color="(item.isLike?'#EF9A9A': 'white')" @click="op_like(item.id)">点赞({{ item.likeCount }})</v-btn>
            </div>

            <p class="margin_vertical"></p>

            <!-- 评论 -->
            <div class="normal_border" v-if="item.show_comment">
               <v-container>
                  <h6>发布评论</h6>
                  <p class="margin_vertical"></p>
                  <v-textarea
                    class="margin_left char_style"
                    outlined
                    label="输入评论"
                    v-model="commentText"
                  ></v-textarea>
                  <div class="right_btn">
                    <v-btn @click="post_comment(item.id)">评论</v-btn>
                  </div>
              </v-container>
              <v-list>
                <v-list-item v-for="com in item.comment_list" :key="com.id">
                  <v-list-item-content>
                    <div style="display: flex">
                      <v-img
                        class="img_broder"
                        :src="com.avatarUrl"
                        max-height="50px"
                        min-height="50px"
                        max-width="50px"
                        min-width="50px"
                      ></v-img>
                      <div class="char_style margin_left">{{ com.username }}</div>
                      <div class="char_style margin_left">{{ com.commentTime}}</div>
                    </div>

                    <p class="margin_vertical"></p>

                    <v-textarea
                      rows="3"
                      solo
                      readonly
                      :value="com.content"
                    ></v-textarea>
                    <v-divider></v-divider>
                    <p class="margin_vertical"></p>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
              <div>
                <v-btn @click="more_comment(item.id)">更多评论</v-btn>
              </div>
            </div>

            <p class="margin_vertical"></p>
            <v-divider></v-divider>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-container>
    <!-- 下一页 首页 上一页 -->
    <div id="page" class="text_center">
      <div>
      <v-btn color="#64B5F6"  @click="changePage(-1)">上一页</v-btn>
      <v-btn color="#64B5F6" class="margin_left" @click="changePage(0)">首页</v-btn>
      <v-btn color="#64B5F6" class="margin_left" @click="changePage(1)" >下一页</v-btn>
      </div>
    </div>
  </v-container>
</template>
<script >
import axios from 'axios'
export default {
  
  data() {
    return {
      page: 1,//当前页面
      hasNextPage:false,//是否有下一页
      hasPrePage:false,//是否有上一页
      inputText: "",//发布动态文本
      commentText : "",//评论文本
      moment_items: [],//动态列表
      imgFiles :[],//发布动态图片文件
      imgSrc :[],//发布动态图片资源
      token: localStorage.token,//token
      uid : localStorage.uid,//uid
      searchKey: '',
    };
  },
  mounted() {
    this.get_list();
    // this.imitation();
  },
  methods: {
    //模拟数据
    imitation() {
      for (let i = 0; i < 4; i++) {
        var item = {
          id: i,
          photo_num: i + 2,
          photo: [
            require("../assets/dango.png"),
            require("../assets/dango.png"),
            require("../assets/dango.png"),
            require("../assets/dango.png"),
            require("../assets/dango.png"),
            require("../assets/dango.png"),
          ],
          content: "这是第" + i + "条数据",
          time: "2022-04-2" + i + "  10:0" + i,
          name: "hutao" + i,
          imgurl: require("../assets/hutao" + i + ".png"),
          like: "点赞(" + i * 100 + ")",
          comment: "评论(" + i * 100 + ")",
          show_comment: false,
          comment_list: [
            {
              imgurl: require("../assets/hutao1.png"),
              id: i + "x",
              name: "hutao224",
              content: "这是评论" + i,
            },
            {
              imgurl: require("../assets/hutao2.png"),
              id: i + "xx",
              name: "hutao000",
              content: "然后这是评论" + i,
            },
          ],
          star: "收藏(" + i * 100 + ")",
          repost: "转发(" + i * 100 + ")",
        };
        this.moment_items.push(item);
      }
    },
    //触发选择图片按钮
    selectPic(){
      let inputBtn =  document.querySelector("#selectPic");
      inputBtn.click();
    },
    //清空文本
    clearText(){
      this.inputText = '';
    },
    //清空上传图片
    clearPic(){
      this.imgSrc = [];
      this.imgFiles = [];
    },
    //显示上传图片
    getPicture(e){
      let src = window.URL.createObjectURL(e.target.files[0]);
      this.imgFiles.push(e.target.files[0]);
      this.imgSrc.push(src);
      console.log(src);
    },
    //发布动态
    post_moment(){
      var forms = new FormData();
      forms.append('uid',localStorage.uid);
      forms.append('content',this.inputText);
      for(var i =0; i<this.imgFiles.length ;i++)
      {
        forms.append('pictures',this.imgFiles[i]);
      }
      var config = {
        headers:{
          token : localStorage.token
        }
      };
      axios.post('/onlinecommunity/post_moment',forms,config)
      .then(res=>{
        if(res.data.code=='200')
        {
          alert('发布成功');
          this.clearPic();
          this.clearText();
          this.get_list();
        }else{
          alert('发布失败');
        }
      })
    },
    //收藏
    op_star(id){
       console.log("收藏动态"+id);
      if(this.moment_items[id].isStar)
      {
        axios.post("/onlinecommunity/delete_star_moment",
                  {
                    'uid' : localStorage.uid,
                    'momentId' : this.moment_items[id].momentId,
                  },
                  {
                    headers:{
                      'token' : localStorage.token,
                      "Content-Type": "multipart/form-data"
                    }
                  }
                )
              .then(res=>{
                console.log(res.data);
                if(res.data.msg =='success')
                {
                  this.moment_items[id].isStar = false;
              }})
      }
      else{
        axios.get("/onlinecommunity/star_moment",{
                params:{
                  'uid':localStorage.uid,
                  'mid':this.moment_items[id].momentId,
                },
                headers:{
                'token' : this.token,
              }
              }).then(res=>{
                  this.moment_items[id].isStar = true;
              });
      }
    },
    //点赞
    op_like(id) {
      console.log("点击按钮" + id);
      if(!this.moment_items[id].isLike)
      {
        axios.get("/onlinecommunity/like_moment",{
                params:{
                  'uid':localStorage.uid,
                  'mid':this.moment_items[id].momentId,
                },
                headers:{
                'token' : this.token,
              }})
              .then(res=>{
                console.log(res.data);
                if(res.data.msg =='success')
                {
                  this.moment_items[id].likeCount++;
                  this.moment_items[id].isLike = true;
              }})
           }else{
             axios.
          post("/onlinecommunity/delete_like_moment",
                  {
                    'uid' : localStorage.uid,
                    'momentId' : this.moment_items[id].momentId,
                  },
                  {
                    headers:{
                      'token' : localStorage.token,
                      "Content-Type": "multipart/form-data"
                    }
                  }
                )
              .then(res=>{
                console.log(res.data);
                if(res.data.msg =='success')
                {
                  this.moment_items[id].likeCount--;
                  this.moment_items[id].isLike = false;
              }})
      }
     
    },
    //转发
    op_repost(id){
      if(this.moment_items[id].isRepost)
      {
        return;
      }
      var dialog = confirm("确认转发吗");
      if(dialog){
        axios.get("/onlinecommunity/repost_moment",{
          params:{
            uid: localStorage.uid,
            mid : this.moment_items[id].momentId,
          },
          headers:{
            token : this.token,
          }
        }).then(res =>{
          if(res.data.msg == 'success')
          {
            this.moment_items[id].isRepost = !this.moment_items[id].isRepost ;
            this.moment_items[id].repostCount ++;
          }
        })
      }
    },
    //展开或关闭评论
    open_comment(id) {
      //关闭评论
      if(this.moment_items[id].show_comment)
      {
        this.moment_items[id].show_comment = false;
        this.moment_items[id].comment_page = 1;
        this.moment_items[id].comment_list = [];
        return;
      } 
      //展开
      this.moment_items[id].show_comment = true;

      this.get_comment_list(id);
    },
    //获取评论
    get_comment_list(id)
    {
      console.log(this.moment_items[id]);

      let list = [];

      axios.get("/onlinecommunity/get_comment_list",{
        params:{
          'currentPage' : this.moment_items[id].comment_page,
          'mid' : this.moment_items[id].momentId
        },
        headers:{
          'token' : this.token
        }
      }).then(res=>{
        console.log(res.data);
        list = res.data.data.list;
        this.moment_items[id].has_next_comment = res.data.data.hasNextPage;
        for(let i = 0;i<list.length;i++)
        {
          //获取头像
          axios.get("/onlinecommunity/static/"+list[i].avatarUrl,{
                headers:{
                  'token' : this.token
                },
                responseType : "blob"
              }).then(res=>{
                let blob = new Blob([res.data]);
                let obj = list[i];
                obj.avatarUrl = URL.createObjectURL(blob);
                this.moment_items[id].comment_list.push(list[i]);
          });
        }
      })
    },
    //更多评论
    more_comment(id)
    {
      if(!this.moment_items[id].has_next_comment)
      {
        alert("没有更多评论了...");
        return;
      }
      this.moment_items[id].comment_page += 1;
      this.get_comment_list(id);
    },
    //关闭评论
    // close_comment(id) {
    //   this.moment_items[id].show_comment = false;
    // },
    //发表评论
    post_comment(id) {
        console.log("评论"+id+" 内容:"+this.commentText);

        axios.post("/onlinecommunity/comment_moment",
          {
            'mid' : this.moment_items[id].momentId,
            'uid': this.uid,
            'ccontent' : this.commentText,
          },
          {
            headers:{ 
              'token' : this.token,
              "Content-Type": "multipart/form-data"
              }
          }
      ).then(res=>{
        console.log(res.data);
        if(res.data.msg=="success")
        {
          this.commentText = "";
          this.moment_items[id].commentCount++;
        }
      })
    },
    //搜索
    search() {
      console.log("点击搜索");
      console.log(this.searchKey);
      localStorage.key = this.searchKey;
      this.$router.push("/main/search").catch((err) => err);
    },
    //切页
    changePage(page)
    {
      switch(page)
      {
        case -1:
          if(this.hasPrePage){
            this.$data.page--;
            }
          else {
            alert('已经是第一页');
            return;
          }
          break;

        case 0: 
          this.$data.page =1;
          break;

        case 1:
          if(this.hasNextPage){
            this.$data.page++;
          }
          else{
            alert('已经是最后一页');
            return;
          }
          break;
      }
      this.get_list();
    },
    //获取首页动态
    get_list(){
      axios.get("/onlinecommunity/get_like_list",{
      params:{
        'uid': this.uid,
        'currentPage' : this.page ,
      },
      headers:{
        'token' : this.token,
      }
      })
      .then(res=>{
        console.log(res.data);
        if(res.data.msg!='success')return;
        this.$data.hasNextPage = res.data.data.hasNextPage;
        this.$data.hasPrePage = res.data.data.hasPreviousPage;
        let momentList = res.data.data.list;
        //清空数组
        this.moment_items = [];
        //刷新数据
        for(let i = 0;i<momentList.length;i++)
        {
          let momentInfo = {
              id : i,//列表id
              momentId : momentList[i].momentId,//动态id
              content : momentList[i].content,//动态内容
              momentTime : momentList[i].momentTime,//动态时间
              likeCount : momentList[i].likeCount,//点赞数
              commentCount : momentList[i].commentCount,//评论数
              pictureCount : momentList[i].pictureCount,//图片数
              pictureURL : momentList[i].pictureUrlList,//图片路径
              pic : [],//图片
              repostCount : momentList[i].repostCount,//转发数
              username : momentList[i].username,//动态用户名
              avatarUrl : momentList[i].avatarUrl,//头像
              isStar : momentList[i].isStar, //是否收藏
              isLike : momentList[i].isLike, //是否点赞
              isRepost : momentList[i].isRepost, //是否转发
              show_comment : false,//显示评论
              comment_page: 1,//评论页面
              comment_list:[],//评论
              has_next_comment:true,//是否有更多评论
          };
          //获取图片
          for(var j = 0 ; j < momentInfo.pictureCount ; j++)
          {
              axios.get("/onlinecommunity/static/"+momentInfo.pictureURL[j],{
                headers:{
                  'token' : this.token
                },
                responseType : "blob"
              }).then(res=>{
                var blob = new Blob([res.data]);
                momentInfo.pic.push(URL.createObjectURL(blob));
          })
          }
          //获取头像
          axios.get("/onlinecommunity/static/"+momentInfo.avatarUrl,{
                headers:{
                  'token' : this.token
                },
                responseType : "blob"
              }).then(res=>{
                var blob = new Blob([res.data]);
                momentInfo.avatarUrl = URL.createObjectURL(blob);
          });
          this.moment_items.push(momentInfo);
        }
      });
      var t = document.getElementById('home_top');
      t.scrollIntoView();
    },
  },
};
</script>

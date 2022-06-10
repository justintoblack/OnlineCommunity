<template>
  <v-container>
    <!-- 搜索框 -->
    <v-text-field
      outlined
      dense
      label="搜索"
      prepend-icon="fa-search"
      @click:prepend="search()"
    ></v-text-field>

    <v-divider></v-divider>

    <div>
      <v-container>
        <h4>发布动态</h4>
        <p class="margin_vertical"></p>
        <v-textarea
          class="margin_left char_style"
          outlined
          label="分享你的想法"
        ></v-textarea>
        <div class="right_btn">
          <v-btn class="margin_right">上传图片</v-btn>
          <!-- <v-btn class="margin_right">上传视频</v-btn> -->
          <v-btn>发布</v-btn>
        </div>
      </v-container>
    </div>
    <div></div>

    <v-divider></v-divider>
    <v-container>
      <h4>关注动态</h4>
      <v-list>
        <v-list-item v-for="item in items" :key="item.id">
          <v-list-item-content>
            <!-- 头部 -->
            <div style="display: flex">
              <v-img
                class="img_broder"
                :src="item.imgurl"
                max-height="50px"
                min-height="50px"
                max-width="50px"
                min-width="50px"
              ></v-img>
              <div class="margin_left char_style">{{ item.name }}</div>
              <div class="margin_left char_style">{{ item.time }}</div>
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
              <div style="display: flex" v-if="item.photo_num >= 0">
                <v-img
                  :src="item.photo[0]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.photo[1]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.photo[2]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
              <div style="display: flex" v-if="item.photo_num >= 3">
                <v-img
                  :src="item.photo[3]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.photo[4]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.photo[5]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
              <div style="display: flex" v-if="item.photot_num >= 6">
                <v-img
                  :src="item.photo[6]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.photo[7]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>

                <v-img
                  class="margin_left"
                  :src="item.photo[8]"
                  max-height="200px"
                  min-height="200px"
                  max-width="200px"
                  min-width="200px"
                ></v-img>
              </div>
            </div>
            <!-- 操作按钮 -->
            <div name="operation" class="right_btn">
              <v-btn class="margin_right">{{ item.repost }}</v-btn>
              <v-btn class="margin_right">{{ item.star }}</v-btn>
              <v-btn class="margin_right" @click="op_comment(item.id)">{{
                item.comment
              }}</v-btn>
              <v-btn @click="op_like(item.id)">{{ item.like }}</v-btn>
            </div>

            <p class="margin_vertical"></p>

            <!-- 评论 -->
            <div class="normal_border" v-if="item.show_comment">
              <v-list>
                <v-list-item v-for="com in item.comment_list" :key="com.id">
                  <v-list-item-content>
                    <div style="display: flex">
                      <v-img
                        class="img_broder"
                        :src="com.imgurl"
                        max-height="50px"
                        min-height="50px"
                        max-width="50px"
                        min-width="50px"
                      ></v-img>
                      <div class="char_style margin_left">{{ com.name }}</div>
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
                <v-btn>更多评论</v-btn>
                <v-btn @click="close_comment(item.id)">收起评论</v-btn>
              </div>
            </div>

            <p class="margin_vertical"></p>
            <v-divider></v-divider>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-container>
  </v-container>
</template>
<script >
export default {
  data() {
    return {
      inputText: "好的",
      items: [],
    };
  },
  mounted() {
    this.imitation();
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
        this.items.push(item);
      }
    },
    //点赞
    op_like(id) {
      console.log("点击按钮" + id);
    },
    //打开评论
    op_comment(id) {
      this.items[id].show_comment = !this.items[id].show_comment;
    },
    //关闭评论
    close_comment(id) {
      this.items[id].show_comment = !this.items[id].show_comment;
    },
    //搜索
    search() {
      console.log("点击搜索");
    },
  },
};
</script>

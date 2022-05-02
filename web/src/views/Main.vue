<template>
  <v-app>
    <v-app-bar color="blue lighten-1" app dense flat>
      <div>
        <v-app-bar-title>
          <div class="char_style">{{ title }}</div>
        </v-app-bar-title>
      </div>
      <div class="title_content">{{ curView }}</div>
    </v-app-bar>
    <v-main>
      <div class="hori">
        <div class="height100"><Nav :change.sync="curView"></Nav></div>
        <div class="width100"><router-view /></div>
      </div>
    </v-main>
  </v-app>
</template>
<script >
import Nav from "@/components/Nav.vue";
export default {
  components: { Nav },
  data() {
    return {
      title: "分享交流社区",
      curView: "首页",
    };
  },
};
</script>
<style>
.hori {
  display: flex;
  height: 100%;
  width: 100%;
}
.height100 {
  height: 100%;
  width: 15%;
  position: fixed;
}
.width100 {
  width: 85%;
  margin-left: 15%;
}
</style>
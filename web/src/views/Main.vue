<template>
  <div class="hori">
    <div class="height100"><Nav></Nav></div>
    <div class="width100"><router-view /></div>
  </div>
</template>
<script >
import Nav from "@/components/Nav.vue";
export default {
  components: { Nav },
  data() {
    return {
      str: "首页",
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
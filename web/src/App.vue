<template>
  <v-app>
    <router-view></router-view>
  </v-app>
</template>

<script >
import Vue from "vue";

export default Vue.extend({
  name: "App",
  data: () => ({}),
  //禁止缩放
  // mounted() {
  //   this.keepRatio();
  //   window.addEventListener("resize", () => {
  //     this.keepRatio();
  //   });
  // },
  // methods: {
  //   //禁止缩放
  //   keepRatio() {
  //     var ratio = 0; // 定义一个缩放比例
  //     var screen = window.screen; // 获取窗口对象
  //     var ua = navigator.userAgent.toLowerCase();
  //     if (window.devicePixelRatio !== undefined) {
  //       ratio = window.devicePixelRatio; // 像素大小的比例
  //     } else if (~ua.indexOf("msie")) {
  //       if (screen.deviceXDPI && screen.logicalXDPI) {
  //         ratio = screen.deviceXDPI / screen.logicalXDPI;
  //       }
  //     } else if (
  //       window.outerWidth !== undefined &&
  //       window.innerWidth !== undefined
  //     ) {
  //       ratio = window.outerWidth / window.innerWidth; // 外部比例/内部比例：缩放比例
  //     }
  //     if (ratio) {
  //       ratio = Math.round(ratio * 100);
  //     }
  //     this.ratio = (ratio / 100).toFixed(2);
  //     document.body.style.zoom = 1 / this.ratio; // 窗口视图除以缩放比例：即缩放还原
  //   },
  // },
});
</script>


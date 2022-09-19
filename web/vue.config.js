
module.exports = {
   // 基本路径   整个文件夹在哪
   publicPath: './',
   // 输出文件目录   文件夹名
   assetsDir: "./static",
   // 指定生成的 index.html 的输出路径 (相对于 outputDir)。也可以是一个绝对路径    index的路劲和名字
   indexPath: './index.html',
 
  transpileDependencies: [
    'vuetify'
  ],
  // devServer: {
  //   proxy: {
  //     '/api': {
  //       target: "http://localhost:8080",
  //       changeOrigin: true,  //  是否跨域
  //       pathRewrite: {
  //         "^/api": "/onlinecommunity" //  重定向
  //       },
  //     }
  //   }
  // },

}

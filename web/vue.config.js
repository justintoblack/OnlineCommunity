
module.exports = {

  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    proxy: {
      '/api': {
        target: "http://localhost:8080",
        changeOrigin: true,  //  是否跨域
        pathRewrite: {
          "^/api": "" //  重定向
        },
      }
    }
  },

}

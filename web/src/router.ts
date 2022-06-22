import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('@/views/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/Register.vue')
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('@/views/Main.vue'),
      children: [
        {
          path: 'home',
          name: 'home',
          component: () => import('@/components/Home.vue')
        },
        {
          path: 'follow',
          name: 'follow',
          component: () => import('@/components/Follow.vue')
        },
        {
          path: 'user',
          name: 'user',
          component: () => import('@/components/User.vue')
        },
        {
          path: 'fans',
          name: 'fans',
          component: () => import('@/components/Fans.vue')
        },
        {
          path: 'like',
          name: 'like',
          component: () => import('@/components/Like.vue')
        },
        {
          path: 'star',
          name: 'star',
          component: () => import('@/components/Star.vue')
        },
        {
          path: 'search',
          name: 'search',
          component: () => import('@/components/Search.vue')
        },
      ]
    },
  ]
})

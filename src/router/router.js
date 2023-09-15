import Vue from 'vue'
import VueRouter from 'vue-router'


import InitPage from '../components/InitPage.vue' 
import Login from '../components/LogIn.vue' // Login 컴포넌트 경로 설정
import Join from '../components/Join.vue' // Join 컴포넌트 경로 설정

import MainPage from '../components/MainPage.vue' // MainPage 컴포넌트 경로 설정

import PhishingNumber from "../components/PhishingNumber.vue";
import PhishingNumberResult from "../components/PhishingNumberResult.vue";
import toDoPage from "@/components/ToDoPage.vue";


// AppBar 컴포넌트 경로 설정

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'InitPage',
    component: InitPage
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/main',
    name: 'MainPage',
    component: MainPage
  },
  {
    path: '/phishingnum',
    name: 'PhishingNumber',
    component: PhishingNumber
  },
  {
    path: '/phishingnumresult',
    name: 'PhishingNumberResult',
    component: PhishingNumberResult
  },
  {
    path: '/whattodo',
    name: 'ToDoPage',
    component: toDoPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

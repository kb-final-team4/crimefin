import Vue from 'vue'
import VueRouter from 'vue-router'


import InitPage from '../components/InitPage.vue' 
import Login from '../components/LogIn.vue' // Login 컴포넌트 경로 설정
//import Join from '../components/Join.vue' // Join 컴포넌트 경로 설정
import SignUp from '../components/SignUp.vue' //join 컴포넌트 경로 설정

import MainPage from '../components/MainPage.vue' // MainPage 컴포넌트 경로 설정

import PhishingNumber from "../components/PhishingNumber.vue";
import PhishingNumberResult from "../components/PhishingNumberResult.vue";
import PhishingLink from "../components/PhishingLink.vue";
import PhishingLinkResult from "../components/PhishingLinkResult.vue";
import AICheck from "../components/AICheck.vue";

import toDoPage from "../components/ToDoPage.vue";
import ServiceInfo from "../components/ServiceInfo.vue";
import Agency from "../components/Agency.vue";
import DashBoard from "../components/DashBoard.vue";


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
    paht: '/signup',
    name: 'SignUp',
    component:SignUp
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
    path: '/phishingLink',
    name: 'PhishingLink',
    component: PhishingLink,
    props: true,
  },
  {
    path: '/phishingLinkresult',
    name: 'PhishingLinkResult',
    component: PhishingLinkResult,
    props: true,
  },
  {
    path: '/aicheck',
    name: 'AICheck',
    component: AICheck,
    props: true,
  },
  {
    path: '/whattodo',
    name: 'ToDoPage',
    component: toDoPage
  },
  {
    path: '/serviceinfo',
    name: 'ServiceInfo',
    component: ServiceInfo
  },
  {
    path: '/closestagency',
    name: 'Agency',
    component: Agency
  },
  {
    path: '/dashboard',
    name: 'DashBoard',
    component: DashBoard
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
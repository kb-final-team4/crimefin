import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import vuetify from './plugins/vuetify'

import VueSession from 'vue-session'
import VueApexCharts from 'vue-apexcharts'
import axios from 'axios';

Vue.use(VueApexCharts);
Vue.component('apexchart', VueApexCharts);

//세션 사용 세팅
var sessionOptions = {
  persist: true
}
axios.defaults.withCredentials = true; //세션 설정

Vue.use(VueSession, sessionOptions)

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')

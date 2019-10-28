import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import component from './component/index'
import $ from 'jquery'
window.$ = $

/* service */
import constant from '@/service/constant'
import auctionService  from '@/service/auctionService'
import productService from '@/service/productService'
import userService from '@/service/userService'
import axios from 'axios'

Vue.prototype.$auctionService = auctionService
Vue.prototype.$productService = productService
Vue.prototype.$userService = userService
Vue.prototype.$axios = axios

/* style */
import './assets/style.css'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import '@fortawesome/fontawesome-free/css/all.css'


const opts = {
    theme: { themes: { light: { primary: '#3f51b5', secondary: '#b0bec5', accent: '#8c9eff', error: '#b71c1c', }, }, },
    icons: { iconfont: 'fa' }
}

Vue.use(Vuetify, opts);

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  data:{API_BASE_URL : constant.API_BASE_URL},
  components: { App },
  template: '<App/>',
  vuetify: new Vuetify(),
})

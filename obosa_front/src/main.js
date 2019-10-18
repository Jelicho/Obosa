import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import $ from 'jquery'
window.$ = $

/* common component */
import nav from '@/common/nav'
import breadcrumb from '@/common/breadcrumb'
import mypagenav from '@/common/mypage-nav'
import logo from '@/common/logo'
import VueDaumPostcode from "vue-daum-postcode"

Vue.component('v-nav', nav)
Vue.component('v-breadcrumb', breadcrumb)
Vue.component('v-mypage-nav', mypagenav)
Vue.component('logo', logo)
Vue.use(VueDaumPostcode);

/* service */
import constant from '@/service/constant'
import auctionService  from '@/service/auctionService'
import productService from '@/service/productService'
import userService from '@/service/userService'

Vue.prototype.$auctionService = auctionService
Vue.prototype.$productService = productService
Vue.prototype.$userService = userService

/* style */
import './assets/style.css'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'



const opts = {theme: {themes: {light: {primary: '#3f51b5',secondary: '#b0bec5',accent: '#8c9eff',error: '#b71c1c',},},},}

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

import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import component from './component/index'
import $ from 'jquery'
window.$ = $

/* service */
import constant from '@/service/constant'

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
  data:{API_BASE_URL : constant.API_BASE_URL, 
        USER_IMG_BASE_URL : constant.USER_IMG_BASE_URL,
        PRODUCT_IMG_BASE_URL : constant.PRODUCT_IMG_BASE_URL,
        DEFAULT_IMG_BASE_URL : constant.DEFAULT_IMG_BASE_URL,
        INTRO_BASE_URL : constant.INTRO_BASE_URL},
  components: { App },
  template: '<App/>',
  vuetify: new Vuetify(),
})

import Vue from 'vue'
import Vuex from 'vuex'
import userModule from './modules/userModule'
import productModule from './modules/productModule'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    userModule,
    productModule
  },
})

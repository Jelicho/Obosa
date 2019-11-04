import Vue from 'vue'
import Vuex from 'vuex'
import userModule from '@/store/modules/userModule'
import productModule from '@/store/modules/productModule'

import signupModule from '@/store/modules/signupModule'
import auctionModule from '@/store/modules/auctionModule'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    userModule,
    productModule,
    signupModule,
    auctionModule
  },
})

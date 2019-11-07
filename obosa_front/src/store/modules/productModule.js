import api from '../api/productAPI'
import axios from 'axios'

const state = {
  productList: [
  ],

}

const getters = {
  getProductState(){
    return state.productList
  }
}

const actions = {
  // product CRUD
  createProduct({commit}, formData){
    // params : pname / pdescription / productImgs
    api.create(formData)
  },
  readProduct({commit}){
    api.read().then(({data}) => commit('setProductList', data))
  },
  updateProduct({commit}, formData){
    api.update(formData)
  },
  deleteProduct({commit}, {pid}){
    api.delete({pid})
  }
}

const mutations = {
  setProductList (state, {data}) {
    console.log(data)
    state.productList = data.content
    // console.log(state.productList)
  }
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters
}

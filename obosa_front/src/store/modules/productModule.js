import api from '../api/productAPI'
import axios from 'axios'

const state = {
  productList: [
    // DESC : dummy data
    // {
    //   "pid" : "1",
    //   "pname" : "음",
    //   "pdescription" : "오",
    //   "productImgs" : ["https://www.google.com/url?sa=i&url=https%3A%2F%2Fimgur.com%2F&psig=AOvVaw2Sux9RQf_Ljj2pCQwRoqZ7&ust=1572917814312000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNDX3c21z-UCFQAAAAAdAAAAABAD","@/assets/product.png"],
    // },
    // {
    //   "pid" : "2",
    //   "pname" : "음",
    //   "pdescription" : "오",
    //   "productImgs" : ["https://www.google.com/url?sa=i&url=https%3A%2F%2Fimgur.com%2F&psig=AOvVaw2Sux9RQf_Ljj2pCQwRoqZ7&ust=1572917814312000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNDX3c21z-UCFQAAAAAdAAAAABAD"],
    // }
  ]
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
    state.productList = data.productList.data
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

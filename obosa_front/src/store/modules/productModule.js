import api from '../api/productAPI'
import axios from 'axios'

// initial state
const state = {
  productList: [
    {
      "pid" : "1",
      "pname" : "음",
      "pdescription" : "오",
      "productImgs" : [ 'https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/default/product.png','https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/default/product.png' ],
    },
    {
      "pid" : "2",
      "pname" : "음",
      "pdescription" : "오",
      "productImgs" : [ 'https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/default/product.png' ],
    }
  ],

}

const getters = {
  getProductState(){
    return state.productList
  }
}

// actions
const actions = {
  // product CRUD
  createProduct({commit}, formData){
    // params : pname / pdescription / uid / productImgs
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
  },

  login ({commit}, {email, password}) {
    api.login({email, password})
    .then(({data}) => commit('setAccessToken', data))
  },
  reLogin ({commit}) {
    if ((state.accessToken==null)
        && localStorage.accessToken ){
          commit('reSetAccessToken')
    }
  },
  logout({commit}) {
    commit('delAccessToken')
  },
  async passwordReChk ({commit}, password) {
    // const resp = api.passwordReChk("min3248")
    await  api.passwordReChk("min3248").then(response => {
      if(response.data.status == 200){
        console.log("communication : success");
        state.rechecked = true;
      }
    })
    // const accessToken = resp.data.accessToken.data
    // commit('login', accessToken)
  },
  getUserInfo({commit}){
    console.log("getprodList");
    api.getUserInfo().then(({data}) => commit('setUserInfo', data))
  },
  updateUser ({commit}, {
      password
    , phone
    , profileImg
    , zipCode
    , address
    }) {
    console.log(password);
    api.updateUser({
        password
      , phone
      , profileImg
      , zipCode
      , address
      })
    .then(response => {
      console.log(response);
    })

  },
}

// mutations
const mutations = {
  setProductList (state, {data}) {
    state.productList = data.productList.data
    console.log(state.productList)
  },
  setAccessToken (state, {data}) {
    state.accessToken = data.accessToken.data
    state.isSigned = true
    console.log(state.accessToken)
    // 토큰을 로컬 스토리지에 저장
    axios.defaults.headers.common['Authorization'] = state.accessToken;
    localStorage.accessToken = data.accessToken.data
  },
  reSetAccessToken (state) {
    state.accessToken = localStorage.accessToken
    state.isSigned = true
    axios.defaults.headers.common['Authorization'] = state.accessToken;
  },
  delAccessToken (state) {
    state.accessToken = null
    state.isSigned = false
    localStorage.removeItem("accessToken")
  },
  setUserInfo(state, {data}) {
    state.userInfo = data
    console.log("store : ")
    console.log(state.userInfo)
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters
}

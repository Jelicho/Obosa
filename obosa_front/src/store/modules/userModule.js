import api from '../api/userAPI'
import axios from 'axios'

// initial state
const state = {
  isSigned: false,
  accessToken: null,
  rechecked: false,
  userInfo: {}
}

const getters = {
  getSignedState(){
    return state.isSigned
  },
  getAccessToken(){
    return state.accessToken
  },
  getUser(){
    return state.userInfo
  }

}

// actions
const actions = {
  login ({commit}, {email, password}) {
    console.log(email, password);
    
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
    console.log(password);
    
    await  api.passwordReChk(password).then(response => {
      console.log(response.data);
      
      if(response.data.status == 200){
        console.log("communication : success");
        state.rechecked = true;
      }
    })
    // const accessToken = resp.data.accessToken.data
    // commit('login', accessToken)
  },
  getUserInfo({commit}){
    console.log("getuserinfo");
    api.getUserInfo().then(({data}) => commit('setUserInfo', data))
  },
  // updateUser ({commit}, form) {
  //   console.log(form);
  //   api.updateUser(form)
  //   .then(response => {
  //     console.log(response);
  //   })
  //
  // },
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

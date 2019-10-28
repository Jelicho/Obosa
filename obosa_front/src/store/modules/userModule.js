import api from '../api'
import axios from 'axios'

// initial state
const state = {
  isSigned: false,
  accessToken: null,
  rechecked: false,
  user: {}
}

const getters = {
  getSignedState(){
    return state.isSigned
  },
  getAccessToken(){
    return state.accessToken
  }

}

// actions
const actions = {
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
    console.log("getuserinfo");
    api.getUserInfo().then(({data}) => commit('setUserInfo', data))
  }
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
    state.user = data
    console.log(state.user)
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters
}

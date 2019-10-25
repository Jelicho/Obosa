import api from '../api'

// initial state
const state = {
  isSigned: false,
  accessToken: null,
  user: []
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
  passwordReChk ({commit}, params) {
    // params = {password}
    const resp = api.login(params)
    const accessToken = resp.data.accessToken.data
    commit('login', accessToken)
  },
  getUserInfo(){
    const params = {
      accessToken : this.state.accessToken
    }
    const resp = api.getUserInfo()
    const userInfo = resp.data.map(u => ({
      // 이름, 닉네임, 폰, 짚코드, 주소, 이미지
      name: u.name,
      nickname: u.nickname,
      phone: u.phone,
      zipCode: u.zipCode,
      address: u.address,
      email: u.email,
      profileImg: u.profileImg
    }))
    commit('setUserInfo', user)
  }
}

// mutations
const mutations = {
  setAccessToken (state, {data}) {
    state.accessToken = data.accessToken.data
    state.isSigned = true
    console.log(state.accessToken)
    // 토큰을 로컬 스토리지에 저장
    localStorage.accessToken = data.accessToken.data
  },
  reSetAccessToken (state) {
    state.accessToken = localStorage.accessToken
    state.isSigned = true
  },
  delAccessToken (state) {
    state.accessToken = null
    state.isSigned = false
    localStorage.removeItem("accessToken")
  },
  setUserInfo(state, user) {
    console.log("user : " + user)
    state.user = user.map(u => u)
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

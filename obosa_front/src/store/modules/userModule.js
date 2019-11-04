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
    api.login({email, password})
    .then(({data}) => commit('setToken', data))

    // DESC : status 401 (AccessToken Expired)
   axios.interceptors.response.use(function (response) {
     return response
   }, function (error) {
     const originalRequest = error.config
     if (error.response.status === 401){
       originalRequest._retry = true
       this.reFresh()
       originalRequest.headers['Authorization'] = state.accessToken
       return axios(originalRequest)
     }
     return Promise.reject(error)
   })
  },
  reFresh ({commit}) {
    if (localStorage.refreshToken ){
      console.log("refreshToken:"+localStorage.refreshToken);
      axios.defaults.headers.common['Authorization'] = localStorage.refreshToken;
      api.reFresh()
      .then(({data}) => {
        console.log(data);
        commit('reSetToken', data.data)
      })
      // DESC : status 401 (AccessToken Expired)
     axios.interceptors.response.use(function (response) {
       return response
     }, function (error) {
       const originalRequest = error.config
       if (error.response.status === 401){
         originalRequest._retry = true
         this.reFresh()
         originalRequest.headers['Authorization'] = state.accessToken
         return axios(originalRequest)
       }
       return Promise.reject(error)
     })
  }
},
  logout({commit}) {
    commit('delAccessToken')
  },
  async passwordReChk ({commit}, password) {
    await  api.passwordReChk("min3248").then(response => {
      if(response.data.status == 200){
        console.log("communication : success");
        state.rechecked = true;
      }
    })
  },
  getUserInfo({commit}){
    api.getUserInfo().then(({data}) => commit('setUserInfo', data))
  },
  updateUser ({commit}, {
      password
    , phone
    , profileImg
    , zipCode
    , address
    }) {
    api.updateUser({
        password
      , phone
      , profileImg
      , zipCode
      , address
      })
    .then(response => {
    })

  },
}

// mutations
const mutations = {
  setToken (state, {data}) {
    state.accessToken = data.accessToken.data
    state.isSigned = true
    axios.defaults.headers.common['Authorization'] = state.accessToken;
    localStorage.accessToken = data.accessToken.data
    localStorage.refreshToken = data.refreshToken.data
  },
  reSetToken (state, {data}) {
    state.accessToken = data
    localStorage.accessToken = state.accessToken
    state.isSigned = true
    axios.defaults.headers.common['Authorization'] = state.accessToken;
  },
  delToken (state) {
    state.accessToken = null
    state.isSigned = false
    localStorage.removeItem("accessToken")
    localStorage.removeItem("refreshToken")
  },
  setUserInfo(state, {data}) {
    state.userInfo = data
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters
}

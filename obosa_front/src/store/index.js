import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const enhanceAccessToeken = () => {
  const {accessToken} = localStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
}
enhanceAccessToeken()

export default new Vuex.Store({
  state: {
    isSigned: false,
    accessToken: null,
    user: {
      id: 0, // 사용자 아이디 저장
    },
    address : 'default'
  },
  getters: {
    getAccessToken() {return state.accessToken}
  },
  setMessageAction(newValue) {
    if (this.debug) console.log('setMessageAction triggered with', newValue)
    this.state.message = newValue
  },
  clearMessageAction() {
    if (this.debug) console.log('clearMessageAction triggered')
    this.state.message = ''
  },

  // Mutations
  mutations: {
    LOGIN (state, {data}) {
      state.accessToken = data.accessToken.data
      state.isSigned = true
      console.log(data.accessToken.data)
      // 토큰을 로컬 스토리지에 저장
      localStorage.accessToken = state.accessToken
    },
    LOGOUT (state) {
      state.accessToken = null,
      state.isSigned = false,
      localStorage.removeItem("accessToken")
    }
  },
  // actions
  actions: {
    LOGIN ({commit}, {email, password}) {
      console.log("email : " + email)
      console.log("password : " + password)
      return axios.post(`http://localhost:8083/auth/login`, {email, password})
        .then(({data}) => commit('LOGIN', data))
        // console.log(accessToken)
        // state.user.id = email
        // state.isSigned = true
        // 모든 HTTP 요청 헤더에 Authorization 을 추가한다.
        // axios.defaults.headers.common['Authorization'] = `Bearer ${data.accessToken}`;
    },
    LOGOUT ({commit}) {
      commit('LOGOUT')
    },
  }
})
// check session storage for logined user
function checkSessionStorage() {
  let storedState = JSON.parse(sessionStorage.getItem("state"))
  if (storedState !== null) {
    this.$store.state = storedState;
  }
}

checkSessionStorage()

var filter = function (text, length, clamp) {
  clamp = clamp || '...';
  var node = document.createElement('div');
  node.innerHTML = text;
  var content = node.textContent;
  return content.length > length ? content.slice(0, length) + clamp : content;
};

Vue.filter('truncate', filter);

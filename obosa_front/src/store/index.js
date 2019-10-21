import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: { 
    isSigned: false,
    user: {
      id: 0, // 사용자 아이디 저장
      hasWallet: false // 지갑을 가지고 있는지 여부 조회
    },
    address : 'default'
  },
  setMessageAction(newValue) {
    if (this.debug) console.log('setMessageAction triggered with', newValue)
    this.state.message = newValue
  },
  clearMessageAction() {
    if (this.debug) console.log('clearMessageAction triggered')
    this.state.message = ''
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

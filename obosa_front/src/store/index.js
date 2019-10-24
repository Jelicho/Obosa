import Vue from 'vue'
import Vuex from 'vuex'

import signupModule from '@/store/modules/signupModule'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    signupModule,
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

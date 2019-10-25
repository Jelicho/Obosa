import axios from 'axios'

const apiUrl = 'http://localhost:8083'

export default {
  login({email, password}) {
    return axios.post(`${apiUrl}/auth/login`, {
      email
      , password
    })
  },
  passwordReChk(params) {
    return axios.post(`${apiUrl}/auth/passReChk`, {
      params,
    })
  },
  getUserInfo(params) {
    return axios.post(`${apiUrl}/auth/passReChk`, {
      params,
    })
  }
}

import axios from 'axios'

// const apiUrl = 'http://localhost:8080'
const apiUrl = 'http://obosa.ssafy.io/api'

export default {
  login({email, password}) {
    return axios.post(`${apiUrl}/auth/login`, {
      email
      , password
    })
  },
  passwordReChk(password) {
    console.log(password)
    var config = {
    headers: {
        'Content-Type': 'text/plain'
      }
    };
    return axios.post(`${apiUrl}/auth/verification`,
      password, config
    )
  },
  getUserInfo() {
    return axios.get(`${apiUrl}/mypage`)
  },
  updateUser({
      password
    , phone
    , profileImg
    , zipCode
    , address
    }) {
    var config = {
    headers: {
        'Content-Type': 'text/plain'
      }
    };
    return axios.put(`${apiUrl}/mypage`, {
        password
      , phone
      , profileImg
      , zipCode
      , address
      }, config
  )
  }
}

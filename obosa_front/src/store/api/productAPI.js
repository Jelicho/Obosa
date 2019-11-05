import axios from 'axios'

// const apiUrl = 'http://localhost:8080'
const apiUrl = 'http://obosa.ssafy.io/api'

export default {
  create(params) {
    var config = {
    headers: {
        'Content-Type': 'multipart/form-data'
      }
    };
    return axios.post(`${apiUrl}/proudct`, formData, config)
  },
  read() {
    return axios.get(`${apiUrl}/mypage/product`)
  },
  update() {
    return axios.put(`${apiUrl}/product`, formData, config)
  },
  delete() {
    return axios.delete(`${apiUrl}/product`, {pid})
  },
}

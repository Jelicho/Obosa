import axios from 'axios'
// const apiUrl = 'http://obosa.ssafy.io:8333/api'
// const apiUrl = 'http://localhost:8080/api'
const apiUrl = 'http://obosa.ssafy.io/api'

export default {
  create(formData) {
    console.log(formData)
    console.log('dfasdf');
    var config = {
      headers: {
          'Content-Type': 'multipart/form-data'
        }
      };
    return axios.post(`${apiUrl}/product`, formData, config)
  },
  read() {
    return axios.get(`${apiUrl}/mypage/products`)
  },
  update() {
    return axios.put(`${apiUrl}/product`, formData, config)
  },
  delete() {
    return axios.delete(`${apiUrl}/product`, {pid})
  },
}

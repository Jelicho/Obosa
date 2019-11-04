import axios from 'axios'

const apiUrl = 'http://localhost:8083'

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
    var config = {
    headers: {
        'Content-Type': 'multipart/form-data'
      }
    };
    return axios.put(`${apiUrl}/product`, formData, config)
  },
  delete() {
    return axios.delete(`${apiUrl}/product`, {pid})
  },
}

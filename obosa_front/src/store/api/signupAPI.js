import axios from 'axios'
// const apiUrl = 'http://obosa.ssafy.io:8333/api/signup'
// const apiUrl = 'http://localhost:8080/api/signup'
const apiUrl = 'http://obosa.ssafy.io/api/signup'

export default {
  signup(regiFormData) {
    return $.ajax({
        type: "POST",
        url: apiUrl,
        data: regiFormData,
        processData: false,
        contentType: false,
    });
  },
  duplicateEmail(email) {
    return axios.get(`${apiUrl}/duplicateEmail`, {
      params: {
        'email': email
      }
    })
  },
  duplicateNickname(nickname) {
    return axios.get(`${apiUrl}/duplicateNickname`, {
      params: {
        'nickname': nickname
      }
    })
  }
}

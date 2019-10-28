import axios from 'axios'

const apiUrl = 'http://localhost:8080/auction'

export default {
    getAuctionList(params) {
        if (params.searchStr) {
            return axios.get(`${apiUrl}/search`, {
                params: params
            })
        } else {
            return axios.get(`${apiUrl}`, {
                params: {
                    page: params.page,
                    size: params.size,
                    sort: params.sort
                }
            })
        }
    },
    createAuction(params) {
        return axios.post(`${apiUrl}/create`, {
            params: params
        })
    }
}
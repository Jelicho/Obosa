import axios from 'axios'
axios.defaults.headers.common['Authorization'] = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJvYm9zYSIsImV4cCI6MTU3MjI0MDY4OSwiZW1haWwiOiJzZEBzZC5zZCJ9.F7Mus15lPlEYXReVFJKMIwwhqlbfal3hPex_AZdyuOk";
const apiUrl = 'http://localhost:8080/auction'

export default {
    getAuctionList(params) {
        if (params.searchStr != "") {
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
        return $.ajax({
            type: "POST",
            url: `${apiUrl}`,
            data: params,
            processData: false,
            contentType: false,
        });
    }
}
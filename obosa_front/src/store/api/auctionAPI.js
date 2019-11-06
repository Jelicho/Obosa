import axios from 'axios'
// axios.defaults.headers.common['Authorization'] = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJvYm9zYSIsImV4cCI6MTU3MjI0MDY4OSwiZW1haWwiOiJzZEBzZC5zZCJ9.F7Mus15lPlEYXReVFJKMIwwhqlbfal3hPex_AZdyuOk";
// const apiUrl = 'http://localhost:8080/api/auction'
const apiUrl = 'http://obosa.ssafy.io/api/auction'

export default {
    getAuctionList(params) {
        if (params.searchStr != "") {
            return axios.get(`${apiUrl}/search`, {
                params: params
            })
        } else {
            if(params.sort === 'count') params.sort = 'endDate'
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
        console.log(axios.defaults.headers.common['Authorization'])
        console.log(params)
        var config = {
            headers: {
                'Content-Type': 'multipart/form-data'
              }
            };
        return axios.post(`${apiUrl}`, params, config)
        // return $.ajax({
        //     type: "POST",
        //     url: `${apiUrl}`,
        //     data: params,
        //     processData: false,
        //     contentType: false,
        // });
    },
    bidAuction({ aid, bidPrice }) {
        return axios.post(`${apiUrl}/bid`, 
                { aid, bidPrice })
    }
}
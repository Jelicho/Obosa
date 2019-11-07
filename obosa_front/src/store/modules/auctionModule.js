import auctionAPI from '@/store/api/auctionAPI'
import Stomp from 'stomp-websocket'
import SockJS from 'sockjs-client'

const state = {
    auctionList: [],
    hasMoreAuctions: true,
    auctionResponse: {},
    stompClient: null,
    auctionIndexList: {}
}

const actions = {
    async getAuctionList({ commit }, params) {
        await auctionAPI.getAuctionList(params).then(response => {
            console.log(response.data);
            if(response.data.status == 200) {
                if(response.data.data.first){
                    commit('setAuctionList', response.data.data.content)
                } else {
                    commit('addAuctionList', response.data.data.content)
                }
                if(response.data.data.last){
                    commit('setHasMoreAuctions', false)
                }
            } else {
                commit('setAuctionList', [])
                commit('setHasMoreAuctions', false)
            }
        })
    },
    async getAuctionDetail({ commit }, params ){
        await auctionAPI.getAuctionDetail(params).then( response => {
            console.log(response);
            
            if(response.data.status == 200 ){
                commit('setAuction', response.data.data)
            } else {
                commit('setAuction', {})
                commit('setAuctionResponse', response)
            }
        })

    },
    async getAuctionListByUser({ commit }, params) {
        const response = await auctionAPI.getAuctionListByUser(params)
        if (response != null)
            params.page == 0 ? commit('setAuctionList', response) : commit('addAuctionList', response)
        else commit('setHasMoreAuctions', false)
    },
    async createAuction({ commit }, params) {
        const response = await auctionAPI.createAuction(params)
        commit('setAuctionResponse', response)
    },
    async bidAuction({ commit }, params){
        console.log(params);
        return auctionAPI.bidAuction( params ).then(response => {
            if(response.data.status == 200){
                return true;
            } else {
                return response.data.message;
            }
        })
    },
    // web socket
    connect({ state, commit }) {
        const socket = new SockJS("http://localhost:8080/api/bid");
        state.stompClient = Stomp.over(socket);
        state.stompClient.connect({}, function(frame) {
          console.log("Connected: " + frame);
          state.stompClient.subscribe("/api/topic/bidPrice", function(response) {
            commit('setPrice', JSON.parse(response.body))
            // TODO: 받은 id에 해당하는 auction의 최고가를 highPrice로 변경한다.
          });
        });
    },
    disconnect({ state }) {
        if (state.stompClient !== null) {
          state.stompClient.disconnect();
        }
        console.log("Disconnected");
    },
    updatePrice({ state },  auctionId ) {
        state.stompClient.send(
            "/api/bidPrice",
            {},
            JSON.stringify({ id: auctionId })
        );
    },
}

const mutations = {
    setAuctionList(state, response) {
        state.auctionList = response
        
        state.auctionIndexList = {}
        var i = 0;
        for (const auction of state.auctionList) {
            state.auctionIndexList[auction.aid] = i++
        }
    },
    addAuctionList(state, response) {
        var i = state.auctionList.length
        for (const auction of response) {
            state.auctionList.push(auction)
            state.auctionIndexList[auction.aid] = i++
        }
    },
    setHasMoreAuctions(state, response) {
        state.hasMoreAuctions = response
    },
    setAuction(state, response) {
        state.auction = response
    },
    setAuctionResponse(state, response) {
        state.auctionResponse = response
    },
    setStompclient(state, response) {
        state.stompClient = response
    },
    setPrice(state, response) {
        state.auctionList[state.auctionIndexList[response.id]].highPrice = response.highPrice
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}
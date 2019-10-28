import auctionAPI from '@/store/api/auctionAPI'

const state = {
    auctionList: [],
    hasMoreAuctions: true,
    auctionsResponse: {}
}

const actions = {
    async getAuctionList({ commit }, params) {
        const response = await auctionAPI.getAuctionList(params)
        if (response != null)
            params.page == 0 ? commit('setAuctionList', response) : commit('addAuctionList', response)
        else commit('setHasMoreAuctions', false)
    },
    async getAuctionListByUser({ commit }, params) {
        const response = await auctionAPI.getAuctionListByUser(params)
        if (response != null)
            params.page == 0 ? commit('setAuctionList', response) : commit('addAuctionList', response)
        else commit('setHasMoreAuctions', false)
    },
    async createAuction({ commit }, params) {
        const response = await auctionAPI.createAuction(params)
        commit('setAuctionsResponse', response)
    }
}

const mutations = {
    setAuctionList(state, response) {
        state.auctionList = response
    },
    addAuctionList(state, response) {
        for (const auction of response) {
            state.auctionList.push(auction)
        }
    },
    setHasMoreAuctions(state, response) {
        state.hasMoreAuctions = response
    },
    setAuctionsResponse(state, response) {
        state.auctionsResponse = response
    }
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}
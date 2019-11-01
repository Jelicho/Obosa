import signupAPI from '@/store/api/signupAPI'

const state = {
    signupResponse: [],
    duplicateResponse: []
}

const actions = {
    async signup({commit}, params) {
        const response = await signupAPI.signup(params)
        commit('setSignupResponse', response)
    },
    async duplicateEmail({commit}, email) {
        const response = await signupAPI.duplicateEmail(email)
        commit('setDuplicateResponse', response.data)
        return response.data
    },
    async duplicateNickname({commit}, params) {
        const response = await signupAPI.duplicateNickname(params)
        commit('setDuplicateResponse', response.data)
        return response.data
    }
}

const mutations = {
    setSignupResponse(state, response){
        state.signupResponse = response
    },
    setDuplicateResponse(state, response) {
        state.duplicateResponse = response
    }
}

export default {
    namespaced : true,
    state,
    actions,
    mutations
}
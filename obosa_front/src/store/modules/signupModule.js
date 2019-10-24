import signupAPI from '@/store/api/signupAPI'

const state = {
    signupResponse: []
}

const actions = {
    async signup({commit}, params) {
        const response = await signupAPI.signup(params)
        commit('setSignupResponse', response)
    },
    async duplicateEmail({commit}, email) {
        const response = await signupAPI.duplicateEmail(email)
        commit('setSignupResponse', response.data)
        return response.data
    },
    async duplicateNickname({commit}, params) {
        const response = await signupAPI.duplicateNickname(params)
        commit('setSignupResponse', response.data)
        return response.data
    }
}

const mutations = {
    setSignupResponse(state, response){
        state.signupResponse = response
    }
}

export default {
    namespaced : true,
    state,
    actions,
    mutations
}
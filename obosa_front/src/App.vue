<template>
  <v-app>
    <v-nav />
    <router-view class="router-view" />
  </v-app>
</template>

<script>
import {mapGetters, mapState} from 'vuex'
import {mapActions} from 'vuex'
import Stomp from 'stomp-websocket'
import SockJS from 'sockjs-client'
export default {
  name: "App",
  async beforeMount() {
    await this.reLogin()
  },
  computed: {
      ...mapState('webSocketModule',['stompClient'])
  },
  methods:{
    ...mapActions('userModule', ['reLogin']),
  },
};
</script>

<style>
</style>

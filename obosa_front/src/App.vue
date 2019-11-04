<template>
  <v-app>
    <v-nav />
    <router-view class="router-view" />
  </v-app>
</template>

<script>
import {mapGetters} from 'vuex'
import {mapActions} from 'vuex'
export default {
  name: "App",
  async beforeMount() {
    await this.reLogin()
  },
  methods:{
    ...mapActions('userModule', ['reLogin'])
  }

};
</script>

<style>
</style>

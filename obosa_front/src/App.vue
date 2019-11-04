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
    await this.reFresh()
  },
  methods:{
    ...mapActions('userModule', ['reFresh'])
  }

};
</script>

<style>
</style>

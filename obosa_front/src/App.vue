<template>
  <v-app>
    <v-nav />
    <router-view class="router-view" />
  </v-app>
</template>

<script>
export default {
  name: "App",
  async beforeMount() {
    if ((this.$store.state.accessToken==null)
        && localStorage.accessToken ){
      this.$store.state.accessToken = await localStorage.accessToken
      this.$store.state.isSigned = true
    }
  }

};
</script>

<style>
</style>

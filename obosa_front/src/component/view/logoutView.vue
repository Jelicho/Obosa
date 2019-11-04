<template></template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Logout",
  mounted: function() {
    this.onSubmit();
  },
  methods: {
    ...mapActions('userModule', ['logout']),
    async onSubmit() {
      await this.logout()
      this.redirect()
    },
    redirect() {
      this.$router.push("/")
    }
  }
};
</script>

<style>
</style>

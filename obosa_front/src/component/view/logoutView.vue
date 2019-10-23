<template></template>

<script>
export default {
  name: "Logout",
  mounted: function() {
    this.logout();
  },
  methods: {
    logout: function() {
      this.$store.dispatch('LOGOUT')
        .then(() => this.redirect())
        .catch(({message}) => this.msg = message)


      // var scope = this;
      //
      // this.$userService.logout(function() {
      //   store.state.isSigned = false;
      //   localStorage.removeItem("accessToken");
      //   scope.$router.push("/");
      //
        alert("성공적으로 로그아웃 되었습니다.");
      // });
      // if (sessionStorage.state) {
      //   sessionStorage.removeItem("state");
      // }
    },
    redirect() {
      console.log('entered redirect')
      this.$router.push("/")
    }
  }
};
</script>

<style>
</style>

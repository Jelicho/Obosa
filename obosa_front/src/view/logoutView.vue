<template></template>

<script>
export default {
  name: "Logout",
  mounted: function() {
    this.logout();
  },
  methods: {
    logout: function() {
      var scope = this;

      this.$userService.logout(function() {
        store.state.isSigned = false;
        store.state.user.id = 0;
        store.state.user.hasWallet = false;
        sessionStorage.removeItem("state");
        scope.$router.push("/");

        alert("성공적으로 로그아웃 되었습니다.");
      });
      if (sessionStorage.state) {
        sessionStorage.removeItem("state");
      }
    }
  }
};
</script>

<style>
</style>
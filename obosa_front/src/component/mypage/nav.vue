<template>
  <div class="row sub-nav">
    <div class="col-md-6">
      <nav class="nav nav-pills nav-fill">
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.product' }"
          :to="{ name: 'mypage.product' }"
        >내 경매</router-link>
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.product' }"
          :to="{ name: 'mypage.product' }"
        >내 상품</router-link>
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.update' }"
          :to="{ name: 'mypage.update' }"
        >개인정보 수정</router-link>
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.change_password' }"
          :to="{ name: 'mypage.change_password' }"
        >비밀번호 변경</router-link>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  name: "mypageNav",
  data() {
    return {
      sharedState: this.$store.state,
      viewName: ""
    };
  },
  mounted: function() {
    this.viewName = this.$route.name;
  }
};
</script>

<style>
</style>

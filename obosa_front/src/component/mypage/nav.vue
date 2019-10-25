<template>
  <div class="row sub-nav">
    <div class="col-md-11">
      <nav class="nav nav-pills nav-fill">
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.auction' }"
          to="/mypage/auction"
        >내 경매</router-link>
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.product' }"
          to="/mypage/product"
        >내 상품</router-link>
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.info' }"
          to="/mypage/info"
        >개인정보 수정</router-link>
        <router-view></router-view>
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

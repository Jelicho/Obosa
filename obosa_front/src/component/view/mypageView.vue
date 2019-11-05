<template>
  <div>
    <mypageNav />
    <router-view class="router-view"></router-view>
  </div>
</template>

<script>
import mypageNav from "@/component/mypage/nav";

export default {
  name: "MypageView",
  components: {
    mypageNav
  }
};
</script>

<style>
</style>

<template>
  <div>
    <mypageNav />

  </div>
</template>

<script>
import mypageNav from "@/component/mypage/nav";
import myUpdate from "@/component/mypage/update";
import myAuctionList from "@/component/mypage/auctionList";
import myProductList from "@/component/mypage/productList";
import myChangePassword from "@/component/mypage/changePassword";

export default {
  name: "MypageView",
  components: {
    mypageNav,
    myUpdate,
    myAuctionList,
    myProductList,
    myChangePassword
  }
};
</script>

<style>
</style>
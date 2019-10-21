<template>
  <div>
    <auctionNav />
    <router-view></router-view>
  </div>
</template>

<script>
import auctionNav from "@/component/auction/auctionNav";

export default {
  name: "Auction",
  components: {
    auctionNav
  },
  methods: {
  }
};
</script>

<style>
</style>
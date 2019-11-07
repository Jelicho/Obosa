<template>
  <div class="item-list">
    <v-layout row wrap pa-5>
      <v-flex v-for="auction in auctionList" xs12 sm6 md4 lg3 v-bind:key="auction.aid">
        <auction :auction="auction" height="500px"></auction>
      <v-divider></v-divider>
    </v-flex>
  </v-layout>
  </div>
</template>

<script>
import auction from "@/component/auction/auction";

export default {
  name: "myAuctionList",
  components: {
    auction
  }
};
</script>

<style>
</style>

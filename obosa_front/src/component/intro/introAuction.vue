<template>
  <router-link :to="{name: 'auction.detail' , params : { auction: auction }}">
    <v-card :height="height" flat>
      <v-img class="item-img" width="400px" height="400px" :src="auction.product.imgCount == 0 ? imgsrc() : productImg">
        <v-card-title>{{auction.product.pname}}</v-card-title>
      </v-img>
    </v-card>
  </router-link>
</template>

<script>
export default {
  name: "introAuction",
  props: ["auction", "height"],
  data() {
    return {
      productImg: PRODUCT_IMG_BASE_URL + "/" + this.auction.product.dirS3 + "/1"
    };
  },
  methods: {
    imgsrc(src) {
      return DEFAULT_IMG_BASE_URL + "/product.png";
    }
  }
};
</script>

<style>
</style>
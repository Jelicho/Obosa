<template>
  <router-link :to="{name: 'auction.detail' , params : { auction: auction }}">
    <v-card :height="height" flat>
      <v-img class="item-img" width="400px" height="400px" :src="imgsrc(productImg)">
      <v-card-title>{{auction.product.pname}}</v-card-title></v-img>
    </v-card>
  </router-link>
</template>

<script>
export default {
  name: "introAuction",
  props: ["auction", "height"],
  data() {
    return {
      productImg: "",
      progressColor: "amber"
    };
  },
  methods: {
    imgsrc(src) {
      return "https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/user/default_user_img.png";
    }
  }
};
</script>

<style>
</style>
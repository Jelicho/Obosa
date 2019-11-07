<template>
    <v-hover v-slot:default="{ hover }">
    <v-card :height="height" flat @click="toDetail">
      <div v-if="hover & isClosed()" class="non_click" ><div>이미 마감된<br/> 경매입니다</div></div>
      <v-img class="item-img" width="400px" height="400px" :src="auction.product.imgCount == 0 ? imgsrc() : productImg">
        <div v-if="hover & !isClosed()" class="non_click"><div>현재 최고가<br/>{{auction.highPrice}}원</div></div>
      </v-img>
    </v-card>
    </v-hover>
</template>

<script>
export default {
  name: "introAuction",
  props: ["auction", "height"],
  data() {
    return {
      productImg: PRODUCT_IMG_BASE_URL + "/" +this.auction.product.user.uid + "/" + this.auction.product.dirS3 + "/1"
    };
  },
  mounted(){
    var now = new Date().getTime();
  },
  methods: {
    imgsrc(src) {
      return DEFAULT_IMG_BASE_URL + "/product.png";
    },
    toDetail(){
      if(this.isClosed()) return;
      this.$router.push({name: "auction.detail",
        params: {
          auction: this.auction
        }})
    },
    isClosed(){
      return new Date(this.auction.endDate) < new Date()
    }
  }
};
</script>

<style>
</style>
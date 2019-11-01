<template>
  <v-layout mt-5 wrap>
    <v-flex v-for="i in products.length > limits ? limits : products.length" xs12 sm6 md4 lg3>
      <product
      :pId="products[i - 1].pId"
      :pName="products[i - 1].pName"
      :pDesc="products[i - 1].pDesc"
      :pImg="products[i - 1].pImg"
      ></product>
      <v-divider></v-divider>
    </v-flex>
    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn color="info" dark v-on:click="loadMorePosts"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
import product from "@/component/product/product";

export default {
  name: "productList",
  data() {
    return {
      products: []
    }
  },
  components: {
    product
  },
  methods: {
    fetchProduct() {
      this.$productService.findProductsByOwner(userId, function(productList) {
        if (productList) {
          this.products = product;
        }
      });
    }
  },
  mounted: function() {
    var scope = this;
    var userId = this.sharedStates.user.id;

    /**
     * TODO 1. 회원의 작품 목록을 가져옵니다.
     * Backend와 API 연동합니다.
     * 작품 마다 소유권 이력을 보여줄 수 있어야 합니다.
     */
    // 여기에 작성하세요.
    this.fetchproduct(userId);

    /**
     * TODO 2. 회원의 경매 목록을 가져옵니다.
     * Backend와 API 연동합니다.
     * 경매 중인 작품 마다 소유권 이력을 보여줄 수 있어야 합니다.
     */
    // 여기에 작성하세요.
  },
  computed: {
    isExistproduct() {
      if (this.products.length > 0) {
        return true;
      }
      return false;
    }
  }
};
</script>

<style>
</style>

<template>
  <div class="item-list">
    <v-layout row wrap pa-5>
      <v-flex pa-5 xs12 sm6 md4 lg3 v-for="product in products" v-bind:key="product.pId">
        <product :product="product" height="250px" ></product>
      </v-flex>
      <v-flex pa-5 xs12 sm6 md4 lg3>
        <loadingbox v-if="hasMoreAuctions" :height="430" />
      </v-flex>
    </v-layout>
  </div>
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

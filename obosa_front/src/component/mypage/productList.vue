<template>
  <div class="item-list">
  <v-layout row wrap pa-5>
      <v-flex v-for="product in products" xs12 sm6 md4 lg3 v-bind:key="product.pid">
        <Product :product="product"></Product>
      <v-divider></v-divider>
    </v-flex>
  </v-layout>
  <productRegister></productRegister>
</div>
</template>
<script>
import { mapActions } from "vuex";
import { mapGetters } from "vuex";
import { mapState } from "vuex";
import productRegister from "../product/productRegister"
import Product from "../product/product"
export default {
  props: {
    column: {type: Number, default: 1},
    limits: {type: Number, default: 4},
    loadMore: {type: Boolean, default: true}
  },
  data() {
    return {
      products: [],
      dialog: false
    }
  },
  watch: {
    productList: function() {
      this.setProductList()
    }
  },
  computed: {
    ...mapState('productModule', ['productList'])
  },
  components: {
    Product,
    productRegister,
  },
  async beforeMount() {
    await this.getProductList()
    await this.setProductList()
  },
  methods: {
    ...mapActions('productModule', ['readProduct']),
    ...mapGetters('productModule', ['getProductState']),
    // productCRUD
    getProductList(){
      this.readProduct()
    },
    async setProductList(){
      console.log("set productlist");
      this.products = await this.getProductState()
    },
  }
}
</script>
<style>
.mw-700 {
  max-width: 700px;
  margin: auto;
}
</style>

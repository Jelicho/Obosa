<template>
  <div>
  <v-layout mt-5 wrap>
      <v-flex v-for="product in products" xs12 sm6 >
        <Product
        :pId="product.pid"
        :pName="product.pname"
        :pDesc="product.pdescription"
        :pImgs="product.productImgs"
        ></Product>
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
    productRegister
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
      console.log(this.products);
      console.log("len : " + this.products.length);
      console.log("pid : " + this.products[0].productImgs);
      console.log("pid : " + this.productList[0].productImgs);

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

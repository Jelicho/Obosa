<template>
  <!-- <v-card
    class="mx-auto"
    max-width="400"
    @onclick=""
  > -->
  <v-card :height="height" outlined>

    <!-- <v-img
      class="white--text align-end"
      height="200px"
      :src="this.pImgs[0]"
    > -->
    <v-img class="item-img" :src="product.productImgs[0]" >
    </v-img>
    <v-card-title>{{product.pname}}</v-card-title>
    <v-card-text class="text--primary">
      <div>{{product.pdescription}}</div>
    </v-card-text>

    <v-card-actions>
      <v-btn color="orange" text @click="register()">
        경매등록sd
      </v-btn>

      <v-btn color="red" text @onclick="deleteProd">
        상품삭제
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import {mapActions} from "vuex"
export default {
  name: 'product',
  data() {
    return {
      dialog: false
    }
  },
  props: {
    product: {},
    height: ''
  },
  mounted(){
    console.log(this.product);
  },
  methods: {
    ...mapActions('productModule', ['updateProduct', 'deleteProduct']),
    updateProd(){
      // TODO: 상품삭제 메소드 바인딩 params : pid, pname, pdesc, pImg
      let formData = new formDate()
      this.updateProduct(formData)
    },
    deleteProd(){
      // TODO: 상품삭제 메소드 바인딩 params : pid
      this.deleteProduct({pid})
    },
    register(target){
      console.log(this.product);
      
      this.$router.push({
        name : 'mypage.auction.register',
        params : {
          product: this.product
        }
      })
    }
  }
}
</script>

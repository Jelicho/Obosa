<template>
  <v-card  outlined>
    <v-img width="100%"  :height="height" class="item-img" :src="getUrl()" >
    </v-img>
    <v-card-title>{{product.pname}}</v-card-title>
    <v-card-text class="text--primary">
      <div>{{product.pdescription}}</div>
    </v-card-text>

    <v-card-actions>
      <v-btn color="orange" text @click="register()">
        경매등록
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
      let formData = new formData()
      this.updateProduct(formData)
    },
    deleteProd(){
      // TODO: 상품삭제 메소드 바인딩 params : pid
      this.deleteProduct({pid})
    },

    getUrl(){
      if(this.product.imgCount===0){
        return DEFAULT_IMG_BASE_URL+'/product.png'
      }
      return PRODUCT_IMG_BASE_URL+'/'+this.product.user.uid+'/'+this.product.dirS3+'/'+1
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

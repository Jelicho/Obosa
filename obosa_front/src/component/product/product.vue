<template>
  <v-card
    class="mx-auto"
    max-width="400"
    @onclick=""
  >
    <v-img
      class="white--text align-end"
      height="200px"
      :src="getUrl()"
    >
      <v-card-title>{{pName}}</v-card-title>
    </v-img>

    <v-card-text class="text--primary">
      <div>{{pDesc}}</div>
    </v-card-text>

    <v-card-actions>
      <v-btn color="orange" text @onclick="updateProd">
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
    pid: '',
    pname: '',
    pdescription: '',
    dirS3:'',
    imgCount: 0,
    user:{}
  },
  mounted(){
    // console.log(this.pImgs);
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
    auctionRegister(){
      // TODO: quctionRegister binding(From. MK)

    },
    getUrl(){
      return PRODUCT_IMG_BASE_URL+'/'+this.user.uid+'/'+this.dirS3+'/'+1
    }
  }
}
</script>

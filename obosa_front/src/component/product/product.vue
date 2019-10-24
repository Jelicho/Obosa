<template>
  <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="200px"
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
      <v-card-title>상품명이 들어갑니다.</v-card-title>
    </v-img>

    <v-card-text class="text--primary">
      <div>상품 설명</div>
    </v-card-text>

    <v-card-actions>
      <v-btn color="orange" text>
        경매등록
      </v-btn>

      <v-btn color="orange" text>
        상품삭제
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>

export default {
  name: 'product',
  data() {
    return {
      dialog: false
    }
  },
  props: {
    pid: '',
    pName: '',
    pDesc: '',
    pImg: [],
  },
  methods: {
    deleteProduct(){
      // TODO: 상품삭제 메소드 바인딩 params : pid
    },
    updatePost(){
      // TODO: 상품삭제 메소드 바인딩 params : pid, pname, pdesc, pImg
    },
    auctionRegister(){
      // TODO: quctionRegister binding(From. MK)
    }
  }
}
</script>

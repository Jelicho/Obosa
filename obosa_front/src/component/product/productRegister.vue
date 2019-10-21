<template>
          <!-- <v-breadcrumb title="작품 등록" description="새로운 상품을 등록합니다."></v-breadcrumb> -->
          <div class="container">
              <div class="row">
                  <div class="col-md-8 mx-auto">
                      <div class="card">
                          <div class="card-body">
                              <div class="form-group">
                                  <label id="name">상품 이름</label>
                                  <input type="text" class="form-control" id="name" v-model="product.name">
                              </div>
                              <div class="form-group">
                                  <label id="description">상품 설명</label>
                                  <textarea class="form-control" id="description" v-model="product.description"></textarea>
                              </div>
                              <button type="button" class="btn btn-primary" v-on:click="save">상품 등록하기</button>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
</template>

<script>
// import imgur from '../components/Imgur'
import productService from '../../service/productService'

export default {
  name:"productRegister",
  data(){
      return {
          product: {
              name: "",
              description: ""
          },
          sharedStates: store.state,
          offsetTop: 0,
          dialog: false,
      }
  },
  methods: {
      save: function(){
          var scope = this;
          productService.create({
              "이름": this.product.name,
              "설명": this.product.description,
              "회원id": this.sharedStates.user.id
          },
          function(){
              alert('작품이 등록되었습니다.');
              scope.$router.push('/artworks');
          },
          function(error){
              alert("입력폼을 모두 입력해주세요.");
          });
      },
      onScroll(e) {
        this.offsetTop = window.scrollY;
      }
  }
}
</script>
<style>

</style>

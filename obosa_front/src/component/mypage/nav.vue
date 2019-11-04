<template>
<v-toolbar class="header" height="64px" flat>
    <v-toolbar-items>
      <v-btn text @click="to('mypage.auction')" class="nav-link" id='mypage_auction'>내 경매</v-btn>
      <v-btn text @click="to('mypage.product')" class="nav-link" id='mypage_product'>내 상품</v-btn>
      <v-btn text @click="to('mypage.info')" class="nav-link" id='mypage_info'>개인정보 수정</v-btn>
    </v-toolbar-items>
  </v-toolbar>
  <!-- <div class="row sub-nav">
    <div class="col-md-11">
      <nav class="nav nav-pills nav-fill">
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.auction' }"
          to="/mypage/auction"
        >내 경매</router-link>
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.product' }"
          to="/mypage/product"
        >내 상품</router-link>
        <router-link
          class="nav-item nav-link"
          v-bind:class="{ active: viewName == 'mypage.info' }"
          to="/mypage/info"
        >개인정보 수정</router-link>
        <router-view></router-view>
      </nav>
    </div>
  </div> -->
</template>

<script>
export default {
  name: "mypageNav",
  data() {
    return {
      sharedState: this.$store.state,
      viewName: ""
    };
  },
  mounted: function() {
    this.viewName = this.$route.name;
  },
  methods: {
    to(params) {
      for (const active of $('.header .btn-active')) {
        $(active).removeClass('btn-active')
      }
      console.log(params.replace('.', '-'));
      
      $('#'+params.replace('.', '-')).addClass('btn-active')
      this.$router.push({
        name: params
      });
    }
  }
};
</script>

<style>
</style>

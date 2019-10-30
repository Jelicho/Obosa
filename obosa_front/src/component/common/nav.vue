<template>
  <v-toolbar class="header" height="64px" flat>
    <v-toolbar-title>
      <logo />
    </v-toolbar-title>

    <v-spacer></v-spacer>

    <v-toolbar-items>
      <v-btn text @click="to('auction.list', 'count')" id="auclist">
        경매참여하기
      </v-btn>
      <v-btn text @click="to('mypage')" id="mypage" v-if="sharedState.isSigned">
        마이페이지
      </v-btn>
      <v-btn text @click="to('login')" id="login" v-if="!sharedState.isSigned">
        로그인
      </v-btn>
      <v-btn text @click="to('register')" id="register" v-if="!sharedState.isSigned">
        회원가입
      </v-btn>
      <v-btn text @click="to('logout')" id="logout" v-if="sharedState.isSigned">
        로그아웃
      </v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>

export default {
  name: "navbar",
  props: ["isSigned"],
  data() {
    return {
      store: this.$store,
      sharedState: this.$store.state
    };
  },
  methods: {
    to(name, params) {
      for (const active of $('.header .btn-active')) {
        $(active).removeClass('btn-active')
      }
      $('#'+name).addClass('btn-active')
      console.log(params || null );
      
      this.$router.push({
        name: name,
        params : { sortby : params || null }
      });
    }
  }
};
</script>

<style>
.header {
  width: 100%;
  position: fixed;
  z-index: 10;
}
</style>

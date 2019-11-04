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
      <v-btn text v-if="getSignedState" @click="to('passwordRe')" id="passwordRe" >
        마이페이지
      </v-btn>
      <v-btn text v-if="!getSignedState" @click="to('login')" id="login" >
        로그인
      </v-btn>
      <v-btn text v-if="!getSignedState" @click="to('register')" id="register" >
        회원가입
      </v-btn>
      <v-btn text v-if="getSignedState" @click="to('logout')" id="logout" >
        로그아웃
      </v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: "navbar",
  props: ["isSigned"],
  data() {
    return {
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
    },
  },
  computed: {
    ...mapGetters('userModule',['getSignedState'])
  }
}
</script>

<style>
.header {
  width: 100%;
  position: fixed;
  z-index: 10;
}
</style>
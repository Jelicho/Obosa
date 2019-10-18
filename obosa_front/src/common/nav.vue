<template>
  <v-toolbar color="#FFD54F" height="64px" flat >
    <v-toolbar-title>
      <logo />
    </v-toolbar-title>

    <v-spacer></v-spacer>

    <v-toolbar-items>
      <v-btn text>
        <router-link class="nav-link" to="/auction">경매참여하기</router-link>
      </v-btn>
      <v-btn text v-if="sharedState.isSigned">
        <router-link class="nav-link" to="/mypage/update">마이페이지</router-link>
      </v-btn>
      <v-btn text v-if="!sharedState.isSigned">
        <router-link class="nav-link" to="/login">로그인</router-link>
      </v-btn>
      <v-btn text v-if="!sharedState.isSigned">
        <router-link class="nav-link" to="/register">회원가입</router-link>
      </v-btn>
      <v-btn text v-if="sharedState.isSigned">
        <router-link class="nav-link" to="logout">로그아웃</router-link>
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
};
</script>

<style>
</style>

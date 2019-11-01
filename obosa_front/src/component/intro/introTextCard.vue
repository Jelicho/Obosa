<template>
  <v-row class="intro-view intro-item">
    <v-col cols="6" class="intro-card intro-card-1">
      <p>
        언제 어디서든
        <br />경매에 참여할 수 있습니다.
      </p>
      <br />
      <v-btn outlined tile large color="white" @click="to('auction.list', 'count')">경매 참여하기</v-btn>
    </v-col>
    <v-col cols="6" class="intro-card intro-card-2">
      <p>
        버리기 아쉬웠던 물건에게
        <br />새로운 주인을 찾아주세요.
      </p>
      <br />
      <v-btn outlined tile large color="white" @click="to('register')">회원가입</v-btn>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "introTextCard",
  methods: {
    to(name, params) {
      this.$router.push({
        name: name,
        params : { sortby : params || null }
      });
    }
  }
};
</script>

<style>
</style>
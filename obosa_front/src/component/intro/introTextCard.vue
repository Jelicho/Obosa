<template>
  <v-row class="intro-view intro-item">
    <v-col cols="6" class="intro-card intro-card-1">
      <p>
        언제 어디서든
        <br />경매에 참여할 수 있습니다.
      </p>
      <br />
      <v-btn outlined tile large color="white">경매 참여하기</v-btn>
    </v-col>
    <v-col cols="6" class="intro-card intro-card-2">
      <p>
        버리기 아쉬웠던 물건에게
        <br />새로운 주인을 찾아주세요.
      </p>
      <br />
      <v-btn outlined tile large color="white">회원가입</v-btn>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "introTextCard"
};
</script>

<style>
.intro-item {
  margin-top: 30px;
  /* height: 250px; */
  background-color: #fff0!important;
}
.intro-card {
  padding: 30px !important;
  height: 100%;
  text-align: left;
}
.intro-card > p {
  font-size: 30px;
  font-weight: 700;
  color: white;
}
.intro-card-1 {
  background-color: rgba(93, 114, 87, 0.603);
}
.intro-card-2 {
  background-color: rgba(75, 45, 53, 0.603);
}
</style>
<template>
  <div>
    <v-nav />
    <div id="main-overview" class="container">
      <v-row>
        <v-col md="7">
          <br />
          <v-btn depressed large color="#FFD54F">
            <router-link :to="{ name: 'register' }">회원가입</router-link>
          </v-btn>
        </v-col>
        <v-col md="5">
          <h1>
            OISO
            <br />BOISO
            <br />SAISO
            <br />
          </h1>
          <h4>중고 상품 경매를 시작해보세요.</h4>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style>
</style>
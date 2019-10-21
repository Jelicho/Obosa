<template>
  <v-toolbar color="#FFF9C4" height="64px" flat>
    <v-toolbar-items>
      <v-btn text class="nav-link">
        <router-link to="/auction/count">인기 순</router-link>
      </v-btn>
      <v-btn text class="nav-link">
        <router-link to="/auction/endDate">마감 임박</router-link>
      </v-btn>
      <v-btn text class="nav-link">
        <router-link to="/auction/regDate">신규 순</router-link>
      </v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
export default {
  name: "auctionNav"
};
</script>

<style>
</style>
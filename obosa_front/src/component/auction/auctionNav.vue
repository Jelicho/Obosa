<template>
  <v-toolbar class="header" height="64px" flat>
    <v-toolbar-items>
      <v-btn text @click="to('endDate')" class="nav-link" id='count'>인기 순</v-btn>
      <v-btn text @click="to('endDate')" class="nav-link" id='endDate'>마감 임박</v-btn>
      <v-btn text @click="to('registeredDate')" class="nav-link" id='registeredDate'>신규 순</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
export default {
  name: "auctionNav",
  watch: {
    $route(to){
      console.log(to.params.sortby);
    }
  },
  mounted(){
    $('#'+this.$route.params.sortby).addClass('btn-active')
  },
  methods: {
    to(params) {
      for (const active of $('.header .btn-active')) {
        $(active).removeClass('btn-active')
      }
      $('#'+params).addClass('btn-active')
      this.$router.push({
        name: "auction.list",
        params: {
          sortby: params
        }
      });
    }
  }
};
</script>

<style>
</style>
<template>
  <v-card class="auction-description" outlined color="grey lighten-3" width="100%">
    <v-card-title>{{subtitle}}</v-card-title>
    <v-card-text>{{description}}</v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "auctionDescription",
  props: ["subtitle", "description"]
};
</script>

<style>
</style>
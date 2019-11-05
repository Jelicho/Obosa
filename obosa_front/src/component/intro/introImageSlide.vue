<template>
  <v-carousel
    class="intro-view"
    show-arrows-on-hover
    hide-delimiter-background
    cycle
  >
    <v-carousel-item v-for="(item,i) in images" :key="i" :src="item"></v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  name: "introImageSlide",
  data() {
    return {
      images: []
    };
  },
  mounted() {
    for(let i = 1; i <= 6; i++){
      this.images.push(`${INTRO_BASE_URL}/introImg${i}.jpg`);
    }
  },
};
</script>

<style>
</style>
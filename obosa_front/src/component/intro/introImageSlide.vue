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
    this.images.push(INTRO_BASE_URL + "/introImg1.jpg");
    this.images.push(INTRO_BASE_URL + "/introImg2.jpg");
    this.images.push(INTRO_BASE_URL + "/introImg3.jpg");
    this.images.push(INTRO_BASE_URL + "/introImg4.jpg");
    this.images.push(INTRO_BASE_URL + "/introImg5.jpg");
    this.images.push(INTRO_BASE_URL + "/introImg6.jpg");
  },
};
</script>

<style>
</style>
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
    this.images.push("https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/intro/introImg1.jpg");
    this.images.push("https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/intro/introImg2.jpg");
    this.images.push("https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/intro/introImg3.jpg");
    this.images.push("https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/intro/introImg4.jpg");
    this.images.push("https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/intro/introImg5.jpg");
    this.images.push("https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/intro/introImg6.jpg");
  },
};
</script>

<style>
</style>
<template>
  <v-carousel cycle interval="2000">
    <!-- S3에서 product의 이미지를 가져옵니다. -->
    <v-carousel-item v-for="(productImg, i) in imageList" :src="productImg" :key="i">
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
    name : 'imageSlide',
    props : ['imageList']
};
</script>

<style>
</style>
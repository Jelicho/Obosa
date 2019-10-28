<template>
  <v-carousel cycle interval="2000">
    <!-- S3에서 product의 이미지를 가져옵니다. -->
    <v-carousel-item v-for="(productImg, i) in imageList" :key="productImg">
      <v-sheet :color="productImg" height="100%" tile>
        <v-row class="fill-height" align="center" justify="center">
          <div class="display-3">Slide {{ i + 1 }}</div>
        </v-row>
      </v-sheet>
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
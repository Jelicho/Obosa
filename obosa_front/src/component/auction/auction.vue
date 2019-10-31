<template>
  <router-link :to="{name: 'auction.detail' , params : { auction: auction }}">
    <v-card height="auto" outlined>
      <v-img class="item-img" :src="imgsrc(productImg)" />
      <v-card-title>{{auction.product.pname}}</v-card-title>
      <v-card-subtitle>{{auction.uid}}</v-card-subtitle>
      <v-progress-linear :value="progressDate" :color="progressColor"></v-progress-linear>
      <v-card-text>
        <p>마감일자 : {{auction.endDate}}</p>
        <br />
        남은시간 : {{calculateDate(auction.registeredDate, auction.endDate)}}
      </v-card-text>
    </v-card>
  </router-link>
</template>

<script>
import auctionDetail from "@/component/auction/auctionDetail";

export default {
  name: "auction",
  components: {
    auctionDetail
  },
  props: ["auction", "height"],
  data() {
    return {
      productImg: "",
      progressDate: 0,
      progressColor: "amber"
    };
  },
  methods: {
    imgsrc(src) {
      return "https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/user/default_user_img.png";
    },
    calculateDate(start, end) {
      var startDate = new Date(start).getTime();
      var now = new Date().getTime();
      var endDate = new Date(end).getTime();

      this.progressDate = ((now - startDate) / (endDate - startDate)) * 100;

      if (100 <= this.progressDate) {
        this.progressColor = "red";
      }

      var diff = endDate - now;

      // 만약 종료일자가 지났다면 "경매 마감"을 표시한다.
      if (diff < 0) {
        return "경매 마감";
      } else {
        var d = new Date(diff);
        var days = d.getDate();
        var hours = d.getHours();
        var minutes = d.getMinutes();

        return days + "일 " + hours + "시간 " + minutes + "분";
      }
    }
  }
};
</script>

<style>
</style>
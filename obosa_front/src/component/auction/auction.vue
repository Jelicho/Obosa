<template style="position:relative">
    <v-hover v-slot:default="{ hover }">
    <v-card  outlined @click="toDetail" >
      <div v-if="hover & closed" class="non_click" ><div>이미 마감된<br/> 경매입니다</div></div>
      <v-img width="100%" :height="height" class="item-img" :src="auction.product.imgCount === 0 ? imgsrc() : productImg" />
      <v-card-title>{{auction.product.pname}}</v-card-title>
      <v-card-subtitle>{{auction.uid}}</v-card-subtitle>
      <v-progress-linear :value="progressDate" :color="progressColor"></v-progress-linear>
      <v-card-text>
        <p>마감일자 : {{auction.endDate}}</p>
        <p class="price" style="color:red">{{auction.highPrice}}</p>
        <br />
        남은시간 : {{calculateDate(auction.registeredDate, auction.endDate)}}
      </v-card-text>
    </v-card>
    </v-hover>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: "auction",
  props: ["auction", "height"],
  data() {
    return {
      closed: false,
      productImg: PRODUCT_IMG_BASE_URL + "/" + this.auction.product.user.uid+"/"+this.auction.product.dirS3 + "/1",
      progressDate: 0,
      progressColor: "success"
    };
  },
  methods: {
    ...mapActions("auctionModule", [ "getAuctionDetail" ]),
    imgsrc() {
      return DEFAULT_IMG_BASE_URL + "/product.png";
    },
    calculateDate(start, end) {
      var startDate = new Date(start).getTime();
      var now = new Date().getTime();
      var endDate = new Date(end).getTime();

      this.progressDate = ((now - startDate) / (endDate - startDate)) * 100;

      if (100 <= this.progressDate) {
        this.closed = true;
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
    },
    async toDetail(){
      if(this.closed) return;

      await this.getAuctionDetail(this.auction.aid)

      this.$router.push({name: "auction.detail"})
    }
  }
};
</script>

<style>

</style>
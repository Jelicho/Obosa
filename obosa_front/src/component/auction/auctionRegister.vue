<template>
  <div>
    <v-row full-width justify="center" class="register_title">경매 등록하기</v-row>
    <v-divider class="pb-8"/>
    <v-row align="start">
      <v-col md="4">
        <v-row align="center" class="pb-5">
          <h1 class="auction-detail-title">{{product.pname}}</h1>
        </v-row>
        <v-row class="pb-5">
          <imageSlide :imageList="productImgList" />
        </v-row>
        <v-row>
          <auctionDescription subtitle="상품 설명" :description="product.pdescription" />
        </v-row>
      </v-col>
      <v-col md="8" class="pl-10">
        <v-row class="pb-5">
          <v-col cols="12" lg="8">
            <v-date-picker
              v-model="dates"
              color="#dc143c"
              no-title
              range
              width="100%"
              :min="today"
            ></v-date-picker>
          </v-col>
          <v-col cols="12" lg="4">
            <v-row class="auction-sub-title" justify="center">
              <p>경매 마감 일시</p>
              <p class="end_date">{{dates[1]}}</p>
            </v-row>
            <v-row>
              <v-select hide-details outlined class="time-select" :items="hours" filled></v-select>
              <p class="auction-sub-title">시</p>
            </v-row>
          </v-col>
        </v-row>
        <v-row class="pb-5">
          <v-textarea label="경매 설명"  outlined no-resize hide-details v-model="auction.description"></v-textarea>
        </v-row>
        <v-row class="pb-5">
          <v-text-field label="최저가 설정"  
            outlined
            hide-details
            type="number"
            min="1000"
            v-model="auction.lowPrice"
            step="1000"
          ></v-text-field>
        </v-row>
        <v-row class="pb-5">
          <v-text-field label="최소 입찰액 단위"  
            outlined
            hide-details
            type="number"
            min="1000"
            v-model="auction.upPrice"
            step="1000"
          ></v-text-field>
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-btn @click="registerRequest()" depressed large block color="primary" height="80px">경매 등록하기</v-btn>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import auctionDescription from "@/component/auction/auctionDescription";
import imageSlide from "@/component/common/imageSlide";

export default {
  name: "auctionRegister",
  data() {
    return {
      product: {},
      productImgList: [],
      dates: [],
      today: "",
      hours: [],
      startHours: 1,
      auction: {
        pid: "",
        lowPrice: 1000,
        description: "",
        endDate: "",
        upPrice: 1000
      }
    };
  },
  components: {
    auctionDescription,
    imageSlide
  },
  computed: {
    ...mapState({
      response: state => state.auctionModule.auctionResponse
    })
  },
  watch: {
    response(response) {
      console.log(response);
      if (response.status == 200) {
        this.$router.push({
          name: 'home'
        });
      } else {
        this.alert.message = response.message;
        this.alert.type = "error";
        this.alert.isAlert = true;
      }
    },
    dates(to) {
      if (to[1] == null) {
        this.dates[1] = this.dates[0];
        this.dates[0] = this.today;
      }
      if (this.dates[0] == this.dates[1]) {
        this.startHours = new Date().getHours() + 1;
      } else {
        this.startHours = 1;
      }
    },
    startHours(startHours) {
      this.hours = this.range(startHours, 24)
    }
  },
  mounted() {
    this.today = this.dateformat(new Date());
    this.dates[0] = this.today;
    this.dates[1] = this.today;
    this.startHours = new Date().getHours() + 1

    this.product = this.$route.params.product;
    // this.product = {
    //   pid: 2,
    //   pname: "백만송이 장미",
    //   pdescription: "아주 좋은 장미",
    //   uid: "3",
    //   registeredDate: "2019-10-18",
    //   modifiedDate: null,
    //   imgCount: 2
    // };
  },
  methods: {
    ...mapActions("auctionModule", ["createAuction"]),
    dateformat(date) {
      return (
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + ("00"+date.getDate()).slice(-2)
      );
    },
    registerRequest() {
      (this.auction.pid = this.product.pid), (this.auction.endDate = this.dates[1]);

      var regiFormData = new FormData();

      for (var key in this.auction) {
        regiFormData.append(key, this.auction[key]);
      }

      this.createAuction(regiFormData);
    },
    range(start, end){
      return Array(end - start + 1).fill().map((_, idx) => start + idx)
    }
  }
};
</script>

<style>
</style>
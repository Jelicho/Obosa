<template>
  <div>
    <v-row full-width >경매 등록하기</v-row>
    <v-row align="start" >
      <v-col md="6">
        <v-row align="center">
          <h1 class="auction-detail-title">{{product.pname}}</h1>
        </v-row>
        <v-row>
          <imageSlide :imageList="productImgList" />
        </v-row>
        <v-row>
          <auctionDescription subtitle="상품 설명" :description="product.pdescription" />
        </v-row>
      </v-col>
      <v-col md="6">
        <v-row>
          <v-col cols="12" lg='8'>
            <v-date-picker v-model="dates" color="amber" no-title range width="100%"
            :min="today"></v-date-picker>
          </v-col>
          <v-col cols="12" lg='4'>
            <v-row >
              <p class="auction-sub-title">시작</p>
              <v-text-field :value="dates[0]" width="80%" outlined hide-details disabled ></v-text-field>  
            </v-row>
            <v-row>
              <v-select hide-details outlined class='time-select' :items="hours" filled ></v-select>
              <p class="auction-sub-title">시</p>
              <v-select hide-details outlined class='time-select' :items="minutes" filled ></v-select>
              <p class="auction-sub-title">분</p>
            </v-row>
            <v-row class="auction-sub-title">
              <p class="auction-sub-title">마감</p>
              <v-text-field :value="dates[1]" outlined hide-details disabled ></v-text-field>  
            </v-row>
            <v-row>
              <v-select hide-details outlined class='time-select' :items="hours" filled ></v-select>
              <p class="auction-sub-title">시</p>
              <v-select hide-details outlined class='time-select' :items="minutes" filled ></v-select>
              <p class="auction-sub-title">분</p>
            </v-row>
          </v-col>
        </v-row>
        <v-row>
          <v-textarea outlined no-resize  hide-details v-model="auction.description"></v-textarea>
          <v-text-field outlined hide-details type='number' min='1000' v-model="auction.lowPrice"></v-text-field>  
          <v-text-field outlined hide-details type='number' min='1000' v-model="auction.upprice"></v-text-field>  
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-btn @click="registerRequest()" depressed large block color="primary" height="80px">경매 등록하기</v-btn>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import auctionDescription from '@/component/auction/auctionDescription'
import imageSlide from '@/component/common/imageSlide'

export default {
  name: "auctionRegister",
  data() {
    return {
      product: {},
      productImgList: [],
      dates: [],
      today: '',
      hours: ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24'],
      minutes: ['00', '15', '30', '45'],
      auction: {
        pid : '',
        lowPrice: 1000,
        description : '',
        endDate: ''
      }
    };
  },
  components: {
    auctionDescription, imageSlide
  },
  computed: {
    ...mapState({
      response : state => state.auctionModule.auctionResponse
    })
  },
  watch: {
    response(){
      console.log(this.response);
    }
  },
  mounted() {
    this.today = this.dateformat(new Date())
    // this.product = this.$route.params.product;
    this.product = {
      pid: 2,
      pname: "백만송이 장미",
      pdescription: "아주 좋은 장미",
      uid: "3",
      registeredDate: "2019-10-18",
      modifiedDate: null,
      imgCount: 2
    };
    this.productImgList = [
        "primary",
        "error",
        "warning",
        "success",
        "default"
      ];
  },
  methods: {
    ...mapActions('auctionModule', ['createAuction']),
    dateformat(date){
      return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()    
    },
    registerRequest(){
      this.auction.pid = this.product.pid,
      this.auction.endDate = this.dates[1]
      console.log(this.auction);
      this.createAuction(this.auction)
    }
  }
};
</script>

<style>
.time-select {
  width: 35%;
  border-radius: 0px;
  background-color: #fff5;
  margin: 5px;
}
</style>
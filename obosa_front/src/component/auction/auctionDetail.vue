<template>
  <div>
    <v-row class="auction-detail-container" align="center">
      <v-col md="6">
        <v-row>
          <imageSlide :imageList="productImgList" />
        </v-row>
      </v-col>
      <v-col md="6" class="auction-detail-info">
        <v-row align="center">
          <h1 class="auction-detail-title">{{auction.product.pname}}</h1>
        </v-row>
        <v-row>
          <auctionDescription subtitle="상품 설명" :description="auction.product.pdescription" />
          <auctionDescription subtitle="경매 설명" :description="auction.description" />
        </v-row>
        <v-row>
          <v-col md="4">
            <p class="auction-sub-title">경매 시작가</p>
            <div class="price price_low">{{auction.lowPrice}}</div>
          </v-col>
          <v-col md="8">
            <p class="auction-sub-title">현재 최고가</p>
            <div class="price price_high">{{auction.highPrice}}</div>
          </v-col>
        </v-row>
        <v-row>
          <v-col md="8">
            <!-- 현재 경매의 최고경매가를 min 값으로 지정한다. (현재는 Redis와 연결하지 않아서 lowPrice로 설정) -->
            <v-text-field
              outlined
              type="number"
              v-model="bidPrice"
              :min="minbidPrice"
              :step="auction.upPrice"
              height="80px"
              class="price"
              color="#994fa980"
              :hint="auction.upPrice+'원 단위로 입찰할 수 있습니다!'"
              persistent-hint
            ></v-text-field>
          </v-col>
          <v-col md="4" style="align-self: start!important;">
            <v-btn depressed large tile color="#ff0000d4" class="white--text" height="80px" @click="bidRequest()">
              내가 현재 최고가로
              <br />
              <h2 style="display:contents;">입찰하기</h2>
            </v-btn>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import auctionDescription from "@/component/auction/auctionDescription";
import imageSlide from '@/component/common/imageSlide'

export default {
  name: "auctionDetail",
  components: {
    auctionDescription, imageSlide
  },
  data() {
    return {
      auction: { product: { pname: "" } },
      minbidPrice: 0,
      bidPrice: 0,
      productImgList: []
    };
  },
  mounted() {
    // 경매 리스트를 router paramerter로 받아옴
    this.auction = this.$route.params.auction;

    // 최소 입찰 가격을 지정함
    this.minbidPrice = this.auction.highPrice + this.auction.upPrice;
    this.bidPrice = this.minbidPrice;

    // 상품 이미지 리스트를 가져옴
    this.getProductImgList(
      this.auction.product.user.uid,
      this.auction.product.dirS3,
      this.auction.product.imgCount
      
    );
  },
  methods: {
    ...mapActions("auctionModule", ["bidAuction"]),
    ...mapActions("webSocketModule", ["updatePrice"]),
    getProductImgList(uid,dirS3, count) {
      if (count == 0) {
        this.productImgList = [DEFAULT_IMG_BASE_URL + "/product.png"];
      } else {
        const baseUrl = PRODUCT_IMG_BASE_URL + "/" + uid + "/" + dirS3 + "/";
        for (var i = 1; i <= count; i++) {
          this.productImgList.push(baseUrl + i);
        }
      }
    },
    bidRequest(){
      let formData = new FormData();
      
      formData.append('aid', this.auction.aid)
      formData.append('bidPrice', Number(this.bidPrice))
      this.bidAuction(formData)
        .then(result =>{
            if(result == true){
                console.log('hi 성공했음')
                this.updatePrice(this.auction.aid);
            }
        })
    }
  }
};
</script>

<style>
</style>
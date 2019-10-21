<template>
  <div class="item-list">
    <v-layout row wrap pa-5>
      <v-flex pa-5 xs12 sm6 md4 lg3 v-for="auction in auctions" v-bind:key="auction.aid">
        <auction :auction="auction" :height="300" />
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import auction from "@/component/auction/auction";

export default {
  name: "auctionList",
  components: {
    auction
  },
  data() {
    return {
      auctions: [
        {
          aid: 2,
          product: {
            pid: 2,
            pname: "백만송이 장미",
            pdescription: "아주 좋은 장미",
            uid: "3",
            registeredDate: "2019-10-18",
            modifiedDate: null,
            imgCount: 2
          },
          uid: 3,
          lowPrice: 2000,
          description: "아주 좋은 물건",
          registeredDate: "2019-10-18",
          endDate: "2019-10-21",
          state: 0,
          upprice: 1000
        },
        {
          aid: 3,
          product: {
            pid: 1,
            pname: "에어컨",
            pdescription: "세상 추워 에어컨",
            uid: "3",
            registeredDate: "2019-10-15",
            modifiedDate: null,
            imgCount: 3
          },
          uid: 3,
          lowPrice: 45000,
          description: "애끼던 물건",
          registeredDate: "2019-10-18",
          endDate: "2019-10-22",
          state: 0,
          upprice: 1000
        },
        {
          aid: 4,
          product: {
            pid: 3,
            pname: "류수한",
            pdescription: "류수한 팝니다.",
            uid: 1,
            registeredDate: "2019-10-18",
            modifiedDate: null,
            imgCount: 5
          },
          uid: 1,
          lowPrice: 18000,
          description: "3대째 내려오는 가보",
          registeredDate: "2019-10-19",
          endDate: "2019-10-20",
          state: 0,
          upprice: 2000
        }
      ]
    };
  },
  mounted() {
    this.getAuctionListOrderBy(this.$route.params.sortby);
  },
  watch: {
    $route(to) {
      this.getAuctionListOrderBy(to.params.sortby);
    }
  },
  methods: {
    getAuctionListOrderBy(sortby) {
      // 경매 리스트를 정렬하여 받아오는 로직
      // this.auctions = $autioService.getAuctionListOrderBy(to.params.sortby);
      console.log(sortby);
    }
  }
};
</script>

<style>
.item-list {
  padding: 20px;
}
</style>
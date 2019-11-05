<template>
  <v-row class="intro-view intro-item">
    <v-col cols="12" class="statistics">
      OBOSA에는 <p class="count">{{userCount}}</p> 명의 회원이 있습니다.<br/>
      OBOSA에는 <p class="count">{{productCount}}</p> 개의 상품이 있습니다.<br/>
      OBOSA에는 <p class="count">{{auctionCount}}</p> 건의 경매가 있습니다.<br/>
      OBOSA는 <p class="count">{{winningBidCount}}</p> 건의 거래를 성사시켰습니다.<br/>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "introStatistics",
  data() {
    return {
      userCount: 0,
      productCount: 0,
      auctionCount: 0,
      winningBidCount: 0
    };
  }
};
</script>

<style>
.statistics {
    margin-top: 120px;
    background-color: rgba(211, 216, 171, 0.452);
    font-size: 30px;
    font-weight: 600;
    text-align: left;
    padding-left: 5%;
    color:rgba(139, 141, 125, 0.746)
}
.count {
    border-bottom: 2px solid rgba(47, 47, 45, 0.746);
    display: inline-block;
    padding: 4px;
    font-size: 40px;
    font-weight: 900;
    color:rgba(47, 47, 45, 0.746)
}
</style>
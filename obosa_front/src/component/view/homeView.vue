<template>
  <div>
    <div id="main-overview" class="container">
        <introImageSlide />
        <introTextCard />
        <introAuctionList />
        <introStatistics />
    </div>
  </div>
</template>

<script>
import introImageSlide from '@/component/intro/introImageSlide'
import introTextCard from '@/component/intro/introTextCard'
import introAuctionList from '@/component/intro/introAuctionList'
import introStatistics from '@/component/intro/introStatistics'

export default {
  name: "Home",
  components:{
    introImageSlide, introTextCard, introAuctionList, introStatistics
  }
};
</script>

<style>
</style>
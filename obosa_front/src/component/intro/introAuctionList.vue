<template>
  <v-sheet class="intro-view intro-item">
    <v-slide-group show-arrows>
      <v-slide-item v-for="auction in auctionList" :key="auction.aid">
        <introAuction :auction="auction" />
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import introAuction from "@/component/intro/introAuction";

export default {
  name: "introAuctionList",
  components: {
    introAuction
  },
  data() {
    return {
      params: {
        searchStr: "",
        sort: "registeredDate",
        page: 0,
        size: 10
      }
    };
  },
  computed: {
    ...mapState({
      auctionList: state => state.auctionModule.auctionList
    })
  },
  mounted() {
    this.auctionRequest();
  },
  methods: {
    ...mapActions("auctionModule", ["getAuctionList"]),
    async auctionRequest() {
      await this.getAuctionList(this.params);
      this.slide();
    },
    slide() {
      var maxTranslateX =
        -$(".v-slide-group__content")
          .css("width")
          .slice(0, -2) + window.innerWidth;
      var translateX = -400;

      setInterval(() => {
        $(".v-slide-group__content").css(
          "transform",
          "translateX(" + translateX + "px)"
        );
        translateX -= 400;
        if (translateX < maxTranslateX) {
          translateX = -400;
        }
      }, 1500);
    }
  }
};
</script>

<style>
.v-slide-group__content > a {
  padding-right: 30px;
}
.v-slide-group__prev,
.v-slide-group__next {
  display: none;
}
</style>
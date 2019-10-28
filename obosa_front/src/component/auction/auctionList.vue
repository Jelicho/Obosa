<template>
  <div class="item-list">
    <v-row>
      <v-col xs="12" sm="4" >
        <v-select append-icon="fas fa-caret-down" class="search" color="amber" item-color="amber" :items="items" outlined v-model="params.type" hide-details></v-select>
      </v-col>
      <v-col xs="12" sm="8" >
        <v-text-field class="search" color="amber" background-color="#fff0" outlined clearable hide-details v-model="params.searchStr" @keyup.enter="auctionRequest"></v-text-field>
      </v-col>
    </v-row>
    <v-layout row wrap pa-5>
      <v-flex pa-5 xs12 sm6 md4 lg3 v-for="auction in auctionList" >
        <auction :auction="auction" :height="300" />
      </v-flex>
      <v-flex pa-5 xs12 sm6 md4 lg3>
        <loadingbox v-if="hasMoreAuctions" :height="430" />
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import auction from "@/component/auction/auction";
import loadingbox from '@/component/common/loadingbox'

export default {
  name: "auctionList",
  components: {
    auction,
    loadingbox
  },
  data() {
    return {
      params: {
        type: "pname",
        searchStr: "",
        sort: "",
        page: 0,
        size: 10
      },
      isEnd: false,
      items: [ 
        { value : 'pname', text : '상품이름' }, 
        { value : 'nickname', text : '판매자'}]
    };
  },
  mounted() {
    this.params.sort = this.$route.params.sortby;
    this.onscroll();
    this.paramsInit()
  },
  watch: {
    $route(to) {
      this.params.sort = to.params.sortby;
      this.paramsInit()
    },
    isEnd() {
      if(this.isEnd) this.auctionRequest(this.params)
    }
  },
  computed: {
    ...mapState({
      auctionList: state => state.auctionModule.auctionList,
      hasMoreAuctions: state => state.auctionModule.hasMoreAuctions
    })
  },
  methods: {
    ...mapActions("auctionModule", ["getAuctionList", "getAuctionListByUser"]),
    ...mapMutations("auctionModule", ["setHasMoreAuctions"]),
    async auctionRequest() {
      if ( (this.params.page == 0 || this.isEnd) && this.hasMoreAuctions) {
        await this.getAuctionList(this.params);
        this.params.page ++;
        this.params.size = 4;
      }
    },
    onscroll(){
      let _this = this
      $(window).scroll( e => {
        let $window = $(window)
        let scrollTop = $window.scrollTop()
        let windowHeight = $window.height()
        let documentHeight = $(document).height();
        _this.isEnd = scrollTop + windowHeight + 10 > documentHeight ? true : false
      })
    },
    paramsInit(){
      this.params.type = "pname"
      this.params.searchStr = ""
      this.params.page = 0
      this.params.size = 10
      this.setHasMoreAuctions(true)
      this.auctionRequest()
    }
  }
};
</script>

<style>
.item-list {
  padding: 20px;
}
.search {
  border-radius: 0px;
}
.search .v-select__selection, .v-text-field__slot {
  font-weight: bold;
  font-size: 19px;
  text-align: center;
}
</style>
<template>
  <div>
    <v-nav></v-nav>
    <v-breadcrumb title="Search Auction" description="주소로 경매 정보를 검색할 수 있습니다."></v-breadcrumb>
    <div class="container">
      <explorer-nav></explorer-nav>
      <div class="row">
        <div class="col-md-12">
          <div class="col-md-12 search-form">
            <div class="card shadow-sm">
              <div class="card-header">검색</div>
              <div class="card-body">
                <form @submit.prevent="searchEther()">
                  <div class="input-group">
                    <input
                      v-model="searchValue"
                      type="text"
                      class="form-control"
                      placeholder="경매 주소 해시"
                    />
                    <div class="input-group-append">
                      <button
                        class="btn btn-primary"
                        type="button"
                        @click.prevent="searchEther()"
                      >검색</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ExplorerAddressSearchView",
  data() {
    return {
      searchValue: ""
    };
  },
  methods: {
    searchEther() {
      if (this.searchValue === "") return;

      if (this.isHash(this.searchValue)) {
        this.$router.push({
          name: "explorer.auction.detail",
          params: { contractAddress: this.searchValue }
        });
      } else {
        alert("올바른 형식의 해쉬값이 아닙니다.");
      }
    },
    isHash(value) {
      return RegExp(/^(0x)?([A-Fa-f0-9]{40})$/).test(value);
    }
  },
  mounted() {}
};
</script>

<style>
</style>
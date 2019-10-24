<template>
  <v-dialog v-model="show" width="600px">
    <v-card class="address-popup">
      <vue-daum-postcode v-if="!isLoad" @complete="result = $event" />
      <v-col sm="12">
        <div v-if="isLoad">
          <v-row>
            <v-col sm="5">우편번호</v-col>
            <v-col sm="7">
              <v-text-field
                full-width
                single-line
                v-model="fulladdress.code"
                background-color="#f4f8f7"
                color="grey darken-2"
                hide-details
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col sm="5">기본주소</v-col>
            <v-col sm="7">
              <v-text-field
                full-width
                single-line
                v-model="fulladdress.address"
                background-color="#f4f8f7"
                color="grey darken-2"
                hide-details
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col sm="5">상세주소</v-col>
            <v-col sm="7">
              <v-text-field
                full-width
                single-line
                v-model="fulladdress.detail"
                background-color="#f4f8f7"
                color="grey darken-2"
                hide-details
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-btn block rounded large dark ripple color="teal" @click="sendAddress()">주소등록</v-btn>
          </v-row>
        </div>
      </v-col>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "addressPopUp",
  props: ["dialog"],
  data() {
    return {
      show: false,
      result: "",
      fulladdress: {
        code: "",
        address: "",
        detail: ""
      },
      isLoad: false
    };
  },
  components: {},
  watch: {
    result() {
      this.fulladdress.code = this.result.postcode;
      this.fulladdress.address = this.result.address;
      this.isLoad = true;
    },
    dialog() {
      this.show = this.dialog;
    }
  },
  methods: {
    sendAddress() {
      this.$emit("update:address", this.fulladdress);
      this.fulladdress.code = "";
      this.fulladdress.address = "";
      this.fulladdress.detail = "";
      this.isLoad = false;
      this.show = false;
    }
  }
};
</script>
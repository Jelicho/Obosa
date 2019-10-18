<template>
  <v-dialog v-model="show" width="600px">
    <v-card>
      <vue-daum-postcode v-if="!isLoad" @complete="result = $event" />
      <div v-if="isLoad">
        {{fulladdress.code}}
        {{fulladdress.address}}
        <v-text-field
          full-width
          single-line
          label="5678"
          v-model="fulladdress.detail"
          background-color="#f4f8f7"
          color="grey darken-2"
        ></v-text-field>
        {{fulladdress.detail}}
        <v-btn block rounded large dark ripple color="teal" @click="sendAddress()">주소등록</v-btn>
      </div>
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
    dialog(){
      this.show = this.dialog
    }
  },
  methods: {
    sendAddress() {
      this.$emit("update:address", this.fulladdress.address + " " + this.fulladdress.detail)
      this.show = false;
    }
  }
};
</script>
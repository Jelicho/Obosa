<template>
  <div>
    <v-container class="container">
      <div id="register-form" class="col-md-10 mx-auto bg-white">
        <v-row>
          <v-col sm="6" align="center">
            <v-img :src="imgsrc(user.png)" width="300" height="300"></v-img>
            <v-btn rounded depressed color="#FDD835" @click="upload()">
              업로드
              <input type="file" style="display:none" id="profile" accept=".gif, .jpg, .png" />
            </v-btn>
          </v-col>
          <v-col sm="6" class="input-form">
            <v-row>
              <v-col>
                <logo />
              </v-col>
            </v-row>
            <v-row>
              <v-col md="9">
                <v-text-field
                  full-width
                  single-line
                  label="이메일"
                  v-model="user.email"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  :rules="[rules.required, rules.email]"
                ></v-text-field>
              </v-col>
              <v-col md="3" style="align-self:baseline!important">
                <v-btn height="56px" width="100%" depressed color="error" error>인증</v-btn>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  full-width
                  single-line
                  label="닉네임"
                  v-model="user.nickname"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  :rules="[rules.required , rules.nickname]"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  full-width
                  single-line
                  type="password"
                  label="비밀번호"
                  v-model="user.password"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  :rules="[rules.required, rules.password]"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  full-width
                  single-line
                  type="password"
                  label="비밀번호 확인"
                  v-model="user.passwordConfirm"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  :rules="[rules.required, rules.pwConfirm]"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col @click="openapi()">
                <v-text-field
                  full-width
                  single-line
                  label="우편번호"
                  v-model="user.zipcode"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  disabled
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col @click="openapi()">
                <v-text-field
                  full-width
                  single-line
                  label="주소"
                  v-model="user.address"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  disabled
                ></v-text-field>
              </v-col>
            </v-row>
            <addressPopUp :dialog="addressDialog" @update:address="setAddress" />
            <v-row>
              <v-col sm="3">
                <v-text-field
                  full-width
                  single-line
                  label="010"
                  v-model="user.phone.no1"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  type="number"
                  class="no-arrow"
                ></v-text-field>
              </v-col>
              <v-col sm="1">-</v-col>
              <v-col sm="3">
                <v-text-field
                  full-width
                  single-line
                  label="1234"
                  v-model="user.phone.no2"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  type="number"
                  class="no-arrow"
                ></v-text-field>
              </v-col>
              <v-col sm="1">-</v-col>
              <v-col sm="3">
                <v-text-field
                  full-width
                  single-line
                  label="5678"
                  v-model="user.phone.no3"
                  background-color="#f4f8f7"
                  color="grey darken-2"
                  type="number"
                  class="no-arrow"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-btn
                  block
                  rounded
                  large
                  dark
                  ripple
                  color="teal"
                  id="sign-up"
                  @click="register()"
                >회원가입</v-btn>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </div>
    </v-container>
  </div>
</template>

<script>
import addressPopUp from "@/component/api/addressPopUp";

export default {
  name: "Register",
  data() {
    return {
      addressDialog: false,
      user: {
        email: "",
        nickname: "",
        password: "",
        passwordConfirm: "",
        zipcode: "",
        address: "",
        phone: {
          no1: "",
          no2: "",
          no3: ""
        }
      },
      rules: {
        required: value => !!value || "입력해주세요",
        nickname: value =>
          (2 <= value.length && value.length <= 6) ||
          "2자 이상, 6자 이하로 입력해주세요",
        password: value =>
          (8 <= value.length && value.length <= 12) ||
          "8자 이상, 12자 이하로 입력해주세요",
        email: value => /.+@.+\..+/.test(value) || "이메일 형식을 지켜주세요",
        pwConfirm: value =>
          value == this.user.password || "비밀번호를 확인해주세요"
      },
      message: ""
    };
  },
  components: {
    addressPopUp
  },
  watch: {
    user() {
      console.log(this.user.address);
    }
  },
  methods: {
    register: function() {
      var scope = this;

      console.log(this.user);

      if (this.user.password === this.user.passwordConfirm) {
        this.$userService.signUp(
          this.user.email,
          this.user.name,
          this.user.password,
          function(response) {
            alert("회원가입이 완료되었습니다.");
            scope.$router.push("/");
          }
        );
      } else {
        alert("비밀번호가 일치하지 않습니다.");
      }
    },
    imgsrc(src) {
      return require("@/assets/user.png");
    },
    upload() {
      $("#profile").trigger("click");
    },
    openapi() {
      this.addressDialog = !this.addressDialog;
    },
    setAddress(fulladdress) {
      this.user.address = fulladdress.address + ", " + fulladdress.detail;
      this.user.zipcode = fulladdress.code;
      this.addressDialog = false;
    }
  }
};
</script>

<style>
</style>
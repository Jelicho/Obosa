<template>
  <div>
    <v-form ref="regiform" valid>
      <v-container class="container">
        <div id="register-form" class="col-md-10 mx-auto bg-white">
          <v-row>
            <v-col sm="6" align="center">
              <v-img :src="profilePreview" width="300" height="300"></v-img>
              <v-btn rounded depressed color="#FDD835" @click="upload()">
                업로드
                <input type="file" style="display:none" accept=".gif, .jpg, .png" id="profile" @change="onFileChange"/>
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
                    required
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
                    required
                    full-width
                    single-line
                    label="이름"
                    v-model="user.name"
                    background-color="#f4f8f7"
                    color="grey darken-2"
                    :rules="[rules.required]"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-text-field
                    required
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
                    required
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
                    required
                    full-width
                    single-line
                    type="password"
                    label="비밀번호 확인"
                    v-model="passwordConfirm"
                    background-color="#f4f8f7"
                    color="grey darken-2"
                    :rules="[rules.required, rules.pwConfirm]"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col @click="openapi()">
                  <v-text-field
                    required
                    full-width
                    single-line
                    label="우편번호"
                    v-model="user.zipCode"
                    background-color="#f4f8f7"
                    color="grey darken-2"
                    disabled
                    :rules="[rules.required]"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col @click="openapi()">
                  <v-text-field
                    required
                    full-width
                    single-line
                    label="주소"
                    v-model="user.address"
                    background-color="#f4f8f7"
                    color="grey darken-2"
                    disabled
                    :rules="[rules.required]"
                  ></v-text-field>
                </v-col>
              </v-row>
              <addressPopUp :dialog="addressDialog" @update:address="setAddress" />
              <v-row>
                <v-col sm="3">
                  <v-text-field
                    required
                    full-width
                    single-line
                    label="010"
                    v-model="no1"
                    :rules="[rules.required, rules.phone]"
                    background-color="#f4f8f7"
                    color="grey darken-2"
                    type="number"
                    class="no-arrow"
                  ></v-text-field>
                </v-col>
                <v-col sm="1">-</v-col>
                <v-col sm="3">
                  <v-text-field
                    required
                    full-width
                    single-line
                    label="1234"
                    v-model="no2"
                    :rules="[rules.required, rules.phone]"
                    background-color="#f4f8f7"
                    color="grey darken-2"
                    type="number"
                    class="no-arrow"
                  ></v-text-field>
                </v-col>
                <v-col sm="1">-</v-col>
                <v-col sm="3">
                  <v-text-field
                    required
                    full-width
                    single-line
                    label="5678"
                    v-model="no3"
                    :rules="[rules.required, rules.phone]"
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
    </v-form>
  </div>
</template>

<script>
import addressPopUp from "@/component/api/addressPopUp";

export default {
  name: "Register",
  data() {
    return {
      valid: true,
      addressDialog: false,
      profilePreview: require("@/assets/user.png"),
      user: {
        email: "",
        name: "",
        nickname: "",
        password: "",
        zipCode: "",
        address: "",
        phone: "",
        profileImg: ""
      },
      no1: "",
      no2: "",
      no3: "",
      passwordConfirm: "",
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
          value == this.user.password || "비밀번호를 확인해주세요",
        phone: value => value.length <= 4 || "전화번호를 정확히 입력해주세요."
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
  mounted(){
    this.profilePreview = require("@/assets/user.png")
  },
  methods: {
    register: function() {
      var scope = this;
      var regiFormData = new FormData();
      this.user.phone = this.no1 + "-" + this.no2 + "-" + this.no3;

      if (this.$refs.regiform.validate()) {
        for (var key in this.user) {
          regiFormData.append(key, this.user[key]);
        }
        console.log("send data");
        this.$userService.signUp(regiFormData)
      } else {
        this.snackbar = true;
      }
    },
    imgsrc(src) {
      return require("@/assets/user.png");
    },
    upload() {
      $("#profile").trigger("click");
    },
    onFileChange(e){
      var profile = e.target.files[0];
      this.user.profileImg = profile;
      console.log(profile);
      
      this.setPreview(profile);
    },
    setPreview(imgfile){
      var reader = new FileReader(0);
      var _this = this;
      reader.onload = (e) => {
        _this.profilePreview = e.target.result;
      }
      reader.readAsDataURL(imgfile);
    },
    openapi() {
      this.addressDialog = !this.addressDialog;
    },
    setAddress(fulladdress) {
      this.user.address = fulladdress.address + ", " + fulladdress.detail;
      this.user.zipCode = fulladdress.code;
      this.addressDialog = false;
    }
  }
};
</script>

<style>
</style>
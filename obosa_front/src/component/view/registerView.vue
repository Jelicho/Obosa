<template>
  <div>
    <v-container class="container">
      <v-form ref="regiform" valid>
        <v-row class="form-container" >
          <div class="col-md-5 text-container ">
            <v-img :src="profilePreview" style="margin:auto" width="300px" height="300"></v-img>
            <v-btn rounded depressed color="#FDD835" @click="upload()">
              업로드
              <input
                type="file"
                style="display:none"
                accept=".gif, .jpg, .png"
                id="profile"
                @change="onFileChange"
              />
            </v-btn>
          </div>
          <v-divider vertical />
          <div id="register-form" class="col-md-6 mx-auto bg-white">
            <v-row>
              <v-col cols="9">
                <v-text-field
                  required
                  outlined
                  class="input_text"
                  label="이메일"
                  v-model="user.email"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  :rules="[rules.required, rules.email]"
                ></v-text-field>
              </v-col>
              <v-col cols="3" style="align-self:baseline!important">
                <v-btn
                  height="56px"
                  width="100%"
                  tile
                  :color="emailDuplicated ? 'red lighten-2' : 'green lighten-2'"
                  @click="duplicateCheck('email')"
                >중복 확인</v-btn>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  required
                  outlined
                  class="input_text"
                  label="이름"
                  v-model="user.name"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  :rules="[rules.required]"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="9">
                <v-text-field
                  required
                  outlined
                  class="input_text"
                  label="닉네임"
                  v-model="user.nickname"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  :rules="[rules.required , rules.nickname]"
                ></v-text-field>
              </v-col>
              <v-col cols="3" style="align-self:baseline!important">
                <v-btn
                  height="56px"
                  width="100%"
                  tile
                  :color="nicknameDuplicated ? 'red lighten-2' : 'green lighten-2'"
                  @click="duplicateCheck('nickname')"
                >중복 확인</v-btn>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  required
                  outlined
                  class="input_text password"
                  type="password"
                  label="비밀번호"
                  v-model="user.password"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  :rules="[rules.required, rules.password]"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  required
                  outlined
                  type="password"
                  class="input_text password"
                  label="비밀번호 확인"
                  v-model="passwordConfirm"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  :rules="[rules.required, rules.pwConfirm]"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col @click="addressDialog = !addressDialog">
                <v-text-field
                  required
                  outlined
                  class="input_text"
                  label="우편번호"
                  v-model="user.zipCode"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  disabled
                  :rules="[rules.required]"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col @click="addressDialog = !addressDialog">
                <v-text-field
                  required
                  outlined
                  class="input_text"
                  label="주소"
                  v-model="user.address"
                  background-color="#f4f8f7"
                  color="#994fa980"
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
                  outlined
                  class="input_text no-arrow"
                  label="010"
                  v-model="no1"
                  :rules="[rules.required, rules.phone]"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  type="number"
                ></v-text-field>
              </v-col>
              <v-col sm="1">-</v-col>
              <v-col sm="3">
                <v-text-field
                  required
                  outlined
                  class="input_text no-arrow"
                  label="1234"
                  v-model="no2"
                  :rules="[rules.required, rules.phone]"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  type="number"
                ></v-text-field>
              </v-col>
              <v-col sm="1">-</v-col>
              <v-col sm="3">
                <v-text-field
                  required
                  outlined
                  class="input_text no-arrow"
                  label="5678"
                  v-model="no3"
                  :rules="[rules.required, rules.phone]"
                  background-color="#f4f8f7"
                  color="#994fa980"
                  type="number"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-btn
                  block
                  tile
                  large
                  dark
                  ripple
                  color="#994fa980"
                  id="sign-up"
                  @click="onSubmit()"
                >회원가입</v-btn>
              </v-col>
            </v-row>
          </div>
        </v-row>
      </v-form>
    </v-container>
    <v-snackbar v-model="alert.isAlert" middle="true" :color="alert.type" top>
      {{ alert.message }}
      <v-btn text @click="alert.isAlert = false">확인</v-btn>
    </v-snackbar>
  </div>
</template>

<script>
import {mapState, mapActions } from 'vuex'
import addressPopUp from "@/component/api/addressPopUp";

export default {
  name: "Register",
  data() {
    return {
      valid: true,
      addressDialog: false,
      emailDuplicated: true,
      nicknameDuplicated: true,
      profilePreview: "https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/user/default_user_img.png",
      user: {
        email: "",
        name: "",
        nickname: "",
        password: "",
        zipCode: "",
        address: "",
        phone: "",
        profileImg: "https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/user/default_user_img.png"
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
      alert: {
        isAlert: false,
        message: "",
        type: "success"
      },
    };
  },
  components: {
    addressPopUp
  },
  computed: {
    ...mapState({
      response : state => state.signupService.signupResponse
    })
  },
  watch: {
    response(){
      console.log(this.response);
      this.alert.message = this.response.message;
      this.alert.type = this.response.status == 200 ? 'success' : 'error'
      this.alert.isAlert = true;
    }
  },
  methods: {
    ...mapActions("signupModule", ["signup"]),
    ...mapActions("signupModule", ["duplicateEmail"]),
    ...mapActions("signupModule", ["duplicateNickname"]),
    async onSubmit() {
      var regiFormData = new FormData();

      if (this.$refs.regiform.validate()) {
        this.user.phone = this.no1 + "-" + this.no2 + "-" + this.no3;
        for (var key in this.user) {
          regiFormData.append(key, this.user[key]);
        }
        await this.signup(regiFormData)
      } 
    },
    upload() {
      $("#profile").trigger("click");
    },
    onFileChange(e) {
      var profile = e.target.files[0];
      this.user.profileImg = profile;
      this.setPreview(profile);
    },
    setPreview(imgfile) {
      var reader = new FileReader(0);
      var _this = this;
      reader.onload = e => {
        _this.profilePreview = e.target.result;
      };
      reader.readAsDataURL(imgfile);
    },
    setAddress(fulladdress) {
      this.user.address = fulladdress.address + ", " + fulladdress.detail;
      this.user.zipCode = fulladdress.code;
      this.addressDialog = false;
    },
    async duplicateCheck(column) {
      const _this = this;

      if (column == "email" && this.$refs.regiform.inputs[0].validate()) {
        await this.duplicateEmail(this.user.email).then(response => {
          _this.emailDuplicated = response.status == 200 ? false : true;
        })
      } else if ( column == "nickname" && this.$refs.regiform.inputs[2].validate() ) {
        await this.duplicateNickname(this.user.nickname).then(response => {
          _this.nicknameDuplicated = response.status == 200 ? false : true;
        })
      } else {
        this.alert.message = "정확히 입력해주세요";
        this.alert.type = "error";
        this.alert.isAlert = "true";
      }
    },
  }
};
</script>

<style>
</style>
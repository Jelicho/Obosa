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
                <input
                  type="file"
                  style="display:none"
                  accept=".gif, .jpg, .png"
                  id="profile"
                  @change="onFileChange"
                />
              </v-btn>
            </v-col>
            <v-col sm="6" class="input-form">
              <v-row>
                <v-col>
                  <logo />
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-text-field
                    required
                    full-width
                    single-line
                    label="이메일"
                    v-model="user.email"
                    background-color="#f4f8f7"
                    color="grey darken-2"
                    :readonly = "true"
                    :rules="[rules.required, rules.email]"
                  ></v-text-field>
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
                    :readonly = "true"
                    :rules="[rules.required]"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="9">
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
                <v-col cols="3" style="align-self:baseline!important">
                  <v-btn
                    height="56px"
                    width="100%"
                    :color="nicknameDuplicated ? 'red lighten-2' : 'green lighten-2'"
                    @click="duplicateCheck('nickname')"
                  >중복 확인</v-btn>
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
                    @click="updateInfo()"
                  >수정하기</v-btn>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-form>
    <v-snackbar v-model="alert.isAlert" middle="true" :color="alert.type" top>
      {{ alert.message }}
      <v-btn text @click="alert.isAlert = false">확인</v-btn>
    </v-snackbar>
  </div>
</template>
​
<script>
import addressPopUp from "@/component/api/addressPopUp";

export default {
  name: "myInfo",
  data() {
    return {
      valid: true,
      addressDialog: false,
      emailDuplicated: false,
      nicknameDuplicated: true,
      profilePreview: require("@/assets/user.png"),
      user: {
        email: "minjun@naver.com",
        name: "이민준",
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
      message: "",
      alert: {
        isAlert: false,
        message: "",
        type: "success"
      }
    };
  },
  components: {
    addressPopUp
  },
  mounted() {
    this.profilePreview = require("@/assets/user.png");
    this.getUserInfo()
  },
  methods: {
    async getUserInfo(){
      await this.$store.dispatch('getUserInfo')
      this.setUserInfo()
    },
    setUserInfo(){
      profilePreview = this.$store.state.user[0].profileImg,
      user.email= this.$store.state.user[0].email,
      user.name = this.$store.state.user[0].name,
      user.nickname = this.$store.state.user[0].nickname,
      user.password = this.$store.state.user[0].password,
      user.zipCode = this.$store.state.user[0].zipCode,
      user.address = this.$store.state.user[0].address,
      user.phone = this.$store.state.user[0].phone,
      user.profileImg = this.$store.state.user[0].profileImg
      splitPhone(user.phone)
    },
    splitPhone(phone){
      var phSplit = phone.split('-');
      no1 = phSplit[0]
      no2 = phSplit[1]
      no3 = phSplit[2]
    },

    updateInfo: function() {
      var scope = this;
      var regiFormData = new FormData();
      this.user.phone = this.no1 + "-" + this.no2 + "-" + this.no3;
      var route = this.$router

      if (this.$refs.regiform.validate()) {
        for (var key in this.user) {
        }
        regiFormData.append(key, this.user[key]);
        console.log("send data");
        this.$userService.signUp(regiFormData).then(response => {
          console.log(response);

          if (response.status == 200) {
            route.push('/login');
            }
        })
      } else {
        this.snackbar = true;
      }
    },
    upload() {
      $("#profile").trigger("click");
    },
    onFileChange(e) {
      this.user.profileImg = profile;
      var profile = e.target.files[0];
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
    openapi() {
      this.addressDialog = !this.addressDialog;
    },
    setAddress(fulladdress) {
      this.user.address = fulladdress.address + ", " + fulladdress.detail;
      this.user.zipCode = fulladdress.code;
      this.addressDialog = false;
    },
    async duplicateCheck(column) {
      const scope = this;
      var duplicatecheck = null;

        duplicatecheck = this.$userService.duplicateNickname(this.user.nickname);
        if ( column == "nickname" && this.$refs.regiform.inputs[2].validate() ) {
      }

      if (duplicatecheck != null) {
        duplicatecheck.then(response => {
          if (response.status == 200) {
            scope.alert.message = response.message + "합니다.";
            scope.alert.type = "success";
            scope.alert.isAlert = "true";
            scope.emailDuplicated = false;
            /*
             * TODO :
             * 이메일 중복 검사 완료 시 사용자가 이메일을 변경할 경우를 대비하여
             * 중복검사 버튼은 이메일 재변경 버튼으로 변경
             */
          } else {
            scope.alert.message = response.message + "입니다.";
            scope.alert.type = "error";
            scope.alert.isAlert = "true";
            scope.emailDuplicated = true;
          }
        });
      } else {
        scope.alert.message = "정확히 입력해주세요";
        scope.alert.type = "error";
        scope.alert.isAlert = "true";
      }
    }
  }
};
</script>

<style>
</style>

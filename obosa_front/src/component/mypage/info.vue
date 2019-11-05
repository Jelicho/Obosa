<template>
  <div>
    <v-container class="container">
      <v-form ref="regiform" valid>
        <v-row class="form-container" >
          <div class="col-md-5 text_container" >
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
              <v-col sm="9" >
                    <v-text-field
                      required
                      outlined
                      class="input_text"
                      label="이메일"
                      v-model="user.email"
                      background-color="#f4f8f7"
                      color="#994fa980"
                      :readonly = "true"
                      :rules="[rules.required, rules.email]"
                    ></v-text-field>
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
                      :readonly = "true"
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
                      width="100%" tile
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
                  <v-col @click="openapi()">
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
                  <v-col @click="openapi()">
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
                      @click="updateInfo()"
                    >수정하기</v-btn>
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
​
<script>
import addressPopUp from "@/component/api/addressPopUp";
import { mapActions } from "vuex";
import { mapState } from "vuex";
import { mapGetters } from "vuex";
export default {
  name: "myInfo",
  data() {
    return {
      valid: true,
      addressDialog: false,
      emailDuplicated: false,
      nicknameDuplicated: true,
      profilePreview: "",
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
  computed: {
    ...mapState('userModule', ['userInfo']),
  },
  watch: {
    userInfo : function() {
      this.setUserInfo()
    }
  },
  async beforeMount(){
    await this.readUserInfo()
  },
  // async mounted() {
  //   // this.profilePreview = require("@/assets/user.png");
  //   await this.setUserInfo()
  // },
  methods: {
    ...mapActions('userModule', ['getUserInfo', 'updateUser']),
    ...mapGetters('userModule', ['getUser']),
    async readUserInfo(){
      await this.getUserInfo()
    },
    async setUserInfo(){
      var userStore = await this.getUser();
      console.log(userStore);
      // profilePreview = this.user.profileImg,
      console.log(userStore.email);
      this.user.email= userStore.email,
      this.user.name = userStore.name,
      this.user.nickname = userStore.nickname,
      this.user.zipCode = userStore.zipCode,
      this.user.address = userStore.address,
      this.user.phone = userStore.phone,
      this.user.profileImg = userStore.profileImg
      this.profilePreview = this.user.profileImg
      this.splitPhone(userStore.phone)
    },
    splitPhone(phone){
      var phSplit = phone.split('-');
      this.no1 = phSplit[0]
      this.no2 = phSplit[1]
      this.no3 = phSplit[2]
    },

    updateInfo: function() {

      this.user.phone = this.no1 + "-" + this.no2 + "-" + this.no3;
      // var form = new FormData()
      // console.log(this.user.password);
      // form.append('password', this.user.password)
      // form.append('phone', this.user.phone)
      // form.append('profileImg', this.user.profileImg)
      // form.append('zipCode', this.user.zipCode)
      // form.append('address', this.user.address)
      // form.append('profileImg', this.user.profileImg)
      // console.log(form);
      // this.updateUser(form)

      var password = this.user.password
      var phone = this.user.phone
      var profileImg = this.user.profileImg
      var zipCode = this.user.zipCode
      var address = this.user.address
      var profileImg = this.user.profileImg
      this.updateUser({
          password
        , phone
        , profileImg
        , zipCode
        , address
        })
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

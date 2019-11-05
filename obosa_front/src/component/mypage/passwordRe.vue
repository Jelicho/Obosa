<template>
  <div>
    <v-container class="container">
      <v-row class="form-container">
        <div class="col-md-5 text-container">
          <p class="login-text">
            비밀번호를 한번 더 입력해주세요.
            <br />당신의 정보는 소중하니까-
          </p>
        </div>
        <v-divider vertical />
        <div id="login-form" class="col-md-6 mx-auto bg-white">
          <v-row>
            <v-col>
              <v-text-field
                class="input_text password"
                color="#994fa980"
                type="password"
                label="비밀번호"
                outlined
                v-model="user.password"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-btn block tile large dark ripple color="#994fa980" id="sign-up" @click="onSubmit()">확 인</v-btn>
            </v-col>
          </v-row>
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { mapState } from "vuex";
export default {
  name: "passwordRe",
  data() {
    return {
      user: {
        password: ""
      }
    };
  },
  methods: {
    ...mapActions('userModule', ['passwordReChk']),
    ...mapState('userModule', ['rechecked']),
    async onSubmit() {
      var password = this.user.password
      await this.passwordReChk(password)
      if (this.rechecked) {
        this.$router.push("/mypage/info")
      } else {
        window.alert("rejected : 비밀번호를 확인해주세요.")
      }
    }
  }
}
</script>

<style>
</style>

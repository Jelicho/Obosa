<template>
  <div>
    <v-container class="container">
      <div id="login-form" class="col-md-10 mx-auto bg-white">
        <v-row>
          <v-col>
            <logo />
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
              background-color="#f2f8f7"
              color="grey darken-2"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-btn block rounded large dark ripple color="teal" id="sign-up" @click="onSubmit()">확 인</v-btn>
          </v-col>
        </v-row>
      </div>
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

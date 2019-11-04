<template>
  <div>
    <v-container class="container">
      <v-row class="form-container">
        <div class="col-md-5 text-container">
          <p class="login-text">
            언제 어디서든
            <br />경매에 참여할 수 있습니다.
          </p>
        </div>
        <v-divider vertical />
        <div id="login-form" class="col-md-6 mx-auto bg-white">
          <v-row>
            <v-col>
              <v-text-field
                class="input_text"
                color="#994fa980"
                label="이메일"
                v-model="user.email"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
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
              <v-btn
                block
                tile
                large
                dark
                ripple
                color="#994fa980"
                id="sign-up"
                @click="onSubmit()"
              >로그인</v-btn>
            </v-col>
          </v-row>
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "Login",
  data() {
    return {
      user: {
        email: "",
        password: ""
      }
    };
  },
  methods: {
    ...mapActions('userModule', ['login']),
    async onSubmit() {
      var email = this.user.email
      var password = this.user.password
      console.log(email, password);
      
      // LOGIN 액션 실행
      await this.login({email, password})
      this.redirect()
    },
    redirect() {
      console.log('entered redirect')
      this.$router.replace(this.$route.query.redirect || '/')
    }
  }
}
</script>

<style>
</style>

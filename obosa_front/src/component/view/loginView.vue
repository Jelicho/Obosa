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
              label="이메일"
              v-model="user.email"
              background-color="#f2f8f7"
              color="grey darken-2"
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
              background-color="#f2f8f7"
              color="grey darken-2"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-btn block rounded large dark ripple color="teal" id="sign-up" @click="onSubmit()">로그인</v-btn>
          </v-col>
        </v-row>
      </div>
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

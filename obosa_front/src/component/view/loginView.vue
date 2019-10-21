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
            <v-btn block rounded large dark ripple color="teal" id="sign-up" @click="login()">로그인</v-btn>
          </v-col>
        </v-row>
      </div>
    </v-container>
  </div>
</template>

<script>

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
    login: function() {
      var scope = this;
      var store = this.$store

      console.log(this.user);

      this.$userService.login(
        this.user.email,
        this.user.password,
        function(data) {
          store.state.isSigned = true;
          // store.state.user.id = data.id;
          console.log(data)
          console.log('왔다!!')
          // this.$walletService.findById(store.state.user.id, function(response) {
          //   if (response.status == 204) {
          //     store.state.user.hasWallet = false;
          //   } else if (response["소유자id"] == store.state.user.id) {
          //     store.state.user.hasWallet = true;
          //   } else {
          //     alert("Unexpected status code : " + response.status);
          //   }
          //   sessionStorage.setItem("state", JSON.stringify(store.state));
          //   scope.$router.push("/");
          // });
        },
        function(error) {
          alert("유저 이메일 혹은 비밀번호가 일치하지 않습니다.");
        }
      );
    }
  }
};
</script>

<style>
</style>

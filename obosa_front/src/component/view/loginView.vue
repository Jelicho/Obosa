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
                @click="login()"
              >로그인</v-btn>
            </v-col>
          </v-row>
        </div>
      </v-row>
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
          store.state.user.id = data.id;

          this.$walletService.findById(store.state.user.id, function(response) {
            if (response.status == 204) {
              store.state.user.hasWallet = false;
            } else if (response["소유자id"] == store.state.user.id) {
              store.state.user.hasWallet = true;
            } else {
              alert("Unexpected status code : " + response.status);
            }
            sessionStorage.setItem("state", JSON.stringify(store.state));
            scope.$router.push("/");
          });
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
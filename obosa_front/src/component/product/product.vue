<template>
<v-dialog
  v-model="dialog"
  width="1000"
>
<template v-slot:activator="{ on }">
  <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="200px"
      :src="pImgs[0]"
      v-on="on"
    >
      <v-card-title>{{pName}}</v-card-title>
    </v-img>

    <v-card-text class="text--primary">
      <div>{{pDesc}}</div>
    </v-card-text>

    <v-card-actions>
      <v-btn color="orange" text @onclick="to('auction.register')">
        경매등록
      </v-btn>

      <v-btn color="red" text @onclick="deleteProd">
        상품삭제
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<!-- dialog view -->
<v-card>
  <v-card-title
    class="headline grey lighten-2"
    primary-title
  >
    상품 상세 페이지
  </v-card-title>

  <v-card-text>
        <v-form ref="regiform" valid>
          <v-container class="container">
            <div id="register-form" class="col-md-12 mx-auto bg-white">
              <v-row>
                <v-col sm="6" align="center">
                  <v-img src="this.product.pImgsUp[0]" width="300" height="300"></v-img>
                  <v-btn rounded depressed color="#FDD835" @click="upload()">
                    업로드
                    <input
                      type="file"
                      style="display:none"
                      accept=".gif, .jpg, .png"
                      id="prodImgFile"
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
                        label="상품명"
                        v-model="product.pNameUp"
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
                        height="18em"
                        single-line
                        label="상품설명"
                        v-model="product.pDescUp"
                        background-color="#f4f8f7"
                        color="grey darken-2"
                        :rules="[rules.required]"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </div>
          </v-container>
        </v-form>
  </v-card-text>

  <v-divider></v-divider>

  <v-card-actions>
    <v-spacer></v-spacer>
    <v-btn
      color="primary"
      text
      @click="dialog = false"
    >
      취소
    </v-btn>
    <v-btn
      color="primary"
      text
      @click="updateProd"
    >
      수정하기
    </v-btn>
  </v-card-actions>
</v-card>

</v-dialog>
</template>
<script>
import {mapActions} from "vuex"
export default {
  name: 'product',
  data() {
    return {
      dialog: false,
      product:{
      pIdUp: '',
      pNameUp: '',
      pDescUp: '',
      pImgsUp: []
      },
      rules: {
        required: value => !!value || "입력해주세요"
      }
    }
  },
  props: {
    pId: String,
    pName: String,
    pDesc: String,
    pImgs: Array,
  },
  mounted(){
    console.log("in product comp : "+this.pId);
    this.product.pIdUp = this.pId
    this.product.pNameUp = this.pName
    this.product.pDescUp = this.pDesc
    this.product.pImgsUp = this.pImgs[0]
  },
  methods: {
    ...mapActions('productModule', ['readProduct', 'updateProduct', 'deleteProduct']),
    async updateProd(){
      let formData = new FormData()
      formData.append('pid', this.pIdUp);
      formData.append('pname', this.pNameUp);
      formData.append('pdescription', this.pDescUp);
      formData.append('productImgs', this.pImgsUp);
      await this.updateProduct(formData)
      await this.readProduct()
    },
    async deleteProd(){
      var pid = this.pId
      await this.deleteProduct({pid})
      await this.readProduct()
    },
    auctionRegister(target){
        this.$router.push({
          name:target,
          params:{
            product:this.product
          }
        })
    },
    onFileChange(e) {
      var prodImg = e.target.files[0];
      this.product.pImgsUp = prodImg;
      this.setPreview(prodImg);
    },
    setPreview(imgfile) {
      var reader = new FileReader(0);
      var _this = this;
      reader.onload = e => {
        _this.product.pImgsUp[0] = e.target.result;
      };
      reader.readAsDataURL(imgfile);
    },
    upload() {
      $("#prodImgFile").trigger("click");
    },
}
}
</script>

<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="1000"
    >
      <template v-slot:activator="{ on }">
        <v-btn
          color="red lighten-2"
          dark
          v-on="on"
        >
          상품추가
        </v-btn>
      </template>

      <v-card>
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          상품 등록 해욥~~~~!
        </v-card-title>

        <v-card-text>
              <v-form ref="regiform" valid>
                <v-container class="container">
                  <div id="register-form" class="col-md-12 mx-auto bg-white">
                    <v-row>
                      <v-col sm="6" align="center">
                        <!-- <v-img :src="prodImgPrev" width="300" height="300"></v-img> -->
                        <imageSlide :imageList="prodImgPrev"/>
                        <v-btn rounded depressed color="#FDD835" @click="upload()">
                          업로드
                          <input
                            type="file"
                            style="display:none"
                            accept=".gif, .jpg, .png"
                            id="profile"
                            multiple
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
                              v-model="product.pname"
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
                              v-model="product.pdescription"
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
            @click="onSubmit"
          >
            등록하기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
// import imgur from '../components/Imgur'
import {mapActions} from "vuex"
import imageSlide from '@/component/common/imageSlide'

export default {
  name:"productRegister",
  data(){
      return {
          dialog: false,
          prodImgPrev: [require("@/assets/product.png")],
          product: {
              pname: "",
              pdescription: "",
              productImgs: []
          },
          rules: {
            required: value => !!value || "입력해주세요"
          }
      }
  },
  components: {
    imageSlide
  },
  methods: {
    ...mapActions('productModule', ['createProduct']),
      onSubmit() {
        let formData = new FormData();
          formData.append('pname', this.product.pname)
          formData.append('pdescription', this.product.pdescription)
          formData.append('productImgs', this.product.productImgs)
          console.log(this.product.productImgs)
          console.log(formData)
          this.createProduct(formData)
      },
      upload() {
        $("#profile").trigger("click");
      },
      onFileChange(e) {
        var prodImgs = e.target.files;
        this.product.productImgs = prodImgs;
        this.setPreview(prodImgs);
      },
      setPreview(imgfileList) {
        this.prodImgPrev = []
        for (const img of imgfileList) {
          var reader = new FileReader(0);
          this.dataUrl(reader, img)
        }
        console.log(this.prodImgPrev);
      },
      dataUrl(reader, img){
        var _this = this
        reader.onload = e => {
          _this.prodImgPrev.push(e.target.result)
        }
        reader.readAsDataURL(img)
      }
  }
}
</script>
<style>

</style>

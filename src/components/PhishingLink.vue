<template>
  <v-app>
    <!-- Appbar -->
    <AppBar />

    <v-main class="d-flex align-center justify-center">
      <br /><br />
      <p class="title1">
        출처를 알 수 없는 링크가 포함되어있나요? 악성 링크 여부를
        분석해드립니다. <br />링크를 조회해 보세요.
      </p>
      <br /><br />
      <v-card class="mx-auto my-auto" id="form-container">
        <v-row justify="center">
          <v-col cols="12">
            <div class="image-container">
              <img src="../assets/link.png" />
            </div>

            <div class="input-container mx-auto">
              <v-form @submit.prevent="sendLink">
                <v-row>
                  <v-text-field
                    v-model="inputUrl"
                    label="링크를 입력하세요"
                    outlined
                    dense
                    required
                  ></v-text-field
                  >&nbsp;&nbsp;&nbsp;
                  <v-btn type="submit" class="mx-auto" color="#14274E"
                    >조회하기</v-btn
                  >
                </v-row>
              </v-form>
            </div>
            <br />
            <p class="subtitle">
              출처를 알 수 없는 링크가 포함되어있는 경우, 링크를 클릭하면 나도
              모르는 사이에 스마트폰에 악성 앱 등이 설치될 수 있습니다.<br />공공기관에서
              문자가 왔다면 해당 기관의 대표 번호나 홈페이지 주소와 일치하는 지
              확인이 필요합니다.
            </p>
          </v-col>
        </v-row>
      </v-card>
      <br /><br /><br />
    </v-main>

    <v-dialog v-model="showResultModal" width="70%">
      <PhishingLinkResult
        v-if="showResultModal"
        :responsedata="responsedata"
        :showResultModal.sync="showResultModal"
        :phishingtype="phishingtype"
      />
    </v-dialog>

    <!-- Footer -->
    <Footer />
  </v-app>
</template>

<script>
import axios from "axios";
import AppBar from "../views/AppBar.vue";
import Footer from "../views/Footer.vue";
import PhishingLinkResult from "../components/PhishingLinkResult.vue";

export default {
  components: {
    AppBar,
    Footer,
    PhishingLinkResult,
  },

  props: ["phishingtype"],

  data() {
    return {
      inputUrl: "",
      responsedata: null,
      showResultModal: false,
    };
  },
  methods: {
    sendLink() {
      var url = "http://13.125.225.44:9999/phishing/link";
      var data = {
        inputUrl: this.inputUrl,
      };
      axios
        .post(url, data)
        .then((response) => {
          console.log("POST 요청 성공:", response.data);

          this.responsedata = response.data;
          this.showResultModal = true;
        })
        .catch((error) => {
          console.error("POST 요청 오류:", error);
        });
    },
  },
};
</script>
<style scoped>
#form-container {
  width: 60%;
  height: 60%;
  border-radius: 15px;
  padding: 20px;
  border: 5px solid #394867;
  font-family: "Pretendard-Regular", sans-serif;
  margin-bottom: 50px;
}
p {
  font-family: "Pretendard-Regular", sans-serif;
}
.title1 {
  font-size: 25px;
  font-weight: 800;
}
.subtitle {
  font-size: 13px;
  color: gray;
  font-weight: 15;
}

.v-btn {
  background-color: #14274e;
  color: white;
  margin-left: 15px;
}

.input-container {
  display: inline-block;
  justify-content: center;
  width: 50%;
  font-size: 25px;
}

.image-container {
  display: flex;
  justify-content: center;
}

.image-container img {
  width: 20%;
}
</style>

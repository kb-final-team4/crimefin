<template>
  <v-app>
  <AppBar />

    <v-main>
      <v-container>
        <v-row justify="center">
          <v-col cols="12" sm="6" md="4">
            <h1>{{ phishingtype }}:: 링크를 입력하세요</h1>
            <v-form @submit.prevent="sendLink">
              <v-text-field
                v-model="inputUrl"
                label="링크"
                required
              ></v-text-field>
              <v-btn type="submit">조회하기</v-btn>
            </v-form>
          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <v-dialog v-model="showResultModal" width="60%" height="60%" position-x="center" position-y="center" class="result">
    <PhishingLinkResult
      v-if="showResultModal"
      :responsedata="responsedata"
      :showResultModal.sync="showResultModal"
      :phishingtype="phishingtype"
    />
  </v-dialog>
  </v-app>
</template>

<script>
import axios from 'axios';
import AppBar from '../views/AppBar.vue'; // 상단바 컴포넌트를 import 합니다.
import PhishingLinkResult from '../components/PhishingLinkResult.vue';


export default {
  components: {
    AppBar, 
    PhishingLinkResult},

  props: ['phishingtype'],

  data() {
    return {
      inputUrl: '', 
      responsedata:null,
      showResultModal:false,
      };
  },
  methods: {
    sendLink() {
      var url = "http://localhost:9999/phishing/link";
      var data = {
        inputUrl: this.inputUrl
      }
      axios.post(url, data)
        .then((response) => {
          console.log('POST 요청 성공:', response.data);
         // response.data를 responseData에 저장
        this.responsedata = response.data
        alert(response.data)
        // 모달 창 표시
        this.showResultModal = true;
        })
        .catch((error) => {
          console.error('POST 요청 오류:', error);
        });
    },
  },
};
</script>
<style scoped>
.result{
  padding: 30px;
  margin:  30px;
}
</style>
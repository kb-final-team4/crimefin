<template>
  <v-app>
    <AppBar />

    <v-main>
      <v-container>
        <v-row justify="center">
          <v-col cols="12" sm="6" md="4">
            <h1>{{ phishingtype }} :: 전화번호를 입력하세요</h1>

            <v-form @submit.prevent="sendPhoneNumber" >
              <v-text-field v-model="phoneNum" label="조회할 번호를 입력하세요" required></v-text-field>
              <v-btn type="submit" dark>조회하기</v-btn>
            </v-form>
          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <v-dialog v-model="showResultModal" width="60%" height="60%" position-x="center" position-y="center" class="result">
      <PhishingNumberResult
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
import AppBar from '../views/AppBar.vue';
import PhishingNumberResult from '../components/PhishingNumberResult.vue';

export default {
  components: {
    AppBar,
    PhishingNumberResult
  },

  props: ['phishingtype'],

  data() {
    return {
      phoneNum: '',
      responsedata:null,
      showResultModal:false,
    };
  },
  methods: {
    sendPhoneNumber() {
      const phoneNum = this.phoneNum;

      axios.get('http://localhost:9999/phishing', { params: { phoneNum } })
        .then(response => {
          console.log('GET 요청 성공:', response.data);
          // response.data를 responseData에 저장
          this.responsedata = response.data

          // 모달 창 표시
          this.showResultModal = true;

        })
        .catch(error => {
          console.error('GET 요청 오류:', error);
        })
    },
  },
}
</script>
<style scoped>
.result{
  padding: 30px;
  margin:  30px;
}
</style>


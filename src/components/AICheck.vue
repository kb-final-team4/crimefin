<template>
    <v-app>
    <AppBar />
  
      <v-main>
        <v-container>
          <v-row justify="center">
            <v-col cols="12" sm="6" md="4">

            <div v-if="phishingtype ==='Phone'">
                <h1>{{ phishingtype }}:: 전화 내용을 입력해주세요!</h1>
            </div>
            <div v-else-if="phishingtype === 'Message'">
                <h1>{{ phishingtype }}:: 문자 내용을 모두 입력해주세요!</h1>
            </div>
            <div v-else-if="phishingtype === 'SNS'">
                <h1>{{ phishingtype }}:: SNS 내용을 모두 입력해주세요!</h1>
            </div>
              <v-form @submit.prevent="sendContent" >
                <v-textarea
                  v-model="content"
                  label="내용"
                  required
                ></v-textarea>
                <v-btn type="submit">AI 검사하기</v-btn>
              </v-form>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
  
      <v-dialog v-model="showResultModal" width="60%" height="60%" position-x="center" position-y="center" class="result">
      <AICheckResult
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
  import AICheckResult from '../components/AICheckResult.vue';
  
  
  export default {
    components: {
      AppBar, 
      AICheckResult},
  
    props: ['phishingtype'],
  
    data() {
      return {
        content: '', 
        responsedata:null,
        showResultModal:false,
        };
    },
    methods: {
        sendContent() {
        var url = "http://localhost:9999/phishing/content";
        var data = {
            content: this.content
        }
        axios.post(url, data)
          .then((response) => {
            console.log('POST 요청 성공:', response.data);
           
          this.responsedata = response.data
  
          // 모달 창 표시
          this.showResultModal = true;
          })
          .catch((error) => {
            console.error('POST 요청 오류:', error);
            
            
            this.responsedata = '위험'
          // 모달 창 표시
          this.showResultModal = true;
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
<template>
  <v-app>
    <AppBar />

    <v-main class="d-flex align-center justify-center">
      <br><br><p class="title1">저장되지 않은 번호로부터 이체, 금융 정보 요구 등 이상한 연락을 받았나요? <br>번호를 조회해 보세요</p>
      <v-card class="mx-auto my-auto" id="form-container">
        <v-row justify="center">
          <v-col cols="12">
            <div class="image-container">
              <img src="../assets/number.png" />
            </div>

            <div class="input-container mx-auto">
              <v-text-field v-model="phoneNum" label="조회할 번호를 입력하세요" outlined dense :style="{ height: '60px' }"></v-text-field> &nbsp;&nbsp;
              <v-btn type="submit"  class="mx-auto" color="#14274E">조회하기</v-btn>
            </div>
            <br>
            <p class="subtitle">검찰, 경찰, 금융감독원 등의 정부기관은 어떠한 경우에도 전화 또는 문자로 자금 이체를 요청하거나,<br> 개인의 통장번호나 공인인증서, 애플리케이션 설치 등을 요구하지 않습니다</p>            

          </v-col>
        </v-row>
      </v-card>
      <br><br><br>
    </v-main>
    
    <v-dialog v-model="showResultModal" width="60%" height="60%" position-x="center" position-y="center" class="result">
      <PhishingNumberResult
        v-if="showResultModal"
        :responsedata="responsedata"
        :showResultModal.sync="showResultModal"
        :phishingtype="phishingtype"
      />
    </v-dialog>
    <Footer/>
  </v-app>
</template>

<script>
import axios from 'axios';
import AppBar from '../views/AppBar.vue';
import Footer from '../views/Footer.vue';
import PhishingNumberResult from '../components/PhishingNumberResult.vue';

export default {
  components: {
    AppBar,Footer,
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
      console.log(phishingtype)
      const phoneNum = this.phoneNum;

      axios.get('http://localhost:9999/phishing', { params: { phoneNum } })
        .then(response => {
          console.log('GET 요청 성공:', response.data);

          this.responsedata = response.data

          this.showResultModal = true;

        })
        .catch(error => {
          console.error('GET 요청 오류:', error);

          this.responsedata = "01011113333"

          this.showResultModal = true;
        })
    },
  },
}
</script>
<style scoped>

@font-face {
    font-family: 'LINESeedKR-Bd';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}
@font-face {
  font-family: 'TheJamsil5Bold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/TheJamsil5Bold.woff2') format('woff2');
  font-weight: normal;
  font-style: normal;
}

#form-container {
   width:70%;
   height:60%;
   border-radius:15px;
   padding :20px ;
   border :5px solid #394867 ;
   font-family: 'LINESeedKR-Bd', sans-serif;
   margin-bottom: 50px;
}
p{
  font-family: 'LINESeedKR-Bd', sans-serif;
}
.title1{
  font-size: 25px;
  font-weight: 500;
}
.subtitle{
  font-size: 13px;
  color: gray;
  font-weight: 15;
}

.v-btn { 
   background-color:#14274E ;
   color:white ;  
   margin-left: 15px;
} 

.result{
padding :30px ;
margin :30px ;
}

.input-container {
  display: flex;
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
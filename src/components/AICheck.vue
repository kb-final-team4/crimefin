<template>
  <v-app>
  <!-- Appbar -->
  <AppBar />
    <v-main class="d-flex align-center justify-center">

      <div v-if="phishingtype ==='Phone'">
        <br><br><p class="title1">통화 내용을 입력받아 문맥을 분석해드립니다. <br>통화 내용을 조회해 보세요.</p><br><br>
      </div>
      <div v-else-if="phishingtype === 'Message'">
        <br><br><p class="title1">수신 내용을 입력받아 문맥을 분석해드립니다. <br>문자 내용을 모두 조회해 보세요.</p><br><br>
      </div>
      <div v-else-if="phishingtype === 'SNS'">
        <br><br><p class="title1">수신 내용을 입력받아 문맥을 분석해드립니다. <br>SNS 내용을 모두 조회해 보세요.</p><br><br>
      </div>
      <v-card class="mx-auto my-auto" id="form-container">
        <v-row justify="center">
          <v-col cols="12">
            <div class="image-container">
              <img src="../assets/ai.png" />
            </div>

            <div class="input-container mx-auto">
              <v-form @submit.prevent="sendContent">
                <v-row>
                  <v-textarea v-model="content" label="조회하고자 하는 내용을 모두 입력하세요" outlined dense required></v-textarea>&nbsp;&nbsp;&nbsp;
                  <v-btn type="submit" class="mx-auto" color="#14274E">조회하기</v-btn> 
                </v-row>
              </v-form>
            </div>
            <br>

          </v-col>
        </v-row>
      </v-card>
      <br><br><br>
    </v-main>
  
    <v-dialog v-model="showResultModal" width="70%">
      <AICheckResult  v-if="showResultModal" :responsedata="responsedata" :showResultModal.sync="showResultModal" :phishingtype="phishingtype"/>
    </v-dialog>

    <!-- Footer -->
    <Footer/>
    </v-app>
  </template>
  
  <script>
  import axios from 'axios';
  import AppBar from '../views/AppBar.vue'; 
  import Footer from '../views/Footer.vue';
  import AICheckResult from '../components/AICheckResult.vue';
  
  
  export default {
    components: {
      AppBar, Footer,
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

#form-container {
   width:60%;
   height:60%;
   border-radius:15px;
   padding :20px ;
   border :5px solid #394867 ;
   font-family: 'Pretendard-Regular', sans-serif;
   margin-bottom: 50px;
}
p{
  font-family: 'Pretendard-Regular', sans-serif;
}
.title1{
  font-size: 25px;
  font-weight: 800;
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
  width: 23%;
}
</style>   
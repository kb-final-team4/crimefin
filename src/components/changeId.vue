<template>
    <div>
      <h1>아이디 찾기</h1>
      <form @submit.prevent="requestVerificationCode" v-if="!verificationCodeSent">
      <div>
          <label for="name">이름:</label>
          <input type="text" id="name" v-model="name" required>
      </div>
      <div>
          <label for="phoneNumber">전화번호:</label>
          <input type="text" id="phoneNumber" v-model="phoneNumber" required>
        </div>
        <button type="submit">인증번호 받기</button>
      </form>
  
      <form @submit.prevent="findUserId" v-if="verificationCodeSent">
        <div>
          <label for="verificationCode">인증번호:</label>
          <input type="text" id="verificationCode" v-model="verificationCode" required>
        </div>
        <button type="submit">아이디 찾기</button>
      </form>
      <div v-if="foundUserId">
        <p>아이디: {{ foundUserId }}</p>
      </div>
      <div v-if="!foundUserId && submitted">
        <p>일치하는 아이디가 없습니다.</p>
      </div>
  
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        name: '',
        phoneNumber: '',
        verificationCode: '',
        foundUserId: '',
        submitted: false,
        verificationCodeSent: false,
      };
    },
    methods: {
      requestVerificationCode() {
        // 사용자가 입력한 이름과 전화번호 가져오기
        const name = this.name;
        const phoneNumber = this.phoneNumber;
  
        // 서버에 GET 요청 보내기 (인증번호 요청)
        axios.get('/your-server-endpoint', {
          params: {
            name: name,
            phoneNumber: phoneNumber,
          },
        })
          .then((response) => {
            // 서버에서 인증번호를 성공적으로 받았을 때 처리
            // response.data에 서버에서 받은 데이터가 포함됨.
            console.log('인증번호 요청 성공:', response.data);
  
            // 인증번호를 성공적으로 받았으므로 verificationCodeSent를 true로 설정
            this.verificationCodeSent = true;
          })
          .catch((error) => {
            // 서버 통신 중 오류가 발생한 경우 처리
            console.error('인증번호 요청 오류:', error);
          });
      },
      // findUserId 메서드 및 나머지 코드는 이전 예제와 동일합니다.
      findUserId(){
          //사용자가 입력한 인증번호 가져오기
          const verificationCode = this.verificationCode;
  
          // 서버에 GET 요청 보내기 (아이디 찾기)
          axios.get('/your-server-endpoint-for-finding-id',{params:{
              verificationCode: verificationCode,
          }
      })
      .then((response)=>{
           // 서버에서 아이디를 성공적으로 찾았을 때 처리
            // response.data에 서버에서 받은 아이디가 포함됌.
          this.foundUserId = response.data;
          this.submitted=true;
      })
      .catch((error)=>{
          // 서버 통신 중 오류가 발생한 경우 처리
          console.error('아이디 찾기 오류:', error);
          this.submitted = true;
      });
      }
    },
  };
  </script>
  
  <style>
  
  </style>
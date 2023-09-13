<template>
    <v-app>
      <!-- ... (이전 코드) -->
      <v-main>
        <v-container>
          <v-row justify="center">
            <v-col cols="12" sm="6" md="4">
              <!-- 링크 입력 폼 -->
              <h1>{{ $route.params.type }} :: 링크를 입력하세요</h1>
              <v-form @submit.prevent="sendLink">
                <v-text-field
                  v-model="link"
                  label="링크"
                  required
                ></v-text-field>
                <v-btn type="submit">조회하기</v-btn>
              </v-form>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        link: '', // 사용자 입력을 저장할 변수
      };
    },
    methods: {
      sendLink() {
        // 사용자 입력된 링크를 사용하여 POST 요청을 보냅니다.
        const link = this.link;
        axios
          .post('/phishing', { link })
          .then((response) => {
            // 성공적으로 요청을 보낸 경우 실행될 로직
            console.log('POST 요청 성공:', response.data);
  
            // {{ $route.params.type }}의 값에 따라 다른 결과 페이지로 이동합니다.
            if (this.$route.params.type === 'Message') {
              this.$router.push({
                name: 'MessageNumberResult',
                query: { result: response.data },
              });
            } else if (this.$route.params.type === 'Phone') {
              this.$router.push({
                name: 'PhoneNumberResult',
                query: { result: response.data },
              });
            }
          })
          .catch((error) => {
            // 요청 중 오류가 발생한 경우 실행될 로직
            console.error('POST 요청 오류:', error);
          });
      },
    },
  };
  </script>
  
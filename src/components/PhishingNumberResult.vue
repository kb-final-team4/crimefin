<template>
    <v-container fluid>
      <v-layout align-center justify-center>
        <v-flex xs12 sm8 md6>
          <v-card>
            <v-card-title>
              <h2 class="display-1">전화번호 검사 결과</h2>
            </v-card-title>
            <v-card-text>
              <v-alert
                :type="result === 0 ? 'error' : 'success'"
                outlined
                border="top"
                elevation="2"
              >
                {{ result === 0 ? '전화번호가 PhishingInfo 테이블에 없습니다.' : '전화번호가 PhishingInfo 테이블에 있습니다.' }}
              </v-alert>
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" @click="goToMainPage">돌아가기</v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </template>
  
  <script>
  export default {
    props: ['result'],
    methods: {
      goToMainPage() {
        // MainPage.vue로 돌아가는 로직을 추가하세요.
        this.$router.push('/MainPage');
      },
    },
  };
  </script>
  
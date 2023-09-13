<template>
    <v-app>
      <!-- 상단바 컴포넌트를 import 해서 불러옵니다 -->
      <AppBar />
      
      <v-main>
      <v-container>
        <!-- 카드를 가로로 배치하는 Flexbox 레이아웃 -->
        <div class="d-flex justify-center">
          <!-- 가로로 3개의 카드 배치 -->
          <!-- 첫 번째 카드 -->
          <v-card class="mx-2 mt-6 mr-2" width="450" tile>
            
            <v-card-title>
              <v-card class="mx-auto" width="400" height="350" tile>
                <v-img src="../assets/call.png" class="mx-auto" width="300" height="300"></v-img>
                <v-card-text>
                  <h2 class="text-center">이상한 전화가 왔어요</h2>
                </v-card-text>
                
              </v-card>
            </v-card-title>
            <v-card-actions class="justify-center">
              <!-- "바로가기" 버튼 -->
              <v-btn color="primary" @click="redirectToPhishingNumber('Phone')">바로가기</v-btn>
            </v-card-actions>
          </v-card>

          <!-- 두 번째 카드 -->
          <v-card class="mx-2 mt-6 mr-2" width="450" height="450" tile>
            
            <v-card-title>
              <v-card class="mx-auto" width="400" height="350" tile>
                <v-img src="../assets/sms.png" class="mx-auto" width="300" height="300"></v-img>
                <v-card-text>
                  <h2 class="text-center">이상한 문자가 왔어요</h2>
                </v-card-text>
                
              </v-card>
            </v-card-title>
            <v-card-actions class="justify-center">
              <!-- "바로가기" 버튼 -->
              <v-btn color="primary" @click="redirectToPhishingNumber('Message')">바로가기</v-btn>
            </v-card-actions>
          </v-card>

          <!-- 세 번째 카드 -->
          <v-card class="mx-2 mt-6 mr-2" width="450" height="450" tile>
            
            <v-card-title>
              <v-card class="mx-auto" width="400" height="350" tile>
                <v-img src="../assets/talk.png" class="mx-auto" width="300" height="300"></v-img>
                <v-card-text>
                  <h2 class="text-center">이상한 카톡이 왔어요</h2>
                </v-card-text>
                
              </v-card>
            </v-card-title>
            <v-card-actions class="justify-center">
              <!-- "바로가기" 버튼 -->
              <v-btn color="primary" @click="redirectToPhishingLink('Message')">바로가기</v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </v-container>
    </v-main>
    </v-app>
  </template>
  
  <script>
 import AppBar from '../views/AppBar.vue'; // 상단바 컴포넌트를 import 합니다.
  
  export default {
    components: {
      AppBar, // 상단바 컴포넌트를 등록합니다.
    },
    methods: {
    redirectToPhishingNumber(type) {
      // PhishingNumber.vue로 이동하는 로직을 작성합니다.
      this.$router.push({ name: 'PhishingNumber' , params: { type } });
    },
    redirectToPhishingLink(type) {
      // PhishingNumber.vue로 이동하는 로직을 작성합니다.
      this.$router.push({ name: 'PhishingLink' , params: { type } });
    },
  },
  };
  </script>
  
  <style scoped>
  /* 스타일을 필요에 따라 추가하세요. */
  </style>
  
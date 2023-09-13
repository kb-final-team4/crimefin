<template>
  <v-container class="content">
    <v-row class="text-center">
      <v-col cols="12">
        <v-img
          :src="require('../assets/CRIME.png')"
          class="my-3"
          contain
          height="200"
        />
      </v-col>

      <v-col cols="12">
        <v-img
          :src="require('../assets/MainImage.png')"
          class="my-3 mx-auto"
          contain
          width="80%"
          style= "opacity: 0.5; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);"
        />
      </v-col>

    </v-row>

  <!-- 카드 목록 -->
    
    <v-layout column>
      <v-divider class="my-10"></v-divider>

      <!-- 첫 번째 카드 -->
      <v-flex>
        <v-card class="mb-4 mx-auto elevation-2" width="90%" dark>
          <v-row>
          <v-col cols="4">
            <v-img src="../assets/init1.svg" height="300" style="max-width: 100%; opacity: 0.8; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);"></v-img>
          </v-col>
          <v-col cols="8">
          <v-card-title class="text-right mt-4 mb-4 my-4" style="font-weight: 300; font-size: 24px;">
            금융 사기로부터 당신의 자산을 지키는 
            최선의 파트너.
            </v-card-title>
          </v-col>
          </v-row>
          
          
        </v-card>
      </v-flex>

      <!-- 카드 사이 구분선 -->
      <v-divider class="my-10"></v-divider>

      <!-- 두 번째 카드 -->
      <v-flex>
        <v-card class="mb-4 mx-auto elevation-2" width="90%" dark>
          <v-row>
          <v-col cols="8"  class="text-left mt-4 mb-4">
            <v-card-title class="text-left mt-4 mb-4 my-4" style="font-weight: 300; font-size: 24px;">
              자산 모니터링으로 금융 사기를 빠르게 감지하고 미리 예방하세요
            </v-card-title>
          </v-col>
          <v-col cols="4">
            <v-img src="../assets/init2.svg" height="300" style="max-width: 100%; opacity: 0.8; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);"></v-img>
          </v-col>
          </v-row>
        </v-card>
      </v-flex>

      <v-divider class="my-10"></v-divider>

      <!-- 세 번째 카드 -->
      <v-flex>
        <v-card class="mb-4 mx-auto elevation-2" width="90%" dark>
          <v-row>
            <v-col cols="4">
              <v-img src="../assets/init3.svg" height="300" style="max-width: 100%; opacity: 0.8; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);"></v-img>
            </v-col>
            <v-col cols="8">
              <v-card-title class="text-right mt-4 mb-4 my-4" style="font-weight: 300; font-size: 24px;">
                체험을 통한 안전한 금융 세상, 여기서 시작하세요
              </v-card-title>
            </v-col>
          </v-row>
        </v-card>
      </v-flex>

      <!-- 카드 사이에 구분선 추가 -->
      <v-divider></v-divider>

    </v-layout>
  
</v-container>
</template>

<script>
  export default {
    name: 'HelloWorld',
  }
</script>
<style scoped>
.content {
  background-color: black;
}
/* 그림자 효과 클래스 정의 */
.elevation-2 {
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease; /* 스케일 변환 효과를 추가합니다. */
}

/* 스크롤 시 v-card가 살짝 뜨도록 스케일 변환 효과를 적용합니다. */
.elevation-2:hover {
  transform: scale(1.02); /* 살짝 뜨는 효과를 주려면 스케일 값을 조절합니다. */
}
</style>
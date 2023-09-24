<template>
  <v-container class="content">
    <v-row class="text-center">

      <v-col class="image-container">
        <v-img :src="require('../assets/CRIME.png')" class="my-3 crime-image" contain height="120" />
        <v-img :src="require('../assets/MainImage5.png')" class="my-3 mx-auto main-image" contain width="100%" style= "opacity: 0.2; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);" />
      </v-col>
    </v-row>

    
    <v-layout column>
      <v-divider class="my-10"/>

      <v-flex>
        <v-card class="mb-4 mx-auto elevation-2" width="90%" height="30%" dark>
          <v-row>
          <v-col cols="4">
            <v-img src="../assets/init1.svg" height="full" style="max-width: 100%; opacity: 0.8; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);"></v-img>
          </v-col>
          <v-col cols="8" class="text-left mt-4 mb-4">
          <v-card-title class="text-right mt-4 mb-4 my-4 card-content" >
            <br>금융 사기로부터 당신의 자산을 지키는 최선의 파트너.
          </v-card-title>
          </v-col>
          </v-row>
          
          
        </v-card>
      </v-flex>

      <v-divider class="my-10"></v-divider>

      <v-flex>
        <v-card class="mb-4 mx-auto elevation-2" width="90%" height="30%" dark>
          <v-row>
            <v-col cols="8" class="text-left mt-4 mb-4">
              <v-card-title class="text-left mt-4 mb-4 my-4 card-content" >
                자산 모니터링으로 금융 사기를 빠르게 감지하고 <br>미리 예방하세요
              </v-card-title>
          </v-col>
          <v-col cols="4">
            <v-img src="../assets/init2.svg" height="full" style="max-width: 100%; opacity: 0.8; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);"></v-img>
          </v-col>
          </v-row>
        </v-card>
      </v-flex>

      <v-divider class="my-10"></v-divider>

      <v-flex>
        <v-card class="mb-4 mx-auto elevation-2" width="90%" height="30%" dark>
          <v-row>
            <v-col cols="4">
              <v-img src="../assets/init3.svg" height="full" style="max-width: 100%; opacity: 0.8; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);"></v-img>
            </v-col>
            <v-col cols="8">
              <v-card-title class="text-right mt-4 mb-4 my-4" style="font-weight: 300; font-size: 24px;">
                <br>체험을 통한 안전한 금융 세상, Crime-Fin에서 시작하세요
              </v-card-title>
            </v-col>
          </v-row>
        </v-card>
      </v-flex>

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
p, div{
  font-family: 'Pretendard-Regular', sans-serif;
}
.content {
  width: 100%;
  background-color: black;
}
.card-content{
  font-weight: 300; 
  font-size: 24px;
}
.elevation-2 {
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease; 
}

.elevation-2:hover {
  transform: scale(1.07); 
}
.image-container {
    position: relative;
}

.crime-image {
    position: absolute;
    z-index: 1; /* 이미지가 다른 이미지 위에 오도록 설정 */
    top: 10%; /* 부모 요소의 중앙으로부터 상단 위치 설정 */
    left: calc(44% - (200px / 2)); /* 가로 중앙 정렬 */
    /* opacity: .8; */
}

.main-image {
   z-index : 0; /* 백그라운드로 보내기 위해서 */ 
}
</style>
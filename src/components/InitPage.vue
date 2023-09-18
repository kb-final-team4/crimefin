<template>
  <v-app class="black-bg">
    <v-row>
      <!-- 왼쪽 섹션 (8:4 비율 중 8 부분) -->
      <v-col cols="8" class="scrollable-section">
        <!-- 카드를 왼쪽 섹션 크기에 맞추기 위해 width와 height 속성 설정 -->
        <card-component
          :card-width="'100%'"    
          :card-height="'100%'"   
          card-content= <InitIntro/>
        ></card-component>
      </v-col>

      <!-- 오른쪽 섹션 (8:4 비율 중 4 부분) -->
      <v-col cols="4" class="non-scrollable-section">
        <!-- 카드를 오른쪽 섹션 크기에 맞추기 위해 width와 height 속성 설정 -->
        <card-component
          :card-width="'100%'"    
          :card-height="'100%'"   
          card-content=<InitStart/>   
        ></card-component>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
import InitIntro from './InitIntro.vue'; // 카드 컴포넌트 불러오기
import InitStart from './InitStart.vue'; // 카드 컴포넌트 불러오기

export default {
  components: {
    InitIntro, InitStart// 카드 컴포넌트 등록
  },
};
</script>

<style>

.scrollable-section {
  background-color: black; /* 왼쪽 섹션 배경색을 검정색으로 설정 */
  overflow-y: scroll; /* 세로 스크롤을 허용하는 스타일 */
  height: 100vh; /* 화면 높이에 맞게 설정 (설정에 따라 조정 가능) */
  width:full;

}

.scrollable-section::-webkit-scrollbar {
    display: none; /* 크롬, 사파리, 오페라, 엣지 */
}

.non-scrollable-section {
  background-color: black; /* 오른쪽 섹션 배경색을 검정색으로 설정 */
  height: 100vh; /* 화면 높이에 맞게 설정 (설정에 따라 조정 가능) */
  /* 오른쪽 섹션에는 스크롤을 비활성화하기 위해 추가 스타일 설정이 필요하지 않습니다. */
}

.black-bg {
  background-color: black; /* 현재 페이지의 배경색을 검정색으로 설정 */
}
</style>
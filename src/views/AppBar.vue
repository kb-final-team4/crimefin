<template>
    <v-app-bar app color="navy" height="128" dark>
        <!-- 로고 이미지를 클릭하면 메인페이지로 이동합니다. -->
        <router-link to="/main">
          <v-img src="../assets/CRIME.png" alt="로고" height="120" width="240"/>
        </router-link>
    
        <!-- 버튼 그룹 -->
        <v-btn-toggle v-model="selectedButton" class="ml-4" >
          <v-btn @click="navigateTo('ServiceInfo')" dark>CrimeFin?</v-btn>
          <v-btn @click="navigateTo('Agency')" dark>가까운 기관 조회</v-btn>
          <v-btn @click="navigateTo('DigitalExp')" dark>디지털 체험관</v-btn>
        </v-btn-toggle>
    
        <!-- 로그인한 사용자 정보 및 토글 버튼 -->
        <v-menu offset-y v-model="menuOpen">
          <template v-slot:activator="{ on, attrs }">
            <v-btn text v-bind="attrs" v-on="on" dark>
              {{ loggedInUser }} 님
              <v-icon>mdi-chevron-down</v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item @click="goTo('ChangeInfo')" >마이페이지</v-list-item>
            <v-list-item @click="goTo('DashBoard')" >자산/대시보드</v-list-item>
          </v-list>
        </v-menu>
      </v-app-bar>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedButton: null,
        menuOpen: false,
        loggedInUser: this.$session.get('loginMemberName'), // 로그인한 사용자 이름을 설정합니다.
      };
    },
    methods: {
      navigateTo(pageName) {
        this.$router.push({ name: pageName });
      },
      goTo(pageName) {
        this.$router.push({ name: pageName });
        this.menuOpen = false; // 메뉴 닫기
      },
      toggleMenu() {
        // 메뉴 토글 버튼을 클릭했을 때 메뉴 열기/닫기 상태를 변경합니다.
        this.menuOpen = !this.menuOpen;
      },
    },
  };
  </script>
  
  <style scoped>
  /* 스타일을 필요에 따라 추가하세요. */
  
  /* 상단바 글씨 색상을 하얀색으로 변경합니다. */
  .v-btn, .v-list-item__title {
    color: white !important;
  }
  </style>
  
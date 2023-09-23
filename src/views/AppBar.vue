<template>
    <v-app-bar app color="navy" height="128" dark>
        
      <v-col :cols="2">
        <router-link to="/main">
          <v-img v-bind:style="{margin: '24px'}" src="../assets/CRIME.png" alt="로고" height="80" width="160"/>
        </router-link>
      </v-col>
      <v-col :cols="5"></v-col>
      <v-col :cols="3">
        <!-- 버튼 그룹 -->
        <v-btn-toggle v-model="selectedButton" v-bind:style="{float: 'right'}" >
          <v-btn v-bind:style="{border: 'none'}" @click="navigateTo('ServiceInfo')" dark>CrimeFin?</v-btn>
          <v-btn v-bind:style="{border: 'none'}" @click="navigateTo('Agency')" dark>가까운 기관 조회</v-btn>
          <v-btn v-bind:style="{border: 'none'}" @click="navigateTo('ExperienceLobby')" dark>디지털 체험관</v-btn>
        </v-btn-toggle>
      </v-col>
      <v-col :cols="2">
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
            <v-list-item @click="Logout()" >로그아웃</v-list-item>
          </v-list>
        </v-menu>
      </v-col>
      </v-app-bar>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedButton: null,
        menuOpen: false,
        loggedInUser: this.$session.get('loginMemberName'), // 로그인한 사용자 이름 설정
      };
    },
    methods: {
      navigateTo(pageName) {
        this.$router.push({ name: pageName });
      },
      goTo(pageName) {
        this.$router.push({ name: pageName });
        this.menuOpen = false; 
      },
      Logout() {
        this.menuOpen = false; 
      },
      toggleMenu() {
        this.menuOpen = !this.menuOpen;
      },
    },
  };
  </script>
  
<style scoped>

  .v-btn, .v-list-item__title {
    color: white !important;
  }
</style>
  
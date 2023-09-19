<template>
    
    <v-navigation-drawer  app>
        <app-bar /> 
   
      <v-list>
        <v-list-item>
          <v-list-item-avatar>
            <v-img src="logo.png"></v-img>
          </v-list-item-avatar>
          <v-list-item-title>CrimeFin</v-list-item-title>
        </v-list-item>

        <v-divider></v-divider>

        <v-list-item link to="/changeinfo2">
        <v-list-item-icon>
          <v-icon>mdi-account</v-icon>
        </v-list-item-icon>
        <v-list-item-title class="larger-text">회원정보 수정</v-list-item-title>
      
      </v-list-item>

      <v-list-item link to="/changepassword">
        <v-list-item-icon>
          <v-icon>mdi-lock</v-icon>
        </v-list-item-icon>
        <v-list-item-title class="larger-text">비밀번호 수정</v-list-item-title>
      </v-list-item>

      <v-list-item link to="/memberresign">
        <v-list-item-icon>
          <v-icon>mdi-cog</v-icon>
        </v-list-item-icon>
        <v-list-item-title class="larger-text">회원탈퇴</v-list-item-title>
      </v-list-item>
      <!-- 추가 메뉴 아이템 작성 -->
    </v-list>
    </v-navigation-drawer>
  </template>

<script>
import AppBar from '../views/AppBar.vue';
export default {
    components: {
      'app-bar':AppBar, // 상단바 컴포넌트를 등록합니다.
    },

  data() {
    return {
      items: [
        { text: ' 회원정보 수정', icon: 'mdi-account' },
        { text: ' 비밀번호 수정', icon: 'mdi-lock' },
        { text: ' 회원탈퇴', icon: 'mdi-cog' }
      ]
    };
  }
};
</script>
<style>
    .larger-text{
        font-size:25px;
    }

    .larger-item{
        font-size:30px;
    }
</style>
<template>
  <v-app>
    <!-- 상단바 컴포넌트를 import 해서 불러옵니다 -->
    <AppBar />

    <v-main class="align-center" v-bind:style="{background : '#DDE6ED'}">
      <v-container class="exp-container">
        <!-- 카드를 가로로 배치하는 Flexbox 레이아웃 -->
        <div class="d-flex justify-space-between align-center">
          <!-- 가로로 3개의 카드 배치 -->
          <!-- 첫 번째 카드 -->
          <v-card class="exp-card">
          <router-link to="/expmessagegame">
            <v-img src="../assets/exp_message.png" width="300px" height="300px"/>
          </router-link>
          </v-card>
          <!-- 두 번째 카드 -->
          <v-card class="exp-card">
          <router-link to="/exptypegame">
            <v-img  src="../assets/exp_type.png" width="300px" height="300px"/>
          </router-link>
          </v-card>
          <!-- 세 번째 카드 -->
          <v-card class="exp-card">
            <router-link to="/expvoicegame">
              <v-img src="../assets/exp_voice.png" width="300px" height="300px"/>
            </router-link>
          </v-card>
        </div>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import AppBar from "../views/AppBar.vue"; // 상단바 컴포넌트를 import 합니다.

export default {
  components: {
    AppBar, // 상단바 컴포넌트를 등록합니다.
  },
  methods: {

  },
};
</script>

<style scoped>
.exp-container{
  background: #9DB2BF;
  border-radius: 20px;
  padding: 30px;
}

.exp-card{
  background : #e6edf7;
  padding: 20px;
  border-radius: 20px;
}
</style>

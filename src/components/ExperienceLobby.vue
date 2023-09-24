<template>
  <v-app>
    <!-- 상단바 컴포넌트를 import 해서 불러옵니다 -->
    <AppBar />

    <v-main class="align-center main-container" >
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
    <!-- Footer -->
    <Footer/>
  </v-app>
</template>

<script>
import AppBar from "../views/AppBar.vue"; 
import Footer from '../views/Footer.vue';

export default {
  components: {
    AppBar,Footer,
  },
  methods: {

  },
};
</script>

<style scoped>
.main-container{
  height: 100vh;
  background-image: url("../assets/19.png");
  background-size: cover;
  background-position: center;
}
/* .exp-container{
  background: #DDE6ED;
  border-radius: 20px;
  padding: 30px;
} */

.exp-card{
  background : #e6edf7;
  padding: 20px;
  border-radius: 20px;
}
</style>

<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main>
    </v-main>

  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue'; // 상단바 컴포넌트 import

export default {
  components: {
    AppBar, // 상단바 컴포넌트 등록
  },

};
</script>

<style >

</style>

<!-- /exptypegame -->
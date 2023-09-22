<template>
  <v-footer height="150px" dark>
<!--    <v-row justify="center" no-gutters>
      <v-btn
          v-for="link in links"
          :key="link"
          color='#4c4d4d'
          variant="text"
          class="mx-2"
          rounded="xl"
      >
        <img :src="getImgUrl(link)" v-bind:alt="link" height="20px" width="20px"/>
      </v-btn>
      <v-col v-bind:style="{color: '#aeb0b0'}" class="text-center mt-4" cols="12">
        {{ new Date().getFullYear() }} — <strong>Crime-Fin</strong>
      </v-col>
    </v-row>-->
    <div class="v-card__text text-center" v-bind:style="{color: 'grey', fontFamily: 'kangyuseok', fontSize: '37px'}">
      phishing me if you can
    </div>

    <div class="v-card__text text-center" v-bind:style="{color: 'grey'}">
      0woogie | kangyuseok | merrykang | seohee99 | joareum | Austin-Choi
    </div>
    <div class="v-card__text text-center" v-bind:style="{marginTop: '-20px'}">
      Copyright © 2023 Crime-Fin. All rights reserved
    </div>
  </v-footer>
</template>
<script>
export default {
  data() {
    return {
      links: ['blog', 'facebook', 'insta', 'kakao', 'youtube'],
      selectedButton: null,
      menuOpen: false,
      loggedInUser: this.$session.get('loginMemberName'), // 로그인한 사용자 이름을 설정합니다.
    };
  },
  methods: {
    //이미지 url 삽입용
    getImgUrl(sns) {
      var images = require.context('../assets/', false, /\.png$/)
      return images('./sns_' + sns + ".png")
    },
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
@font-face {
  font-family: 'kangyuseok';
  src: url("../assets/fonts/Blacksword.otf");
}
/* 스타일을 필요에 따라 추가하세요. */

/* 상단바 글씨 색상을 하얀색으로 변경합니다. */
.v-btn, .v-list-item__title {
  color: white !important;
}
</style>
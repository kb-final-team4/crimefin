<template>
  <v-app>
    <AppBar />

    <v-main>
      <v-container fluid class="btn-container">
        <div class="d-flex justify-center">

          <v-card class="mx-2 mt-6 mr-2  cover1" width="23%" tile @click="redirectToPhishingNumber('Phone')">
            <v-card-title>
              <v-card class="mx-auto inner-cover" width="95%" height="60%" tile>
                <v-img src="../assets/12.png" class="mx-auto" width="100%" height="100%"></v-img>
              </v-card>
            </v-card-title>
            
          </v-card>

          <v-card class="mx-2 mt-6 mr-2 cover2" width="23%" tile @click="redirectToPhishingNumber('Message')">
            <v-card-title>
              <v-card class="mx-auto inner-cover" width="95%" height="60%" tile>
                <v-img src="../assets/13.png" class="mx-auto" width="100%" height="100%"></v-img>
              </v-card>
            </v-card-title>
            
          </v-card>

          <v-card class="mx-2 mt-6 mr-2 cover3" width="23%" tile>
            <v-card-title>
              <v-card class="mx-auto inner-cover" width="95%" height="60%" tile  @click="redirectToPhishingLink('SNS')" >
                <v-img
                  src="../assets/14.png"
                  class="mx-auto"
                  width="100%"
                  height="100%"
                ></v-img>
              </v-card>
            </v-card-title>
          </v-card>
        </div>
      </v-container>

      <v-container fluid class="news-container">
        <div class="d-flex flex-column align-center mt-6">
          <h2 class="mb-4 text-center w-25 font-weight-bold">관심있는 사례, 지금 검색해보세요!</h2>

          <!-- 검색창 -->
          <v-text-field
            v-model="searchText"
            label="Search..."
            single-line
            hide-details
            class="w-25 mb-4"
          ></v-text-field>

          <!-- 키워드 버튼들 -->
          <div class="d-flex flex-wrap justify-center mt-4 w-25">
            <v-btn v-for="(keyword, index) in keywords" :key="index" @click="fetchData(keyword)" class="mr-2 mb-2">{{ keyword }}</v-btn>
          </div>

          <!-- 카드들 -->
          <div v-if="cards.length > 0" class="mt-6 d-flex flex-wrap justify-center">
            <v-card v-for="(card, index) in cards" :key="'card-' + index" width=100% tile outlined >
              {{ card.content }}
            </v-card>
          </div>
        </div>
      </v-container>

      
    </v-main>
  </v-app>
</template>

<script>
import AppBar from "../views/AppBar.vue"; 

export default {
  components: {
    AppBar, 
  },
  data() {
  return {
      searchText: '',
      keywords: ['#keyword1', '#keyword2', '#keyword3','#keyword4','#keyword5'],
      cards: [],
  };
  },
  methods: {
    redirectToPhishingNumber(phishingtype) {
      this.$router.push({ name: 'PhishingNumber', params: {phishingtype} })
    },
    redirectToPhishingLink(phishingtype) {
      this.$router.push({ name: "PhishingLink", params: {phishingtype}});
    },
    fetchData(keyword) {

      // 이 부분에서 실제 데이터를 가져오는 로직을 구현해야 합니다.
      // 예시로 간단한 더미 데이터를 생성하였습니다.
      let dummyData = [
      {
      content: `${keyword} 관련 내용1`,
      },
      {
      content: `${keyword} 관련 내용2`,
      },
      {
      content: `${keyword} 관련 내용3`,
      },
      ];

      this.cards = dummyData;
      },
  },
};
</script>

<style scoped>
.cover1, .cover3 {
  background-color: #14274E !important;
  border-radius: 25px !important;
  margin: 3% !important;
}

.cover2 {
  background-color: #9BA4B4 !important;
  border-radius: 25px !important;
  margin: 3% !important;
}
.inner-cover{
  background-color: #F1F6F9;
  border-radius: 25px !important;
  margin-top: 1% !important;
}

.cover1, .cover2, .cover3 {
  margin-top: 8% !important;
  transition: all .3s ease;
}

.cover1:hover,
.cover2:hover,
.cover3:hover {
  transform: translateY(-5px);
  box-shadow: 0px 10px 20px rgba(0,0,0,.5);
}

.v-text-field { 
margin-top: 20px; 
} 

.v-btn { 
margin-right: 10px; 
margin-bottom: 10px; 
} 

.v-card { 
margin-right: 10px; 
margin-bottom: 20px; 
} 

.btn-container {
   height :83vh;
   /* background-color: #F1F6F9; */
   background-image: url("../assets/main_bg.png");
   background-size: cover;
   background-position: center;
}

</style>

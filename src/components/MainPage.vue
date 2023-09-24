<template>
  <v-app>
    <AppBar/>

    <v-main>
      <v-container fluid class="btn-container">
        <div class="d-flex justify-center">
          <v-card class="mx-2 mt-6 mr-2 cover1" width="23%" tile @click="redirectToPhishingNumber('Phone')" >
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
              <v-card class="mx-auto inner-cover" width="95%" height="60%" tile @click="redirectToPhishingLink('SNS')">
                <v-img src="../assets/14.png" class="mx-auto" width="100%" height="100%"></v-img>
              </v-card>
            </v-card-title>
          </v-card>
        </div>
      </v-container>

      <v-container fluid class="news-container">
        <div class="d-flex flex-column align-center mt-6">
          
          <p class="news-title"> 나와 유사한 사례, 지금 검색해보세요!</p>
          
          <div class="input-container mx-auto">
            <v-form>
              <v-row>
                <v-col cols="9">
                  <v-text-field v-model="searchText" label="검색어를 입력해주세요." single-line dense class="large-input"></v-text-field>  
                </v-col>
                <v-col cols="3">
                  <v-btn class="mx-auto" color="#14274E">조회하기</v-btn> 
                </v-col>
              </v-row>
            </v-form>
          </div>


          <div class="keywords-container">
            <v-btn v-for="(keyword, index) in keywords" :key="index" @click="fetchData(keyword)" class="keywordbtn">{{ keyword }}</v-btn>
          </div>

          <v-flex>
          <div v-if="cards.length > 0" class="mb-4 mx-auto" >
            <v-card v-for="(card, index) in cards" :key="'card-' + index" width="60%" tile outlined @click="handleCardClick(card)" class="mx-auto news">
              
              <v-row>
                <v-col cols="4">
                  <v-img :src="require(`../assets/${card.img}`)" style=" max-width: 100%; opacity: 0.8; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);"></v-img>
                </v-col>

                <v-col cols="8">
                  <v-card-title class="text-left" style="font-weight: bold;">
                    {{ card.title }}
                  </v-card-title>

                  <v-divider></v-divider>
                  
                  <v-card-subtitle class="text-left" style="font-weight: 300; font-size: 18px">
                    {{ card.content }}
                  </v-card-subtitle>
                </v-col>
              </v-row>
           
            </v-card>
          </div>
        </v-flex>

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
  data() {
    return {
      searchText: "",
      keywords: [
        "#자녀사칭",
        "#보이스피싱",
        "#금융사기",
        "#현금수거책",
        "#스미싱",
      ],
      cards: [],
    };
  },
  methods: {
    redirectToPhishingNumber(phishingtype) {
      this.$router.push({ name: "PhishingNumber", params: { phishingtype } });
    },
    redirectToPhishingLink(phishingtype) {
      this.$router.push({ name: "PhishingLink", params: { phishingtype } });
    },
    fetchData(keyword) {

      if (keyword === "#자녀사칭") {
        let dummyData = [
          {
            title: `"나 폰 고장 났어" 유학생 자녀에게 온 문자 눌렀다 '날벼락'`,
            href: "http://news.lghellovision.net/news/articleView.html?idxno=437466",
            img: "similar1.jpg",
            content: `["전기통신사업법 위반으로 현행범 체포합니다."] 자녀를 사칭해 폰이 고장났다는 내용의 피싱 문자를 보낸 일당입니다. 서산경찰서는 피싱 문자를 보내 피해자 3명의 현금 6백90여만 원을 가로챈 혐의로 5명을 구...`,
          },
          {
            title: `제주서 '우체국 직원 사칭' 보이스피싱 현금 수거책 검거`,
            href: "http://www.jemin.com/news/articleView.html?idxno=761164",
            img: "similar2.jpg",
            content: `보이스피싱 조직원들은 이날 오전 9시께 우체국 직원을 사칭해 개인정보가 유출됐다며 현금을 인출해 검정색 비닐봉지에 담아 아파트 우편함에 넣도록 했다. 같은 날 오후 7시33분께 피해자 자녀의 신고를 접수한...`,
          },
          {
            title: `"엄마, 나 폰 고장났어" 사칭 문자로 돈 뜯은 30대 실형`,
            href: "https://newsis.com/view/?id=NISX20230911_0002445343&cID=10201&pID=10200",
            img: "similar3.jpg",
            content: `신분증·계좌·비밀번호를 보내줘'라는 내용의 자녀 사칭 문자를 발송한 뒤 악성 앱 링크를 보냈다. 이들은 이후 유심으로 피해자들 명의의 휴대전화를 번호이동 시킨 뒤 새로 전화를 개통했고, 피해자 개인정보를 ...`,
          },
        ];

        this.cards = dummyData;
      }

      if (keyword === "#보이스피싱") {
        let dummyData = [
          {
            title: `은행연합회, 카카오톡으로 보이스피싱 예방 나서`,
            href: "https://newsis.com/view/?id=NISX20230922_0002460796&cID=15001&pID=15000",
            img: "similar4.jpg",
            content: `추석명절을 앞두고 급증할 것으로 예상되는 보이스피싱 피해 예방을 위한 주의안내 문자를 카카오톡 공식 채널에서 약 2900만명의 카카오톡 가입자에게 발송할 예정이다. 카카오에서 인증된 채널인 경우 채팅방 ...`,
          },
          {
            title: `인터넷은행 과장 행세…보이스피싱 현금수거책 징역형`,
            href: "https://view.asiae.co.kr/article/2023092215454728840",
            img: "similar5.jpg",
            content: `인터넷은행 과장 행세를 하며 보이스피싱 현금수거책 역할을 한 20대 남성이 징역형을 선고 받았다. 23일 서울동부지법 형사3단독 민성철 판사는 지난 20일 사기 혐의로 기소된 A(26)씨에게 징역 1년2월을 선고했...`,
          },
          {
            title: `"추석 연휴 피싱·스미싱 평소보다 10% 이상 늘어"`,
            href: "https://www.yna.co.kr/view/AKR20230922059500017?input=1195m",
            img: "similar6.jpg",
            content: `라온화이트햇 '스마트 안티 피싱' 데이터 분석 결과 보안·인증기업 라온시큐어는 추석 전후 보이스피싱과 스미싱(휴대전화 문자메시지를 이용한 피싱) 범죄가 평소보다 상당폭 늘어 주의가 필요하다고 22일 밝혔다...`,
          },
        ];

        this.cards = dummyData;
      }
    },
    handleCardClick(card) {
      if (card.href) {
        window.open(card.href, "_blank"); // 
      }
    },
  },
};
</script>

<style scoped>

@font-face {
    font-family: 'LINESeedKR-Bd';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}
@font-face {
  font-family: 'TheJamsil5Bold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/TheJamsil5Bold.woff2') format('woff2');
  font-weight: normal;
  font-style: normal;
}
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}

p, div{
  font-family: 'Pretendard-Regular', sans-serif;
}
.cover1,
.cover3 {
  background-color: #14274e !important;
  border-radius: 25px !important;
  margin: 3% !important;
}

.cover2 {
  background-color: #9ba4b4 !important;
  border-radius: 25px !important;
  margin: 3% !important;
}
.inner-cover {
  background-color: #f1f6f9;
  border-radius: 25px !important;
  margin-top: 1% !important;
}

.cover1,
.cover2,
.cover3 {
  margin-top: 8% !important;
  transition: all 0.3s ease;
}

.cover1:hover,
.cover2:hover,
.cover3:hover,
.news:hover {
  transform: translateY(-5px);
  box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.5);
}

.v-text-field {
  margin-top: 20px;
}
.v-btn { 
   background-color:#14274E ;
   color:white ;  
   
} 

.v-card {
  margin-right: 10px;
  margin-bottom: 20px;
}

.btn-container {
  height: 83vh;
  /* background-color: #F1F6F9; */
  background-image: url("../assets/main_bg.png");
  background-size: cover;
  background-position: center;
}
.news-container{
  height: full;
  font-family: 'TheJamsil5Bold';
}

.input-container {
  display: flex;
  justify-content: center;
  width: 40%;
}
.news-title{
  font-family: 'LINESeedKR-Bd';
  font-size: 40px;
  font-weight: 900;
  margin: 4%;
}
.large-input .v-input__control {
    height: 80px;
}

.v-input__slot, .large-input  {
    font-size: 60px; 
}

.keywords-container{
  width: 50%;
  display: flex;
  justify-content: center;
  margin: 2%;
}
.keywordbtn{
  color: #9ba4b4;
  margin: 1.5%;
  font-size: 20px;
  font-family: 'TheJamsil5Bold';
}
.news{
  font-family: 'TheJamsil5Bold';
  margin: 20px;
}

</style>

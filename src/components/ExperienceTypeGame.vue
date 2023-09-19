<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main>
      <v-container>
        <v-sheet class="gameMainSheet mx-auto">
          <v-col :cols="3"></v-col>
          <v-col :cols="6">
          <v-row class="justify-center">
            보이스 피싱 대처 유형 테스트
          </v-row>
            <br>
          <!-- 게임 소개 -->
          <div class="gameIntro"  v-if="isGameIntro === true">
            <v-row class="justify-center v-card__text" id="gameIntro" v-if="isGameIntro === true">
              <v-sheet>
                <v-row>
                  당신의 피싱 대처 유형은?<br>
                  10개의 가벼운 테스트로<br>
                  지금 확인해볼까요?<br>
                </v-row>
                <v-row class="justify-center">
                  <v-btn @click="startGame">시작하기</v-btn>
                </v-row>
              </v-sheet>
            </v-row>
          </div>

          <!-- 문제 나오고 답변 고르는 곳 -->
          <div class="questArea" v-if="this.currentQuestion !== 10 && isQuestArea === true">
            <v-row class="d-block">
              <v-row class="progressBar">
                <!-- 진행도 -->
                  <div id="bar" class="innerbar"></div>
              </v-row>
              <v-row>
                <!-- 질문 영역 -->
                <div v-if="this.currentQuestion===0"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===1"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===2"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===3"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===4"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===5"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===6"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===7"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===8"> Q. {{ this.questions[this.currentQuestion] }}</div>
                <div v-if="this.currentQuestion===9"> Q. {{ this.questions[this.currentQuestion] }}</div>
              </v-row>
            </v-row>
            <v-row>
              <!-- 선택 1 좋은 선택들 -->
              <v-btn @click="questReply(2)">{{ this.questReplyUpper[this.currentQuestion] }}</v-btn>
            </v-row>
            <v-row>
              <!-- 선택 2 나쁜 선택들 -->
              <v-btn @click="questReply(1)">{{ this.questReplyLower[this.currentQuestion] }}</v-btn>
            </v-row>
          </div>

          <!-- 결과 페이지 -->
          <v-row class="justify-center v-card__text" id="typeResult" v-if="this.currentQuestion === 10">
            <v-sheet>
              <!-- 여기다 점수대별로 유형 만들기 -->
              <div v-if="this.score >= 15 && this.score <= 20">
              <v-row >
                피싱 유형 테스트 결과 15~20점대<br>
              </v-row>
              <v-row>
                피싱유형 테스트 내용 15~20점대<br>
              </v-row>
              </div>
              <v-row class="justify-center">
                <router-link to="/explobby">
                  <v-btn>메인으로</v-btn>
                </router-link>
              </v-row>
            </v-sheet>
          </v-row>
          </v-col>
          <v-col :cols="3"></v-col>
        </v-sheet>
      </v-container>
    </v-main>

  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue';

export default {
  components: {
    AppBar, // 상단바 컴포넌트 등록
  },
  data: function () {
    return {
      score : 0, // 유형 판별 위한 점수
      isGameIntro : true, //게임 소개 시트
      isQuestArea : false, //본게임
      //10번까지 질문 담음
      questions : [
        '질문1',
        '질문2',
        '질문3',
        '질문4',
        '질문5',
        '질문6',
        '질문7',
        '질문8',
        '질문9',
        '질문10'
      ],
      //위쪽 선택 버튼 내용 담음
      questReplyUpper : [
          '좋은 대답 1',
          '좋은 대답 2',
          '좋은 대답 3',
          '좋은 대답 4',
          '좋은 대답 5',
          '좋은 대답 6',
          '좋은 대답 7',
          '좋은 대답 8',
          '좋은 대답 9',
          '좋은 대답 10',
      ],
      //아래쪽 선택 버튼 내용 담음
      questReplyLower : [
        '나쁜 대답 1',
        '나쁜 대답 2',
        '나쁜 대답 3',
        '나쁜 대답 4',
        '나쁜 대답 5',
        '나쁜 대답 6',
        '나쁜 대답 7',
        '나쁜 대답 8',
        '나쁜 대답 9',
        '나쁜 대답 10',
      ],
      currentQuestion : -1, // 현재 질문 index
      isPlaying : false,
    }
  },
  methods: {
    startGame(){
      this.isGameIntro = false;
      this.isQuestArea = true;
      this.currentQuestion = 0;
    },
    questReply(num) {
      this.score += num;
      this.currentQuestion += 1;
      this.updateProgressbar();
    },
    updateProgressbar() {
      let el = document.getElementById("bar");
      let width = (this.currentQuestion / 9) * 100 + "%"; //10문항이니까 0~9
      width = parseFloat(width).toFixed(2);
      if (width > 100) width = 100;

      console.log("updateProgressbar width : " + width);
      let widthStr = width + "%";
      el.style.width = widthStr;
    },
  }
};
</script>

<style >
.progressBar {
  max-width: 500px;
  width: 90%;
  margin: 10px auto;
  margin-top: 10px;
  height: 8px;

  border-radius: 3px;
  background: linear-gradient(#6fa6d66c, #7db1df54);
}

.innerbar {
  max-width: 500px;
  height: 100%;
  text-align: right;
  height: 8px; /* same as #progressBar height if we want text middle aligned */
  width: 0%;
  border-radius: 3px;
  background: linear-gradient(#5be6ba, #5ed1ad);
}
.gameMainSheet{
  width: 800px;
  height: 800px;
}
</style>

<!-- /exptypegame -->
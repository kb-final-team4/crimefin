<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main>
      <v-container>
        <v-sheet id="questMainSheet">
          <v-row class="justify-center v-card__title" >
            메신저 피싱 체험
          </v-row>

          <!-- 게임 소개 -->
          <v-row class="justify-center v-card__text" id="gameIntro" v-if="isGameIntro === true">
            <v-sheet>
            <v-row>
              누구나 당할수 있는 메신저 피싱,<br>
              실제 사례들로 구성된 가벼운 테스트로<br>
              확인해볼까요?<br>
            </v-row>
            <v-row class="justify-center">
              <v-btn @click="startGame">시작하기</v-btn>
            </v-row>
            </v-sheet>
          </v-row>

          <!-- 역할 고르기 -->
          <v-row id="selectRole" v-if="isSelectRole === true">
            <v-sheet class="mx-auto">
              <v-row><v-btn @click="showQuest('worker')">직장인</v-btn></v-row>
              <v-row><v-btn @click="showQuest('parents')">부모님</v-btn></v-row>
              <v-row><v-btn @click="showQuest('elderly')">노인</v-btn></v-row>
            </v-sheet>
          </v-row>

          <!-- 게임 : 직장인 -->
          <v-row class="playGame" v-if="isPlaying === true && isWorkerGame === true">
            <v-col :cols="1"></v-col>
            <v-col :cols ="5">
              <!-- 질문 렌더링 -->
              <v-sheet class="questArea">
                <v-row v-if="score===0">
                  [신세계백화점] 추석 이벤트 선물 발송되었습니다. 앱 설치 후 위탁 장소를 지정해 주세요. ssg.li.nk/23dbcES2F
                </v-row>
                <v-row v-if="score===1">
                  추석을 맞이하여 전사 직원 대상으로 발송된 메시지입니다. 본인 확인을 위한 앱 설치 후 간단한 전화번호 인증 부탁드립니다.
                </v-row>
                <v-row v-if="score ===2 ">
                  주소지 정보가 개인정보에 해당되어 까다롭지만 꼭 해주셔야 상품을 수령하실 수가 있습니다.
                </v-row>
              </v-sheet>
            </v-col>
            <v-col :cols="5">
              <v-sheet class="answerArea">
                <v-row class="goodAnswer">
                  <v-btn v-if="score===0" @click="questReply(0)" >1. (링크 확인) </v-btn>
                  <v-btn v-if="score===1" @click="questReply(1)" >1. 회사 이벤트라면 주소 정보가 있어서 따로 인증이 필요없을텐데요?</v-btn>
                  <v-btn v-if="score===2" @click="questReply(0)" >1. 네 인증 후 다시 연락드리겠습니다. </v-btn>
                </v-row>
                <v-row class="badAnswer">
                  <v-btn v-if="score===0" @click="questReply(1)" >2. 저 응모한적 없는데요.</v-btn>
                  <v-btn v-if="score===1" @click="questReply(0)" >2. (링크를 눌러 앱을 설치한다) </v-btn>
                  <v-btn v-if="score===2" @click="questReply(1)" >2. 인사과에 연락해서 확인 먼저 해 볼게요.</v-btn>
                </v-row>
              </v-sheet>
            </v-col>
            <v-col :cols="1"></v-col>
          </v-row>

          <!-- 게임 : 부모님 -->
          <v-row class="playGame" v-if="isPlaying === true && isParentsGame === true">
            <v-col :cols ="7">
              <!-- 질문 렌더링 -->
              <v-sheet class="questArea">

              </v-sheet>
            </v-col>
            <v-col :cols="5">
              <v-sheet class="answerArea">
                <v-row class="goodAnswer">
                  <!--
                    좋은 대답

                   -->
                  <v-btn></v-btn>
                </v-row>
                <v-row class="badAnswer">
                  <!--
                    나쁜 대답
                    이거 누르면 바로 예방실패 뜸
                   -->
                </v-row>
                <v-row>
                  <!--
                    다음 문제로
                    위에꺼 둘중 하나골라아 넘어가짐
                    이거 누르면 questArea에 대답(좋은대답 붙고 다음 질문 붙음
                   -->
                </v-row>
              </v-sheet>
            </v-col>
          </v-row>

          <!-- 게임 : 노인 -->
          <v-row class="playGame" v-if="isPlaying === true && isElderlyGame === true">
            <v-col :cols ="7">
              <!-- 질문 렌더링 -->
              <v-sheet class="questArea">

              </v-sheet>
            </v-col>
            <v-col :cols="5">
              <v-sheet class="answerArea">
                <v-row class="goodAnswer">
                  <!--
                    좋은 대답

                   -->
                  <v-btn @click="questReply(1)" >good answer</v-btn>
                </v-row>
                <v-row class="badAnswer">
                  <!--
                    나쁜 대답
                    이거 누르면 바로 예방실패 뜸
                   -->
                  <v-btn @click="questReply(0)" >bad answer</v-btn>
                </v-row>
              </v-sheet>
            </v-col>
          </v-row>

          <!-- 결과 : 예방 성공 -->
          <v-row class="justify-center v-card__text" id="goodEnding" v-if="isPlaying === false && isGoodEnding === true">
            <v-sheet>
              <v-row>
                예방 성공!!<br>
              </v-row>
              <v-row class="justify-center align-center">
                <v-img src="../assets/ending_good.png" height="40px" width="40px" />
              </v-row>
              <v-row class="justify-center">
                <router-link to="/explobby">
                  <v-btn>메인으로</v-btn>
                </router-link>
              </v-row>
            </v-sheet>
          </v-row>

          <!-- 결과 : 예방 실패 -->
          <v-row class="justify-center v-card__text" id="badEnding" v-if="isPlaying === false && isBadEnding === true">
            <v-sheet>
              <v-row>
                예방 실패!!<br>
              </v-row>
              <v-row class="justify-center align-center">
                <v-img src="../assets/ending_bad.png" height="40px" width="40px" object-fit="cover"/>
              </v-row>
              <v-row class="justify-center">
                <router-link to="/explobby">
                  <v-btn>메인으로</v-btn>
                </router-link>
              </v-row>
            </v-sheet>
          </v-row>

        </v-sheet>
      </v-container>
    </v-main>

  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue'; // 상단바 컴포넌트 import

export default {
  components: {
    AppBar, // 상단바 컴포넌트 등록
  },
  data: function () {
    return {
      score : 0,
      isGameIntro : true, //게임 소개 시트
      isSelectRole : false, //게임 역할 고르는 시트

      isPlaying : false, //엔딩 고를때 필요한 것 이거랑 아래 종류 둘다 true여야지 문제 푸는 상태
      isWorkerGame : false, // 직장인 문제
      isParentsGame : false, // 부모님 문제
      isElderlyGame : false, // 노인 문제

      isGoodEnding : false, // 예방 성공
      isBadEnding : false, // 예방 실패
    }
  },
  //score 변수 변화 감시
  watch: {
    score(newVal, oldVal){
      console.log("score 값 변화 발생 : " + oldVal + " -> " + newVal);
      if(newVal === 3)
      {
        this.isPlaying = false;
        this.isGoodEnding = true;
      }
    }
  },
  methods: {
    //역할 고르는 창 띄우기
    startGame(){
      this.isGameIntro = false;
      this.isSelectRole = true;
    },
    //역할에 맞는 문제 띄우기
    showQuest(role){
      this.isSelectRole = false;
      this.isPlaying = true;
      if(role === "worker")
        this.isWorkerGame = true;
      else if(role === 'parents')
        this.isParentsGame = true;
      else if(role === 'elderly')
        this.isElderlyGame = true;
      this.score = 0;
    },
    //대답 골랏을 때 행해지는 것들
    questReply(num){
      if(num === 0){
        this.isPlaying = false;
        this.isBadEnding = true;
      }
      else{
        this.score += num;
      }

    }
  },
};
</script>

<style >

</style>

<!-- /expmessagegame -->
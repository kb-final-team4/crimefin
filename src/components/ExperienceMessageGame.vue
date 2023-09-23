<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main>
      <v-container>
        <v-sheet id="questMainSheet">
          <v-row class="justify-center" v-bind:style="{ marginTop: '10px' }">
            <v-card>
              <v-img src="../assets/exp/msgGame/title.png" max-width="400" />
            </v-card>
          </v-row>

          <!-- 게임 소개 -->
          <v-row
            class="justify-center v-card__text"
            id="gameIntro"
            v-if="isGameIntro === true"
          >
            <v-sheet
              class="d-block"
              v-bind:style="{ padding: '30px' }"
              width="800"
            >
              <v-img
                class="mx-auto"
                src="../assets/exp/msgGame/intro.png"
                max-width="600"
                max-height="500"
              />
              <v-img
                class="mx-auto gamebtn"
                @click="startGame"
                src="../assets/exp/msgGame/start_btn.png"
              />
            </v-sheet>
          </v-row>

          <!-- 역할 고르기 -->
          <v-row id="selectRole" v-if="isSelectRole === true">
            <v-sheet class="mx-auto" width="800">
              <v-row>
                <v-img
                  class="mx-auto"
                  src="../assets/exp/msgGame/chooseRole.png"
                  max-width="400"
                  max-height="600"
                  v-bind:style="{ marginTop: '20px' }"
                />
              </v-row>
              <v-row>
                <v-col :cols="4"></v-col>
                <v-col :cols="2"
                  ><v-img
                    class="gamebtn mx-auto"
                    @click="showQuest('worker')"
                    src="../assets/exp/msgGame/student_btn.png"
                /></v-col>
                <v-col :cols="2"
                  ><v-img
                    class="gamebtn mx-auto"
                    @click="showQuest('worker')"
                    src="../assets/exp/msgGame/worker_btn.png"
                /></v-col>
                <v-col :cols="4"></v-col>
              </v-row>
              <v-row>
                <v-col :cols="4"></v-col>
                <v-col :cols="2"
                  ><v-img
                    class="gamebtn mx-auto"
                    @click="showQuest('parents')"
                    src="../assets/exp/msgGame/parents_btn.png"
                /></v-col>
                <v-col :cols="2"
                  ><v-img
                    class="gamebtn mx-auto"
                    @click="showQuest('elderly')"
                    src="../assets/exp/msgGame/elderly_btn.png"
                /></v-col>
                <v-col :cols="4"></v-col>
              </v-row>
            </v-sheet>
          </v-row>

          <!-- 게임 : 직장인 -->
          <v-row
            class="playGame"
            v-if="isPlaying === true && isWorkerGame === true"
          >
            <v-sheet class="mx-auto d-flex" width="800">
              <v-col :cols="5">
                <!-- 질문 렌더링 -->
                <v-sheet class="questArea">
                  <v-row v-if="score === 0">
                    <v-img
                      class="questPhone mx-auto"
                      src="../assets/exp/msgGame/quest1.png"
                    />
                  </v-row>
                  <v-row v-if="score === 1">
                    <v-img
                      class="questPhone mx-auto"
                      src="../assets/exp/msgGame/quest2.png"
                    />
                  </v-row>
                  <v-row v-if="score === 2">
                    <v-img
                      class="questPhone mx-auto"
                      src="../assets/exp/msgGame/quest3.png"
                    />
                  </v-row>
                </v-sheet>
              </v-col>
              <v-col :cols="7" class="d-flex justify-center align-center">
                <v-sheet class="answerArea">
                  <v-row>
                    <v-img
                      v-bind:style="{ marginBottom: '20px' }"
                      class="mx-auto"
                      src="../assets/exp/msgGame/answerAreaTitle.png"
                      max-width="300"
                      max-height="70"
                    />
                  </v-row>
                  <v-row class="goodAnswer justify-center">
                    <v-img
                      class="answerbtn"
                      v-if="score === 0"
                      @click="questReply(0)"
                      src="../assets/exp/msgGame/upper1.png"
                    ></v-img>
                    <v-img
                      class="answerbtn"
                      v-if="score === 1"
                      @click="questReply(1)"
                      src="../assets/exp/msgGame/upper2.png"
                    ></v-img>
                    <v-img
                      class="answerbtn"
                      v-if="score === 2"
                      @click="questReply(0)"
                      src="../assets/exp/msgGame/upper3.png"
                    ></v-img>
                  </v-row>
                  <v-row class="badAnswer justify-center">
                    <v-img
                      class="answerbtn"
                      v-if="score === 0"
                      @click="questReply(1)"
                      src="../assets/exp/msgGame/lower1.png"
                    ></v-img>
                    <v-img
                      class="answerbtn"
                      v-if="score === 1"
                      @click="questReply(0)"
                      src="../assets/exp/msgGame/lower2.png"
                    ></v-img>
                    <v-img
                      class="answerbtn"
                      v-if="score === 2"
                      @click="questReply(1)"
                      src="../assets/exp/msgGame/lower3.png"
                    ></v-img>
                  </v-row>
                </v-sheet>
              </v-col>
            </v-sheet>
          </v-row>

          <!-- 게임 : 부모님 -->
          <v-row
            class="playGame"
            v-if="isPlaying === true && isParentsGame === true"
          >
            <v-col :cols="7">
              <!-- 질문 렌더링 -->
              <v-sheet class="questArea"> </v-sheet>
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
          <v-row
            class="playGame"
            v-if="isPlaying === true && isElderlyGame === true"
          >
            <v-col :cols="7">
              <!-- 질문 렌더링 -->
              <v-sheet class="questArea"> </v-sheet>
            </v-col>
            <v-col :cols="5">
              <v-sheet class="answerArea">
                <v-row class="goodAnswer">
                  <!--
                    좋은 대답

                   -->
                  <v-btn @click="questReply(1)">good answer</v-btn>
                </v-row>
                <v-row class="badAnswer">
                  <!--
                    나쁜 대답
                    이거 누르면 바로 예방실패 뜸
                   -->
                  <v-btn @click="questReply(0)">bad answer</v-btn>
                </v-row>
              </v-sheet>
            </v-col>
          </v-row>

          <!-- 결과 : 예방 성공 -->
          <v-row
            class="justify-center v-card__text"
            id="goodEnding"
            v-if="isPlaying === false && isGoodEnding === true"
          >
            <v-sheet>
              <v-row>
                <v-img
                  src="../assets/exp/msgGame/good_ending.png"
                  max-width="500px"
                  max-height="500px"
                />
              </v-row>
              <v-row class="justify-center">
                <router-link to="/explobby">
                  <v-img
                    class="gamebtn"
                    src="../assets/exp/msgGame/tomain_btn.png"
                  />
                </router-link>
              </v-row>
            </v-sheet>
          </v-row>

          <!-- 결과 : 예방 실패 -->
          <v-row
            class="justify-center v-card__text"
            id="badEnding"
            v-if="isPlaying === false && isBadEnding === true"
          >
            <v-sheet>
              <v-row>
                <v-img
                  src="../assets/exp/msgGame/bad_ending.png"
                  max-width="500px"
                  max-height="500px"
                />
              </v-row>
              <v-row class="justify-center">
                <router-link to="/explobby">
                  <v-img
                    class="gamebtn"
                    src="../assets/exp/msgGame/tomain_btn.png"
                  />
                </router-link>
              </v-row>
            </v-sheet>
          </v-row>
        </v-sheet>
      </v-container>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import AppBar from "../views/AppBar.vue"; // 상단바 컴포넌트 import
import Footer from "../views/Footer.vue";
export default {
  components: {
    AppBar, // 상단바 컴포넌트 등록
    Footer,
  },
  data: function () {
    return {
      score: 0,
      isGameIntro: true, //게임 소개 시트
      isSelectRole: false, //게임 역할 고르는 시트

      isPlaying: false, //엔딩 고를때 필요한 것 이거랑 아래 종류 둘다 true여야지 문제 푸는 상태
      isWorkerGame: false, // 직장인 문제
      isParentsGame: false, // 부모님 문제
      isElderlyGame: false, // 노인 문제

      isGoodEnding: false, // 예방 성공
      isBadEnding: false, // 예방 실패
    };
  },
  //score 변수 변화 감시
  watch: {
    score(newVal, oldVal) {
      console.log("score 값 변화 발생 : " + oldVal + " -> " + newVal);
      if (newVal === 3) {
        this.isPlaying = false;
        this.isGoodEnding = true;
      }
    },
  },
  methods: {
    //역할 고르는 창 띄우기
    startGame() {
      this.isGameIntro = false;
      this.isSelectRole = true;
    },
    //역할에 맞는 문제 띄우기
    showQuest(role) {
      this.isSelectRole = false;
      this.isPlaying = true;
      if (role === "worker") this.isWorkerGame = true;
      else if (role === "parents") this.isParentsGame = true;
      else if (role === "elderly") this.isElderlyGame = true;
      this.score = 0;
    },
    //대답 골랏을 때 행해지는 것들
    questReply(num) {
      if (num === 0) {
        this.isPlaying = false;
        this.isBadEnding = true;
      } else {
        this.score += num;
      }
    },
  },
};
</script>

<style>
.gamebtn {
  max-width: 100px;
  max-height: 30px;
  margin-top: 20px;
  margin-bottom: 10px;
}
.questPhone {
  max-width: 300px;
  max-height: 800px;
}
.answerbtn {
  max-width: 200px;
  max-height: 200px;
  margin-bottom: 20px;
  padding: 5px;
}
.gamebtn:hover {
  transform: translateY(-5px);
}

.answerbtn:hover {
  transform: translateY(-5px);
}

.answerArea {
  border: #f5c86e solid 5px;
}
</style>

<!-- /expmessagegame -->

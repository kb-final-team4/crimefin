<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main>
      <v-container>
        <v-sheet class="gameMainSheet mx-auto">

          <v-row class="justify-center" v-bind:style="{marginBottom: '30px'}">
            <v-card>
              <v-img v-bind:style="{marginTop: '10px'}" src="../assets/exp/typeGame/title.png" max-width="400px" max-height="100px"/>
            </v-card>
          </v-row>
          <!-- 게임 소개 -->
          <div class="gameIntro mx-auto" v-bind:style="{marginTop: '-30px'}"  v-if="isGameIntro === true">
            <v-row class="justify-center" id="gameIntro" v-if="isGameIntro === true">
              <v-sheet>
                <v-row>
                  <v-img class="postit" src="../assets/exp/typeGame/intro.png" max-width="300px" max-height="320px"/>
                </v-row>
                <v-row class="justify-center">
                  <v-img class="mx-auto gamebtn" @click="startGame" src="../assets/exp/msgGame/start_btn.png" />
                </v-row>
              </v-sheet>
            </v-row>
          </div>

          <!-- 문제 나오고 답변 고르는 곳 -->
          <div class="questArea justify-center d-flex" v-if="this.currentQuestion !== 7 && isQuestArea === true">

            <v-col :cols="5" class="questPostArea">
            <v-row class="d-block">
              <v-row class="progressBar">
                <!-- 진행도 -->
                  <div id="bar" class="innerbar"></div>
              </v-row>
              <v-row class="justify-center">
                <!-- 질문 영역 -->
                <div v-if="this.currentQuestion===0"> <v-img class="postitQ" :src="getImgUrl(this.questions[this.currentQuestion])" max-height="350px" max-width="300px"/></div>
                <div v-if="this.currentQuestion===1"> <v-img class="postitQ" :src="getImgUrl(this.questions[this.currentQuestion])" max-height="350px" max-width="300px"/></div>
                <div v-if="this.currentQuestion===2"> <v-img class="postitQ" :src="getImgUrl(this.questions[this.currentQuestion])" max-height="350px" max-width="300px"/></div>
                <div v-if="this.currentQuestion===3"> <v-img class="postitQ" :src="getImgUrl(this.questions[this.currentQuestion])" max-height="350px" max-width="300px"/></div>
                <div v-if="this.currentQuestion===4"> <v-img class="postitQ" :src="getImgUrl(this.questions[this.currentQuestion])" max-height="350px" max-width="300px"/></div>
                <div v-if="this.currentQuestion===5"> <v-img class="postitQ" :src="getImgUrl(this.questions[this.currentQuestion])" max-height="350px" max-width="300px"/></div>
                <div v-if="this.currentQuestion===6"> <v-img class="postitQ" :src="getImgUrl(this.questions[this.currentQuestion])" max-height="350px" max-width="300px"/></div>
              </v-row>
            </v-row>
            </v-col>

            <v-col :cols="1"></v-col>

            <v-col :cols="5" class="answerBtnArea">
              <v-row class="justify-center">
                <v-img
                    v-bind:style="{ marginBottom: '20px', marginTop: '20px' }"
                    class="mx-auto"
                    src="../assets/exp/msgGame/answerAreaTitle.png"
                    max-width="300"
                    max-height="70"
                />
              </v-row>
              <v-row class="justify-center">
                <!-- 선택 1 좋은 선택들 -->
                <v-img class="gamebtn" :src="getImgUrl(this.questReplyUpper[this.currentQuestion])"  @click="questReply(2)" max-height="100px" max-width="200px"/>
              </v-row>
              <v-row class="justify-center">
                <!-- 선택 2 나쁜 선택들 -->
                <v-img class="gamebtn" v-bind:style="{marginBottom: '20px'}" :src="getImgUrl(this.questReplyLower[this.currentQuestion])"  @click="questReply(1)" max-height="100px" max-width="200px"/>
              </v-row>
            </v-col>

          </div>

          <!-- 결과 페이지 -->
          <v-row class="justify-center" id="typeResult" v-if="this.currentQuestion === 7">
            <v-sheet>
              <!-- 여기다 점수대별로 유형 만들기 -->
              <div v-if="this.score > 0 && this.score <= 9">
                <v-row >
                  <v-img class="postit" src="../assets/exp/typeGame/end1.png" max-height="330px" max-width="300px"/>
                </v-row>
              </div>
              <div v-if="this.score > 9 && this.score <= 12">
                <v-row >
                  <v-img class="postit" src="../assets/exp/typeGame/end2.png" max-height="330px" max-width="300px"/>
                </v-row>
              </div>
              <div v-if="this.score > 12 && this.score <= 14">
                <v-row >
                  <v-img class="postit" src="../assets/exp/typeGame/end3.png" max-height="330px" max-width="300px"/>
                </v-row>
              </div>
              <v-row class="justify-center">
                <router-link to="/explobby">
                  <v-img class="gamebtn" src="../assets/exp/msgGame/tomain_btn.png" />
                </router-link>
              </v-row>
            </v-sheet>
          </v-row>


        </v-sheet>
      </v-container>
    </v-main>
  <Footer/>
  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue';
import Footer from '../views/Footer.vue';
export default {
  components: {
    AppBar, // 상단바 컴포넌트 등록
    Footer,
  },
  data: function () {
    return {
      score : 0, // 유형 판별 위한 점수
      isGameIntro : true, //게임 소개 시트
      isQuestArea : false, //본게임
      //10번까지 질문 담음
      questions : [
        'q1',
        'q2',
        'q3',
        'q4',
        'q5',
        'q6',
        'q7',
      ],
      //위쪽 선택 버튼 내용 담음
      questReplyUpper : [
          'good1',
          'good2',
          'good3',
          'good4',
          'good5',
          'good6',
          'good7',
      ],
      //아래쪽 선택 버튼 내용 담음
      questReplyLower : [
        'bad1',
        'bad2',
        'bad3',
        'bad4',
        'bad5',
        'bad6',
        'bad7',
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
      console.log("현재 score "+this.score);
    },
    updateProgressbar() {
      let el = document.getElementById("bar");
      let width = (this.currentQuestion / 7) * 100 + "%"; //10문항이니까 0~9
      width = parseFloat(width).toFixed(2);
      if (width > 100) width = 100;

      console.log("updateProgressbar width : " + width);
      let widthStr = width + "%";
      el.style.width = widthStr;
    },
    //<img :src="getImgUrl(item[0])" max-height="40px" max-width="40px"/>
    //이미지 url 삽입용
    getImgUrl(link) {
      var images = require.context('../assets/exp/typeGame', false, /\.png$/)
      return images('./' + link + ".png")
    },
  }
};
</script>

<style >
.progressBar {
  max-width: 300px;
  width: 90%;
  margin: 10px auto;
  margin-top: 10px;
  height: 8px;

  border-radius: 3px;
  background: linear-gradient(#6fa6d66c, #7db1df54);
}

.innerbar {
  max-width: 300px;
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
.gamebtn{
  max-width: 200px;
  max-height: 100px;
  margin-top: 20px;
  margin-bottom: 10px;
}
.postit{
  margin-top: 30px;
  margin-bottom: 30px;
}
.postitQ{
  margin-top: 10px;
  margin-bottom: 30px;
}
.answerBtnArea{
  border: #f5c86e solid 5px;
}
</style>

<!-- /exptypegame -->
<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main v-bind:style="{background : '#DDE6ED'}">
      <v-container fluid>
        <v-col>
          <v-row v-bind:style="{ backgroundColor: '#9DB2BF', color : 'white'}">
            <div class="v-card__title text-h2"
                 v-bind:style="{color : '#27374D'}">
              Crime-Fin?
            </div>
            <div class="v-card__text text-left text-h6"
                 v-bind:style="{color : '#27374D'}">
              금융과 관련하여 발생하는 범죄(Financial Crime)를<br>
              Fin(Finish) 하자는 취지로 지어진 이름입니다.<br>
              저희는 자체적으로 구성한 피싱 정보 데이터베이스를 기반으로<br>
              피해자들에게 도움을 주고 예방을 겸하는 사이트입니다.<br>
            </div>
          </v-row>
          <v-row>
            <v-col>
            <div class="v-card__title justify-center subtitle text-h4"
                 v-bind:style="{color : '#27374D'}">
              <br></br>피싱, 생각보다 가까이 있습니다
            </div>

            <v-row class="separate">
              <div class="row justify-space-around"  >
                <v-card class="mx-auto white--text" width="300" height="300" color="#9DB2BF" tile>
                  <v-col>
                    <div class="text-center text-h5">
                      <br>피해건수
                    </div>
                    <div class="icon-container">
                      <v-img class="mx-auto" src="../assets/thief.png" alt="로고" height="80" width="80"/>
                    </div>
                    <div class="text-center text-h6">
                      <animated-number :value="17841" :duration="1500" />건
                    </div>
                    <div class="v-card__text">
                      &nbsp;&nbsp;2019년 2963건, 2020년 1만3224건, 2021년 1만 7841건으로 점차 늘어나고 있습니다.
                    </div>
                  </v-col>
                </v-card>
                <v-card class="mx-auto white--text" width="300" height="300" color="#9DB2BF" tile>
                  <v-col>
                    <div class="text-center text-h5">
                      <br>피해액
                    </div>
                    <div class="icon-container">
                      <v-img class="mx-auto" src="../assets/won.png" alt="로고" height="80" width="80"/>
                    </div>
                    <div class="text-center text-h6">
                      <animated-number :value="1265" :duration="1000"/>억원
                    </div>
                    <div class="v-card__text">
                      &nbsp;&nbsp;2020년 587억원, 2021년 1265억원으로 범죄 사례 뿐만 아니라 액수도 가파르게 증가했습니다.
                    </div>
                  </v-col>
                </v-card>
                <v-card class="mx-auto white--text" width="300" height="300" color="#9DB2BF" tile>
                  <v-col>
                    <div class="text-center text-h5">
                      <br>범죄 피해 증가율
                    </div>
                    <div class="icon-container">
                      <v-img class="mx-auto" src="../assets/ascending.png" alt="로고" height="80" width="80"/>
                    </div>
                    <div class="text-center text-h6">
                      <animated-number :value="215" :duration="500"/>%
                    </div>
                    <div class="v-card__text">
                      &nbsp;&nbsp;전체적인 증가율은 전년대비 215%로 두배 이상 증가했습니다.
                    </div>
                  </v-col>
                </v-card>
              </div>
            </v-row>
            </v-col>
          </v-row>
          <v-row  class="white--text separate" v-bind:style="{ backgroundColor: '#9DB2BF' }">
            <v-col>
              <div class="v-card__title justify-center subtitle text-h4"
              v-bind:style="{color : '#27374D'}">
                주요 기능
              </div>
              <v-row no-gutters>
                <div class="row justify-space-around" >
                  <v-card v-bind:style="{background :'#526D82'}" class="mx-auto" width="300" height="300" tile>
                    <v-col>
                      <div class="text-center v-card__title white--text justify-center">
                        1. 스팸 번호 판단
                      </div>
                      <div class="v-card__text white--text">
                        &nbsp;&nbsp;저희가 자체적으로 생성한 피싱 번호 데이터베이스를 이용하여 사용자는 번호를 입력하기만 하면 그 번호가 피싱과 연관된 스팸 번호인지 바로 알 수 있습니다.
                      </div>
                    </v-col>
                  </v-card>
                  <v-card v-bind:style="{background : '#526D82'}" class="mx-auto white--text" width="300" height="300" tile>
                    <v-col>
                      <div class="text-center v-card__title justify-center">
                        2. 피싱 링크 판단
                      </div>
                      <div class="v-card__text">
                        &nbsp;&nbsp;VirusTotal API를 활용해서 당신의 문자나 카카오톡으로 온 수상한 링크가 스팸인지 안전한지 대신 판단해드립니다.
                      </div>
                    </v-col>
                  </v-card>
                  <v-card v-bind:style="{background : '#526D82'}" class="mx-auto white--text" width="300" height="300" tile>
                    <v-col>
                      <div class="text-center v-card__title justify-center">
                        3.AI 분석
                      </div>
                      <div class="v-card__text">
                        &nbsp;&nbsp;스팸 번호 판단과 피싱 링크 판단으로도 판별이 되지 않으면, 현재 고객님의 상황을 서술하여 주시면 저희가 인공지능을 활용한 자연어 처리 및 분석으로 해당 내용이 피싱과 연관되었는지 분석해드립니다.
                      </div>
                    </v-col>
                  </v-card>
                  <v-card v-bind:style="{background : '#526D82'}" class="mx-auto white--text" width="300" height="300" tile>
                    <v-col>
                      <div class="text-center v-card__title justify-center">
                        4. 각종 애프터 케어
                      </div>
                      <div class="v-card__text">
                        &nbsp;&nbsp;상황 분석 이후 고객님께 매뉴얼 제공하고 맞춤형 체크리스트를 생성합니다. 또한 개인 주소를 기반으로 은행과 경찰서를 찾아주는 스마트 지도 서비스, 피싱 예방을 위해 간단한 체험관도 제공해드리고 있습니다.
                      </div>
                    </v-col>
                  </v-card>
                </div>
              </v-row>
            </v-col>
          </v-row>
        </v-col>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue'; // 상단바 컴포넌트 import
import AOS from 'aos';
import AnimatedNumber from 'animated-number-vue';
export default {
  components: {
    AppBar, // 상단바 컴포넌트 등록
    AOS,
    AnimatedNumber,
  },
  mounted() {
    AOS.init();
  },
  methods:{

  }
};
</script>

<style >
.icon-container{
  margin-top: 10px;
  margin-bottom: 10px;
}
.subtitle{
  margin-top: 10px;
  margin-bottom: 30px;
}
.separate{
  padding-top: 50px;
  padding-bottom: 80px;
}
#btm-container{

}
</style>
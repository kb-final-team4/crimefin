<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main v-bind:style="{ background: 'F1F6F9' }">
      <v-container fluid>
        <v-col>
          <v-row
            v-bind:style="{ backgroundColor: 'F1F6F9', color: 'white' }"
            align="center"
            justify="center"
          >
            
            <!--서비스 소개-->
            <v-col cols="30" sm="10" class="mb-5">
              <v-row no-gutters>
                <v-col cols="30" sm="24">
                  <div class="v-card__text text-left text-h4" v-bind:style="{ color: '#27374D' }">
                    <br />
                    <b>금융 범죄로부터 당신을 보호하세요.</b><br />
                  </div>
                  <div class="v-card__text text-left text-h7" v-bind:style="{ color: '#27374D' }">
                    <br />
                    Crime-Fin은 피싱 의심 번호, 링크, 상황을 확인하여 보이스 피싱, 스미싱,
                    메신저피싱인지 판단합니다.<br />
                    <br />
                    우리는 피싱 여부를 판단하고, 문자 알림 기능, 피싱 체험관 기능을 통해<br />
                    금융 범죄 예방 및 대응을 위한 종합 서비스를 제공합니다.
                    <br />
                  </div>
                  <div class="v-card__text text-left text-h5" v-bind:style="{ color: '#27374D' }">
                    <b>피싱, 생각보다 가까이 있습니다.</b><br />
                    <b>누구나, 언제나 피해자가 될 수 있습니다.</b><br />
                  </div>
                  
                </v-col>

                 <!-- 구분선 및 간격 -->
                <v-col cols="0">
                  <!--<div style="border-left:1px solid grey; height:100%;"></div>  -->
                </v-col>
                
                <!--피싱 통계-->
                <v-col cols="30" sm="4"  class="mt-5">
                  

                  <carousel :perPage="1" :mouseDrag="true">
                    <!-- Card 1 -->
                    <slide>
                      <v-card
                        class="mx-auto black--text"
                        width="300"
                        height="300"
                        color="#9BA4B4"
                        tile
                      >
                        <v-col>
                          <div class="text-center text-h5"><br />피해건수</div>
                          <div class="icon-container">
                            <v-img
                              class="mx-auto"
                              src="../assets/thief.png"
                              alt="로고"
                              height="80"
                              width="80"
                            />
                          </div>
                          <div class="text-center text-h6">
                            <animated-number :value="17841" :duration="1500" />건
                          </div>
                          <div class="v-card__text">
                            &nbsp;&nbsp;2019년 2963건, 2020년 1만3224건, 2021년 1만 7841건으로 점차
                            늘어나고 있습니다.
                          </div>
                        </v-col>
                      </v-card>
                    </slide>

                    <!-- Card 2 -->
                    <slide>
                      <v-card
                        class="mx-auto black--text"
                        width="300"
                        height="300"
                        color="#9BA4B4"
                        tile
                      >
                        <v-col>
                          <div class="text-center text-h5"><br />피해액</div>
                          <div class="icon-container">
                            <v-img
                              class="mx-auto"
                              src="../assets/won.png"
                              alt="로고"
                              height="80"
                              width="80"
                            />
                          </div>
                          <div class="text-center text-h6">
                            <animated-number :value="1265" :duration="1000" />억원
                          </div>
                          <div class="v-card__text">
                            &nbsp;&nbsp;2020년 587억원, 2021년 1265억원으로 범죄 사례 뿐만 아니라
                            액수도 가파르게 증가했습니다.
                          </div>
                        </v-col>
                      </v-card>
                    </slide>

                    <!-- Card 3 -->
                    <slide>
                      <v-card
                        class="mx-auto black--text"
                        width="300"
                        height="300"
                        color="#9BA4B4"
                        tile
                      >
                        <v-col>
                          <div class="text-center text-h5"><br />범죄 피해 증가율</div>
                          <div class="icon-container">
                            <v-img
                              class="mx-auto"
                              src="../assets/ascending.png"
                              alt="로고"
                              height="80"
                              width="80"
                            />
                          </div>
                          <div class="text-center text-h6">
                            <animated-number :value="215" :duration="500" />%
                          </div>
                          <div class="v-card__text">
                            &nbsp;&nbsp;전체적인 증가율은 전년대비 215%로 두배 이상 증가했습니다.
                          </div>
                        </v-col>
                      </v-card>
                    </slide>
                  </carousel>
                  <!-- <v-img
                    :src="require('@/assets/service_info.png')"
                    style="max-width: 300px; max-height: 300px"
                  ></v-img>-->
                </v-col>
              </v-row>
            </v-col>
          </v-row>
            </v-col>
          </v-row>
        </v-container>

        <!--우리가 하는 일-->
        <v-container fluid style="background-color: #F0F0F0;"> 
          <v-row class="black--text separate" v-bind:style="{ backgroundColor: '#F0F0F0' }">
            <v-col>
              <div
                class="v-card__title justify-center subtitle text-h4"
                v-bind:style="{ color: '#27374D' }"
              >
                <b>우리가 하는 일</b>
              </div>
              <div
                class="v-card__title justify-center subtitle text-h7"
                v-bind:style="{ color: '#27374D' }"
              >
                금융 범죄 예방 및 대응 통합 서비스인 Crime-Fin은 귀하가 금전적 피해를 보기 전에 차단합니다.</br>
                우리는 예상치 못하게 자산을 잃지 않기 위한 첫 번째 단계입니다.</br>
              </div>
        
        <!--구체적 내용-->
        <v-container fluid style="background-color: #F0F0F0;">
        <v-row class="black--text separate" v-bind:style="{ backgroundColor: '#F0F0F0' }">
          <v-col cols="6">
            <div class="v-card__text text-left text-h6" v-bind:style="{ color: '#27374D', paddingLeft: '80px' }">
              <br />
              <b>판단하다</b> </br>
              피싱이 의심되는 번호, 링크, 상황까지 분석합니다. </br>
              공신력 있는 번호, 링크 조회 서비스를 활용하고 </br>
              AI로 귀하의 상황까지 고려하여 </br>
              피싱 여부를 단계적으로 판단합니다.
            </div>
            <div class="v-card__text text-left text-h6" v-bind:style="{ color: '#27374D', paddingLeft: '80px' }">
              <br />
              <b>알리다</b> </br>
              의심스러운 거래는 실시간 문자로 귀하에게 전송됩니다. </br>
              귀하가 등록한 주요 계좌의 거래는 Crime-Fin 시스템에 의해 모니터링되고 있기 때문입니다. </br>
            </div>
            <div class="v-card__text text-left text-h6" v-bind:style="{ color: '#27374D', paddingLeft: '80px' }">
              <br />
              <b>대응하다</b> </br>
              피싱에 대응하고 싶으신가요? </br>
              텍스트 대응 방안, 유사 사례 검색, 가까운 은행·경찰서 위치 조회로 </br>
              빠르게 피싱에 대응할 수 있습니다.</br>
            </div>
            <div class="v-card__text text-left text-h6" v-bind:style="{ color: '#27374D', paddingLeft: '80px' }">
              <br />
              <b>예방하다</b> </br>
              피싱을 당하지 않았지만 혹시 모를 상황에 대비하고 싶으신가요? </br>
              우리의 피싱 체험관 서비스는 미리 준비하는 꼼꼼한 귀하에게 큰 도움이 될 것입니다. </br>
            </div>
          </v-col>
          <v-col cols="6">
            <div class="text-center">
              <img src="/path/to/image.jpg" alt="이미지" width="200" height="200" />
            </div>
          </v-col>
        </v-row> 
        </v-container> 
            </v-col>
          </v-row> 
        </v-col>
      </v-container>
    </v-main>
    <Footer/>
  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue'; // 상단바 컴포넌트 import
import Footer from '../views/Footer.vue'; // 푸터 컴포넌트 import
import AOS from 'aos';
import AnimatedNumber from 'animated-number-vue';
import { Carousel, Slide } from 'vue-carousel';

export default {
  components: {
    AppBar, // 상단바 컴포넌트 등록
    Footer,
    AOS,
    AnimatedNumber,
    Carousel,
    Slide,
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
</style>
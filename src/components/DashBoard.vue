<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main>
      <v-container>
        <v-row>
          <v-col cols="5" class="item-box" v-bind:style="{background : '#2c3e50'}">
            <v-sheet height="700px">
              <v-col>
                <v-row>
                  <v-col :cols="2"></v-col>
                  <v-col :cols="4">
                  <div class="v-card__title text-center mx-auto">
                    개인 계좌 종합
                  </div>
                  </v-col>
                  <v-col :cols="4" class="mx-auto">
                    <br>
                    <v-btn @click="getTotalAccountList" color="primary" block outlined>조회하기</v-btn>
                  </v-col>
                  <v-col :cols="2"></v-col>
                </v-row>
                <v-row>
                  <div class="chart-wrap mx-auto">
                    <div id="chart">
                      <apexcharts type="donut" width="380" height="400" :options="accountChartOptions" :series="accountSeries"></apexcharts>
                    </div>
                  </div>
                </v-row>
                <br><v-divider></v-divider><br>
                <v-row>
                  <!-- todo v-card 안에서 조회받았을때 값들 표시하기 어떻게? v-for 찾아보기 -->
                  <v-card width="400px" class="mx-auto account-list">
                    <div class="v-card__text"
                         v-if="accountInfoList != null"
                         v-for="item in accountInfoList">
                      {{ item }}
                    </div>
                  </v-card>
                </v-row>
              </v-col>
            </v-sheet>
          </v-col>

          <v-col cols="7" class="item-box" v-bind:style="{background : '#2c3e50'}">
            <v-sheet height="700px">
              <v-col>
                <v-row>
                  <v-col :cols="4">
                  <div class="v-card__title mx-auto">
                    거래 내역
                  </div>
                  </v-col>
                  <v-col :cols="7">
                  <v-select
                      v-model="accountNumDropdown"
                      v-if = "this.accountNumArr != null"
                      label="계좌 선택"
                      :items="this.accountNumArr"
                  > <!-- items에 accountNumArr 넣기 -->
                  </v-select>
                  </v-col>
                  <v-col class="mx-auto" :cols="1">
                    <router-link to="/알림상세페이지"><v-img src="../assets/bell.png" width="50px" height="50px"></v-img></router-link>
                  </v-col>
                </v-row>
                <v-row>
                  <div class="chart-wrap mx-auto">
                    <div id="chart">
                      <apexchart type="line" width="1000" height="350" :options="bankingChartOptions" :series="bankingSeries"></apexchart>
                    </div>
                  </div>
                </v-row>
                <v-row>
                  <v-col :cols="1">

                  </v-col>
                  <v-col :cols="3">
                    <v-sheet class="mx-auto" height="100px">

                        <v-form @submit.prevent="getBankingDaily">
                          <v-row class="d-flex">
                          <v-text-field v-model="bankingStartDate" label="시작일자"></v-text-field>
                          <v-text-field v-model="bankingEndDate" label="종료일자"></v-text-field>
                          <v-btn type="submit" color="primary" block outlined>조회하기</v-btn>
                          </v-row>
                        </v-form>
                    </v-sheet>
                  </v-col>
                  <v-col :cols="7">
                  <!-- todo v-card 안에서 조회받았을때 값들 표시하기 어떻게? v-for 찾아보기 -->
                  <v-card class="banking-list">
                    <div class="v-card__text"
                         v-if="bankingInfoList != null"
                         v-for="item in bankingInfoList">
                      {{ item }}
                    </div>
                  </v-card>
                  </v-col>
                  <v-col :cols="1">

                  </v-col>
                </v-row>
              </v-col>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>

  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue'; // 상단바 컴포넌트 import
import VueApexCharts from "vue-apexcharts";
import axios from "axios";
export default {
  components: {
    AppBar, // 상단바 컴포넌트 등록
    apexcharts: VueApexCharts, //차트 라이브러리
  },
  data: function() {
    return {
      memberId : this.$session.get('loginMemberId'),

      accountInfoList : null, //계좌 정보 리턴받아서 스트링 합쳐서 만든 프론트 표현용 스트링 배열
      balancePercentageArr : null, //계좌별 잔고 리턴받은거 다 합친 값중에 100%로 했을때 각자 얼마인지 계산한 int 배열
      //계좌 정보 리스트 받았을때 계좌 번호 들어가는 배열
      accountNumArr : null,
      bankNameArr : null, //계좌 정보 리스트 받았을 때 은행 명 들어가는 배열
      balanceArr : null, //계좌 정보 리스트 받았을 때 계좌 잔고 들어가는 배열

      accountNumDropdown: null,// 드랍박스에서 계좌 고르고
      bankingStartDate : null, // 추적시작날짜 입력하고
      bankingEndDate: null, // 추적종료날짜 입력하기

      bankingInfoList : null, //거래 내역 리턴받아서 스트링 합쳐서 만든 프론트 표현용 스트링 배열
      bankingDateInfoList : null, //거래 내역 리턴받은거 별 거래일자


      //왼쪽 차트 데이터들
      //todo 왼쪽 도넛 차트랑 왼쪽 아래 조회 메서드 만들기
      //getTotalAccountList
      //세션에 바인딩되있는 memberId 보내고
      //은행, 계좌번호, 잔고 리턴받기
      //잔고 총합 구하고 계좌번호별로 분류해서 은행이랑 같이 아래 리스트에 표시하기
      //accountSeries에는 계좌번호별 퍼센티지 저장
      //accountChartOptions의 labels에는 계좌번호 저장
      accountSeries: [44,55,13,33], //this.balancePercentageArr, //balancePercentageArr
      accountChartOptions: {
        chart: {
          width: 380,
          type: 'donut',
        },
        labels: ['111111-11-111111', '222222-22-222222', '333333-33-333333', '444444-44-444444'],//this.accountNumArr, //accountNumArr
        dataLabels: {
          enabled: true
        },
        responsive: [{
          breakpoint: 480,
          options: {
            chart: {
              width: 200
            },
            legend: {
              show: false
            }
          }
        }],
        legend: {
          position: 'right',
          offsetY: 0,
          height: 230,
        }
      },
      //오른쪽 차트 데이터들
      //todo 오른쪽 차트랑 오른쪽 아래 조회 메서드 만들기
      //getBankingListDaily
      //세션에 바인딩된 memberId 보내고 시작시각 종료시각 입력한거 보내기
      //거래시각 별로 bankingSeries에 잔고 저장하고 xaxis의 categories에 배열로 거래시각 저장하기
      bankingSeries: [{
        name: "잔고",
        data: [100000, 41000, 35000, 51000, 49000, 62000, 69000, 91000, 148000]
      }],
      bankingChartOptions: {
        chart: {
          height: 350,
          type: 'line',
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: 'straight'
        },
        grid: {
          row: {
            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
            opacity: 0.5
          },
        },
        xaxis: {
          categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
        }
      },

    }
  },
  created() {
    t
  },
  mounted() {

  },
  methods: {
    getBankingDaily(){
      var url = "http://localhost:9999/asset/dashboard/time/"+this.accountNumDropdown;
      var data ={
        memberId : this.memberId,
        accountNum : this.accountNumDropdown,
        startdate : this.bankingStartDate,
        enddate : this.bankingEndDate
      };

      axios.get(url)
          .then(response => {
            /*
            입금자명이랑 출금자명을 어떻게 연관지어야할지 도저히 모르겠음
             백엔드단에서 무조건 날짜 순으로 정렬해서 주기

              response.data 가서 입금 출금 구별해서 적고
              response.data가서 날짜들 꺼내와서 bankingDateInfoList에 저장,
              //일자 , 입금인지 출금인지, 액수, 잔고
              let depositMap = new Map();
              let withdrawlMap = new Map();

              if(deposit이면)
                depositMap.add('날짜', 'deposit amount')
               else (withdrawl이면)
                withdrawlMap = new Map();

              foreach(item in bankingDateInfo)
                만약 키(item)가 depositMap에 키로 존재하면
                bankingInfoList.add(item + " 입금 " + 'deposit amount');
                만약 키(item)가 withdrawlMap에 키로 존재하면
                bankingInfoList.add(item + " 출금 " + 'withdrawl amount');

             */
          })
          .catch(error => {
            console.error(error);
          });
    },
    getTotalAccountList(){
      var url = "http://localhost:9999/asset/dashboard/"+this.memberId;
      var data = {
        memberId: this.memberId,
      };

      axios.get(url)
          .then(response => {
            /*
            response.data 가서 accountNum 들 꺼내와서 accountNumArr에 삽입하기
            response.data 가서 bankName 들 꺼내와서 bankNameArr에 삽입
            response.data 가서 balance 들 꺼내와서 balanceArr에 삽입

            let total = 0;
            foreach(item in balanceArr) total += item;
            foreach(item in balanceArr) this.balancePercentageArr.add( item / total );

            for(int i = 0; i< accountNum.length; i++){
              this.accountInfoList.add(bankNameArr[i] + " " + accountNumArr[i] + " " + balance[i]);
            }
             */
          })
          .catch(error => {
            console.error(error);
          });
    }
  },
};
</script>

<style >
.banking-list {
  height: calc(100vh - 87vh);
  overflow-y: auto;
}
.account-list {
  height: calc(100vh - 76vh);
  overflow-y: auto;
}
</style>
  
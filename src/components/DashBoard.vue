<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />

    <v-main>
      <v-container>
        <v-row>
          <v-col cols="5" class="item-box" v-bind:style="{background : '#2c3e50'}">
            <v-sheet height="600px">
              <v-col>
                <v-row>

                  <div class="v-card__title text-center mx-auto text--h6">
                    개인 계좌 종합
                  </div>


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
                    <div class="v-card__text text-center"
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
            <v-sheet height="600px">
              <v-col>
                <v-row>
                  <v-col :cols="1"></v-col>
                  <v-col :cols="4">
                  <div class="v-card__title text-center mx-auto text--h6">
                    거래 내역
                  </div>
                  </v-col>
                  <v-col :cols="6">
                  <v-select
                      v-model="accountNumDropdown"
                      v-if = "this.accountNumArr != null"
                      label="계좌 선택"
                      :items="this.accountNumArr"
                  > <!-- items에 accountNumArr 넣기 -->
                  </v-select>
                  </v-col>
                  <v-col class="mx-auto" :cols="1">
                    <router-link to="/알림상세페이지">
                      <v-img src="../assets/bell.png" width="30px" height="30px">
                      </v-img>
                    </router-link>
                  </v-col>
                </v-row>
                <v-row>
                  <!-- todo 동적 apexchart 태그 렌더링? -->
                  <div class="chart-wrap mx-auto">
                    <div id="chart">
                      <apexchart type="line" width="580" height="350" :options="bankingChartOptions" :series="bankingSeries"></apexchart>
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
                          <v-text-field type="datetime-local" height="10px" v-model="bankingStartDate" label="시작일자"></v-text-field>
                          <v-text-field type="datetime-local" height="10px" v-model="bankingEndDate" label="종료일자"></v-text-field>
                          <v-btn type="submit" color="primary" block outlined>조회하기</v-btn>
                          </v-row>
                        </v-form>
                    </v-sheet>
                  </v-col>
                  <v-col :cols="7">
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

      //왼쪽 차트 데이터들
      //왼쪽 도넛 차트랑 왼쪽 아래 조회 메서드 만들기 -> 했음
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
        labels: ['123', '456', '789', '000'],//this.accountNumArr, //accountNumArr
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

      accountNumDropdown: null,// 드랍박스에서 계좌 고르고
      bankingStartDate : null, // 추적시작날짜 입력하고
      bankingEndDate: null, // 추적종료날짜 입력하기


      bankingInfoList : null, //리턴받아서 스트링 합쳐서 만든 프론트 표현용 스트링 배열

      bankingDateList : null, //리턴받은 거래일자, bankingChartOptions.xaxis.categories에 넣기
      bankingBalanceList : null, //리턴받은 잔고, bankingSeries.data에 넣기
      bankingDepositList : null, // 리턴받은 예금액
      bankingDepositNameList : null, // 리턴받은 입금자 명 리스트
      bankingWithdrawlList : null, //리턴받은 출금액
      bankingWithdrawlToList : null, //리턴받은 출금 계좌 명 리스트

      //오른쪽 차트 데이터들
      //todo 오른쪽 차트 업데이트 메서드 만들기
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
    this.getTotalAccountList();
    //this.getBankingDaily();
  },
  mounted() {

  },
  methods: {
    getBankingDaily(){
      var url = "http://localhost:9999/asset/dashboard/time";
      var data ={
        memberId : this.memberId,
        accountNum : this.accountNumDropdown,
        startdate : this.bankingStartDate,
        enddate : this.bankingEndDate
      };

      console.log("getBankingDaily accountNumDropdown "+data.accountNum);
      console.log("getBankingDaily startdate"+ data.startdate); //controller에서는 substring해서 쓰면 될듯
      console.log("getBankingDaily enddate"+data.enddate);

      //todo 테스트 데이타 백에서 넘어오는거 보고 수정하기
      this.bankingDateList = ["2023-09-10","2023-09-11","2023-09-12","2023-09-13","2023-09-14", "2023-09-15"];
      this.bankingDepositList = ["20000", null, "40000", null, "20000", null];
      this.bankingDepositNameList = ["cys", null, "cys2", null, "cys3", null];
      this.bankingWithdrawlList = [null,"10000", null, "20000", null, "1000000"];
      this.bankingWithdrawlToList = [null,"ocl", null, "ocl2", null, "나쁜놈"];

      this.bankingChartOptions.xaxis.categories = this.bankingDateList;
      console.log("getBankingDaily bankingChartOptions.xaxis.categories : " + this.bankingChartOptions.xaxis.categories);

      this.bankingBalanceList = ['1020000', '1010000', '1050000', '1030000', '1050000', '50000'];
      for(let j = 0; j < this.bankingBalanceList.length; j++){
        this.bankingBalanceList[j] = parseInt(this.bankingBalanceList[j]);
      }
      this.bankingSeries.data = this.bankingBalanceList;
      console.log("getBankingDaily bankingSeries.data "+ this.bankingSeries.data);

      this.bankingInfoList = new Array(this.bankingDateList.length);
      for(let i = 0; i<this.bankingDateList.length; i++){
        //입금
        if(this.bankingDepositList[i] != null){
          this.bankingInfoList[i] = this.bankingDateList[i]+" 입금 "+this.bankingDepositNameList[i]+" +"+this.bankingDepositList[i]+"원 잔고 : "+this.bankingBalanceList[i];
        }
        //출금
        else{
          this.bankingInfoList[i] = this.bankingDateList[i]+" 출금 "+this.bankingWithdrawlToList[i]+" -"+this.bankingWithdrawlList[i]+"원 잔고 : "+this.bankingBalanceList[i];
        }
      }

      axios.post(url, data)
          .then(response => {
            /*
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
    //왼쪽 도넛 그래프(모든 계좌 총액에서 각자 계좌가 차지하는 비율 나타내는 그래프)랑
    // 아래 로그인한 사용자의 계좌별 정보(은행, 계좌번호, 잔고)
    getTotalAccountList(){
      var url = "http://localhost:9999/asset/dashboard?memberId="+this.memberId;
      /*var data = {
        memberId: this.memberId,
      };*/

      //todo 테스트 데이타 백에서 넘어오는거 보고 수정하기
      this.accountNumArr = ['111111-11-111111', '222222-22-2222222', '333333-33-333333', '444444-44-444444'];
      this.accountChartOptions.labels = this.accountNumArr;
      this.bankNameArr = ['국민은행', '국민은행', '신한은행', '하나은행'];
      this.balanceArr = ['500000', '300000', '100000', '100000'];


      let total = 0;
      for(let i = 0; i< this.balanceArr.length; i++)
        total += parseInt(this.balanceArr[i]);
      //console.log("getTotalAccountList balanceArr "+this.balanceArr);
      //console.log("getTotalAccountList total "+ total);

      this.balancePercentageArr = new Array(this.accountNumArr.length);
      for(let j = 0; j< this.accountNumArr.length; j++)
        this.balancePercentageArr[j] = parseFloat(parseInt(this.balanceArr[j]) / total) * 100;

      //console.log("getTotalAccountList balancePercentageArr "+this.balancePercentageArr);
      this.accountSeries = this.balancePercentageArr;

      //console.log("getTotalAccountList balanceArr after"+this.balanceArr);

      this.accountInfoList = new Array(this.accountNumArr.length);
      for(let k = 0; k< this.accountNumArr.length; k++){
        this.accountInfoList[k] = this.bankNameArr[k] + " " + this.accountNumArr[k] + " " + this.balanceArr[k]+"원";
      }
      this.accountChartOptions.labels = this.accountNumArr;

      /*axios.get(url)
          .then(response => {
            /!*
            response.data 가서 accountNum 들 꺼내와서 accountNumArr에 삽입하기
            response.data 가서 bankName 들 꺼내와서 bankNameArr에 삽입
            response.data 가서 balance 들 꺼내와서 balanceArr에 삽입
            *!/
            this.accountNumArr = ['111111-11-111111', '222222-22-2222222', '333333-33-333333', '444444-44-444444'];
            this.bankNameArr = ['국민은행', '국민은행', '신한은행', '하나은행'];
            this.balanceArr = [500000, 300000, 100000, 100000];

            let total = 0;
            for(let i = 0; i< this.balanceArr.length; i++)
              total += this.balanceArr[i];
            for(let i = 0; i< this.balanceArr.length; i++)
              this.balancePercentageArr.add( this.balanceArr[i] / total );

            for(let i = 0; i< this.accountNumArr.length; i++) throw DOMException{
              this.accountInfoList.add(this.bankNameArr[i] + " " + this.accountNumArr[i] + " " + this.balanceArr[i]);
            }

          })
          .catch(error => {
            console.error(error);
          });*/
    }
  },
};
</script>

<style >
.banking-list {
  height: calc(100vh - 85vh);
  overflow-y: auto;
}
.account-list {
  height: calc(100vh - 60vh);
  overflow-y: auto;
}
</style>
  
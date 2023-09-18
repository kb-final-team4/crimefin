<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />
    <v-main v-bind:style="{background : '#444766'}">
      <v-container>
        <v-row>
          <v-col cols="5" class="item-box" v-bind:style="{background : '#2c3e50'}">
            <v-sheet height="570px" v-bind:style="{background : '#90dbf5'}">
              <v-col>
                <v-row>
                  <div class="v-card__title text-center mx-auto text--h6">
                    개인 계좌 종합
                  </div>
                </v-row>
                <v-row>
                  <div class="chart-wrap mx-auto">
                    <div id="chart1">
                      <apexcharts id="myChart1" type="donut" width="380" height="400" :options="accountChartOptions" :series="accountSeries"></apexcharts>
                    </div>
                  </div>
                </v-row>
                <br><v-divider></v-divider><br>
                <v-row>
                  <v-card width="370px" class="mx-auto account-list" v-bind:style="{background : '#7ab9cf'}">
<!--                    <div class="v-card__text text-center"
                         v-if="accountInfoList != null"
                         v-for="item in accountInfoList">
                      {{ item }}
                    </div>-->
                  <div class="v-card__text text-center white--text"
                       v-if="accountInfosList != null"
                       v-for="item in accountInfosList"
                       v-bind:style="{height : '60px'}"
                  >
                  <v-row>
                  <v-col :cols="3">
                    <img :src="getImgUrl(item[0])" v-bind:alt="item[0]" height="40px" width="40px"/>
                  </v-col>
                  <v-col :cols="8">
                    <v-row v-bind:style="{paddingTop : '10px'}">
                      {{item[1]}}<br>
                    </v-row>
                    <v-row>
                      {{item[2]}}
                    </v-row>
                  </v-col>
                  </v-row>
                  </div>
                  </v-card>
                </v-row>
              </v-col>
            </v-sheet>
          </v-col>
          <v-col cols="7" class="item-box" v-bind:style="{background : '#2c3e50'}">
            <v-sheet height="570px" v-bind:style="{background : '#90dbf5'}">
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
                    <v-img src="../assets/bell.png" width="30px" height="30px" @click="openNoticelist">
                    </v-img>
                  </v-col>
                </v-row>
                <v-row>
                  <!-- todo 동적 apexchart 태그 re-rendering -->
                  <div class="chart-wrap mx-auto">
                    <div id="chart2">
                      <apexchart id = "bankingchart1" type="line" width="580" height="300" :options="bankingChartOptions" :series="bankingSeries"></apexchart>
                    </div>
                  </div>
                </v-row>
                <v-row>
                  <v-col :cols="1">
                  </v-col>
                  <v-col :cols="3">
                    <v-sheet class="mx-auto" height="100px" v-bind:style="{background : '#90dbf5'}">
                        <v-form @submit.prevent="getBankingDaily">
                          <v-row class="d-flex">
                            <v-col :cols="1"></v-col>
                            <v-col :cols="10">
                              <v-row>
                                <v-text-field type="datetime-local" height="10px" v-model="bankingStartDate" label="시작일자"></v-text-field>
                              </v-row>
                              <v-row>
                                <v-text-field type="datetime-local" height="10px" v-model="bankingEndDate" label="종료일자"></v-text-field>
                              </v-row>
                              <v-row>
                                <v-btn type="submit" color="white" v-bind:style="{background : '#444766'}" block outlined>조회하기</v-btn>
                              </v-row>
                            </v-col>
                            <v-col :cols="1"></v-col>
                          </v-row>
                        </v-form>
                    </v-sheet>
                  </v-col>
                  <v-col :cols="7">
                  <v-card class="banking-list" v-bind:style="{background : '#7ab9cf'}">
                    <div class="v-card__text white--text"
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

        <div class="outer-bg" v-if="this.modal !== false" >
          <div class="modal-bg">
            <h4 class="v-card__title text-center white--text">나의 알림 리스트</h4>
            <v-card>
              <div class="v-card__text white--text"
                   v-bind:style="{background : '#353257'}"
                   v-if="noticeInfoList != null"
                   v-for="notice in noticeInfoList">
                {{ notice }}
              </div>
            </v-card>
            <br>
            <v-btn v-bind:style="{background : '#332b57'}" variant="tonal" type="button" @click="modal=false" class="white--text btn-close">닫기</v-btn>
          </div>
        </div>

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
      modal : false, //알림 모달창 열고닫을때 씀
      noticeInfoList : null, //알림 표현용 리스트

      noticeTimeArr : null, //알림 시간 리스트
      noticeBankNameArr : null, //알림 은행명 리스트
      noticeDepositArr : null, //알림 입금 리스트
      noticeWithdrawlArr : null, //알림 출금 리스트

      memberId : this.$session.get('loginMemberId'),


      accountInfosList : null, // [bankImgArr[i], balanceArr[i], (bankNameArr[i]+accountNumArr[i])]
      accountInfoList : null, //계좌 정보 리턴받아서 (은행이름) 계좌번호 표시하는것
      balancePercentageArr : null, //계좌별 잔고 리턴받은거 다 합친 값중에 100%로 했을때 각자 얼마인지 계산한 int 배열
      //계좌 정보 리스트 받았을때 계좌 번호 들어가는 배열
      accountNumArr : null,
      bankNameArr : null, //계좌 정보 리스트 받았을 때 은행 명 들어가는 배열
      balanceArr : null, //계좌 정보 리스트 받았을 때 계좌 잔고 들어가는 배열
      bankImgArr : null, //왼쪽에 계좌목록에 표기할 은행 아이콘 이름 리스트 가지고 있는 배열

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
      //getBankingListDaily
      //세션에 바인딩된 memberId 보내고 시작시각 종료시각 입력한거 보내기
      //거래시각 별로 bankingSeries에 잔고 저장하고 xaxis의 categories에 배열로 거래시각 저장하기
      bankingSeries: [{
        name: '테스트',
        //data: [100000, 41000, 35000, 51000, 49000, 62000, 69000, 91000, 148000]
        data : ['1020000', '1010000', '1050000', '1030000', '1050000', '50000']
      }],
      bankingChartOptions: {
        chart: {
          height: 300,
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
          //categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
          categories: ["2023-09-10","2023-09-11","2023-09-12","2023-09-13","2023-09-14", "2023-09-15"],
        }
      },

    }
  },
  created() {
    this.getTotalAccountList();
    this.getBankingDaily();
  },
  mounted() {

  },
  methods: {
    /*
    NOTICE_ID   NOT NULL VARCHAR2(20)
    MEMBER_ID   NOT NULL VARCHAR2(20)
    TIME                 TIMESTAMP(6)
    ACCOUNT_NUM          VARCHAR2(20)
    BANK_NAME            VARCHAR2(20)
    DEPOSIT              NUMBER
    WITHDRAWL            NUMBER
     */
    openNoticelist(){
      this.modal = true;
      /*
      noticeTimeArr : null, //알림 시간 리스트
      noticeBankNameArr : null, //알림 은행명 리스트
      noticeDepositArr : null, //알림 입금 리스트
      noticeWithdrawlArr : null, //알림 출금 리스트
       */
      var url = "http://localhost:9999/asset/dashboard/notice";
      var data = {
        memberId : this.memberId,
        accountNum : this.accountNumDropdown
      }

      this.noticeTimeArr = ['2023-09-10','2023-09-13','2023-09-15'];
      this.noticeBankNameArr = ['국민은행', '국민은행', '국민은행'];
      this.noticeDepositArr = [null, null, 10000000];
      this.noticeWithdrawlArr = [9000000,100000000, null];

      this.noticeInfoList = new Array(this.noticeTimeArr.length);
      for(let i = 0; i< this.noticeTimeArr.length; i++)
      {
        if(this.noticeDepositArr[i]!==null)
          this.noticeInfoList[i] = this.noticeTimeArr[i]+" "+this.noticeBankNameArr[i]+" "+this.noticeDepositArr[i]+"원이 입금되었습니다.";
        else
          this.noticeInfoList[i] = this.noticeTimeArr[i]+" "+this.noticeBankNameArr[i]+" "+this.noticeWithdrawlArr[i]+"원이 빠져나갔습니다.";
      }

      /*axios.post(url,data)
          .then(response => {

          })
          .catch(error => {
            console.log(error);
          });*/
    },

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

      //테스트 데이타 백에서 넘어오는거 보고 수정하기
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
      //console.log("getBankingDaily bankingSeries.data before"+ this.bankingSeries.data);
      this.bankingSeries.data = this.bankingBalanceList;
      console.log("getBankingDaily bankingSeries.data after"+ this.bankingSeries.data);

      this.bankingInfoList = new Array(this.bankingDateList.length);
      for(let i = 0; i<this.bankingDateList.length; i++){
        //오른쪽차트 아래에 표기하는 곳
        //입금
        if(this.bankingDepositList[i] != null){
          this.bankingInfoList[i] = this.bankingDateList[i]+" 입금 "+this.bankingDepositNameList[i]+" +"+this.bankingDepositList[i]+"원 잔고 : "+this.bankingBalanceList[i];
        }
        //출금
        else{
          this.bankingInfoList[i] = this.bankingDateList[i]+" 출금 "+this.bankingWithdrawlToList[i]+" -"+this.bankingWithdrawlList[i]+"원 잔고 : "+this.bankingBalanceList[i];
        }
      }

      //차트 리렌더링 -- 안됨
      /*console.log("ApexCharts exec start");
      console.log("ApexCharts exec this.bankingBalanceList " +this.bankingBalanceList );
      console.log("ApexCharts exec this.bankingDateList "+this.bankingDateList);*/
      //console.log("ApexCharts re-rendering : rightChart "+document.getElementById("bankingchart1"));
      /*var rightChart = new ApexCharts(document.getElementById("bankingchart1"), this.bankingChartOptions);
      //rightChart.destroy();
      rightChart.updateSeries({
        data : this.bankingBalanceList
      });
      rightChart.updateOptions({
        xaxis: {
          categories: this.bankingDateList
        }
      });
      rightChart.render();*/
      /*ApexCharts.exec("bankingchart1", "updateOptions", {
        chartOptions: {
          xaxis: {
            categories: this.bankingDateList
          }
        }
      });
      ApexCharts.exec("bankingchart1", "updateSeries", {
        series : {
          name : "balance",
          data :  this.bankingBalanceList
        }
      });*/

      //대시보드 백 완성되면 여기 풀기
      /*axios.post(url, data)
          .then(response => {

          })
          .catch(error => {
            console.error(error);
          });*/
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

      console.log("getTotalAccountList this.bankNameArr "+this.bankNameArr);

      this.accountInfoList = new Array(this.accountNumArr.length);
      this.accountInfosList = new Array(this.accountNumArr.length);
      this.bankImgArr = new Array(this.accountNumArr.length);
      let k;
      for(k = 0; k< this.accountNumArr.length; k++){
        this.accountInfoList[k] = "("+this.bankNameArr[k]+") "+ this.accountNumArr[k];

        if(this.bankNameArr[k]==="하나은행")
          this.bankImgArr[k] = "bank_hana";
        else if(this.bankNameArr[k] === "카카오뱅크")
          this.bankImgArr[k] = "bank_kakao";
        else if(this.bankNameArr[k] === "국민은행")
          this.bankImgArr[k] = "bank_kb";
        else if(this.bankNameArr[k] === "기업은행")
          this.bankImgArr[k] = "bank_kiub";
        else if(this.bankNameArr[k] === "농협은행")
          this.bankImgArr[k] = "bank_nonghyup";
        else if(this.bankNameArr[k] === "새마을은행")
          this.bankImgArr[k] = "bank_saemaeul";
        else if(this.bankNameArr[k] === "신한은행")
          this.bankImgArr[k] = "bank_shinhan";
        else if(this.bankNameArr[k] === "토스뱅크")
          this.bankImgArr[k] = "bank_toss";
        else if(this.bankNameArr[k] === "우리은행")
          this.bankImgArr[k] = "bank_woori";
        else
          this.bankImgArr[k] ="won";

        this.accountInfosList[k] = [this.bankImgArr[k], this.balanceArr[k]+"원", this.accountInfoList[k]];
      }
      this.accountChartOptions.labels = this.accountNumArr;

      console.log("getTotalAccountList accountInfosList " + this.accountInfosList);

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
    },
    // 모달 창을 닫는 메서드
    closeSuccessModal() {
      this.showSuccessModal = false;
    },
    //이미지 url 삽입용
    getImgUrl(bank) {
      var images = require.context('../assets/', false, /\.png$/)
      return images('./' + bank + ".png")
    },
  },
};
</script>

<style >
.account-list {
  height: calc(100vh - 64vh);
  overflow-y: auto;
}
.banking-list {
  height: calc(100vh - 85vh);
  overflow-y: auto;
}
.outer-bg {
  width: 200%;
  height:200%;
  background: #444766;
  position: fixed;
  padding: 20px;
}
.modal-bg {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  background: #525157;
  border-radius: 8px;
  width: 500px;

  padding: 30px;
}
</style>
  
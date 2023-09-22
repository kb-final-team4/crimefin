<template>
  <v-app>
    <!-- 상단바 컴포넌트 import -->
    <AppBar />
    <v-main v-bind:style="{background : '#102C57'}">
      <v-container>
        <v-row>
          <v-col cols="3" class="item-box" v-bind:style="{background : '#DAC0A3'}">

              <v-col>
                <v-row>
                  <!-- 계좌 정보 그래프 카드 -->
                  <v-card width="340px" height="240px" class="mx-auto account-list noscroll-content" v-bind:style="{background : '#F8F0E5', color : 'gray'}">
                    <v-row>
                      <div class="v-card__title text-center mx-auto text--h6">
                        개인 계좌 종합
                      </div>
                    </v-row>
                    <v-row>
                      <div class="chart-wrap mx-auto">
                        <div id="chart1">
                          <apexcharts id="myChart1" type="donut" width="300" height="300" :options="accountChartOptions" :series="accountSeries"></apexcharts>
                        </div>
                      </div>
                    </v-row>
                  </v-card>

                </v-row>
                <v-row>

                  <!-- 계좌정보 text 카드 -->
                  <v-card width="340px" class="mx-auto account-list noscroll-content" v-bind:style="{background : '#F8F0E5', color : 'gray'}">
                  <div class="v-card__text text-center"
                       v-if="accountInfosList != null"
                       v-for="item in accountInfosList"
                       v-bind:style="{height : '60px'}"
                  >
                  <v-row>
                  <v-col :cols="3">
                    <img :src="getImgUrl(item[0])" v-bind:alt="item[0]" height="40px" width="40px"/>
                  </v-col>
                  <v-col :cols="8">
                    <v-row v-bind:style="{paddingTop : '10px', fontSize: '16px'}">
                      {{item[1]}}<br>
                    </v-row>
                    <v-row v-bind:style="{fontSize: '12px'}">
                      {{item[2]}}
                    </v-row>
                  </v-col>
                  </v-row>
                  </div>
                  </v-card>

                </v-row>
              </v-col>

          </v-col>
          <v-col cols="9" class="item-box" v-bind:style="{background : '#DAC0A3'}">
              <v-col>

                <v-card>
                <v-row>
                  <!-- 조건입력 오른쪽 위 card -->
                  <v-col :cols="3">
                  <div class="v-card__title d-flex align-center justify-center text--h6">
                    거래 내역
                  </div>
                  </v-col>
                  <v-col :cols="6">
                    <v-row class="justify-center">
                    <v-select
                        v-bind:style="{maxWidth: '370px'}"
                        v-model="accountNumDropdown"
                        v-if = "this.accountNumArr != null"
                        label="계좌 선택"
                        :items="this.accountNumArr"
                    >
                    </v-select>
                    </v-row>
                    <v-row>
                      <v-col :cols="5" v-bind:style="{marginTop: '-20px', marginLeft: '-10px'}">
                        시작일자
                        <input v-bind:style="{maxWidth:'100px'}" type="date" height="10px" v-model="bankingStartDate" label="시작일자" />
                      </v-col>
                      <v-col :cols="5" v-bind:style="{marginTop: '-20px', marginLeft: '-40px'}">
                        종료일자
                        <input v-bind:style="{maxWidth:'100px'}" type="date" height="10px" v-model="bankingEndDate" label="종료일자" />
                      </v-col>
                      <v-col :cols="2">
                        <v-btn type="submit" color="white" v-bind:style="{background : '#444766', marginTop:'-14px'}" block outlined>조회</v-btn>
                      </v-col>
                    </v-row>
                  </v-col>
                  <v-col class="d-flex align-center justify-center" :cols="3">
                    <v-img src="../assets/bell.png" max-width="30px"  max-height="30px" @click="openNoticelist" />
                    <v-img src="../assets/add.png" max-width="30px" max-height="30px" @click="openaddlist" v-bind:style="{marginLeft: '10px'}"/>
                    <v-img src="../assets/delete.png" max-width="30px" max-height="30px" @click="opendeletelist" v-bind:style="{marginLeft: '10px'}"/>
                  </v-col>

                </v-row>
                <v-row>
                  <!-- todo 동적 apexchart 태그 re-rendering -->
                  <div class="chart-wrap mx-auto" v-bind:style="{marginTop: '-20px'}">
                    <div id="chart2">
                      <apexchart id = "bankingchart1" type="line" width="660" height="300" :options="bankingChartOptions" :series="bankingSeries"></apexchart>
                    </div>
                  </div>
                </v-row>
                </v-card>


                <v-row>
                  <v-col :cols="4">
                  </v-col>
                  <v-col :cols="7" v-bind:style="{marginLeft: '20px'}">
                  <v-card class="banking-list noscroll-content" v-bind:style="{background : '#F8F0E5', color : 'gray'}">
                    <div class="v-card__text"
                         v-if="bankingInfoList != null"
                         v-for="item in bankingInfoList">
                      {{ item }}
                    </div>
                  </v-card>
                  </v-col>
                </v-row>
              </v-col>

          </v-col>
        </v-row>

        <div class="outer-bg" v-if="this.modal !== false" >
          <div class="modal-bg">
            <h4 class="v-card__title text-center" v-bind:style="{color : 'gray'}">나의 알림 리스트</h4>
            <v-card>
              <div class="v-card__text"
                   v-bind:style="{background : '#F8F0E5', color : 'gray'}"
                   v-if="noticeInfoList != null"
                   v-for="notice in noticeInfoList">
                {{ notice }}
              </div>
            </v-card>
            <br>
            <v-btn v-bind:style="{background : '#EADBC8' , color : 'gray'}" variant="tonal" type="button" @click="modal=false" class="btn-close">닫기</v-btn>
          </div>
        </div>

        <div class="outer-bg2" v-if="this.modal2 !== false" >
          <div class="modal-bg2">
            <h4 class="v-card__title text-center" v-bind:style="{color : 'gray'}">계좌 등록</h4>
            <v-card>
                  <v-row>
                    <v-col cols="8">
                      <v-text-field v-model="accountId" label="아이디" variant="solo" class="t-field"></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="8">
                      <v-text-field v-model="bankName" label="은행명" variant="solo" class="t-field"></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="8">
                      <v-text-field v-model="accountNum" label="계좌번호" variant="solo" class="t-field"></v-text-field>
                    </v-col>
                    <v-col cols="1" >
                      <v-btn variant="tonal" class="button" @click="authenticateAccount">인증</v-btn>
                    </v-col>
                  </v-row>
                  
                  <v-row>
                    <!-- <div v-if="isAuthenticated"> -->
                    <v-col cols="8">
                      <v-text-field v-model="authNumConfirm" label="입금자명" variant="solo" class="t-field"></v-text-field>
                    </v-col>
                    <v-col cols="1">
                      <v-btn class="button" @click="confirmAuthNum">확인</v-btn>
                    </v-col>
                    <!-- </div> -->
                  </v-row>
                  <v-row>
                    <v-col cols="8">
                      <v-text-field v-model="accountNickname" label="계좌별명" variant="solo" class="t-field"></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="8">
                      <v-text-field v-model="accountLimit" label="limit" variant="solo" class="t-field"></v-text-field>
                    </v-col>
                  </v-row>
                </v-card>
            <br>
            <v-row class="d-flex justify-end">
            <v-btn v-bind:style="{background : '#EADBC8' , color : 'gray'}" variant="tonal" type="button" @click="registAccount" class="btn-close" style="margin-right:30px;">등록</v-btn>
            <v-btn v-bind:style="{background : '#EADBC8' , color : 'gray'}" variant="tonal" type="button" @click="modal2=false" class="btn-close">닫기</v-btn>
          </v-row>
          </div>
        </div>

        <div class="outer-bg3" v-if="this.modal3 !== false" >
          <div class="modal-bg3">
            <h4 class="v-card__title text-center" v-bind:style="{color : 'gray'}">계좌 삭제</h4>
            <v-card>
                  <v-row>
                    <v-col cols="10">
                      <v-text-field v-model="memberId" label="아이디" variant="solo" class="t-field3"></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="10">
                      <v-text-field v-model="bankName" label="은행명" variant="solo" class="t-field3"></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="10">
                      <v-text-field v-model="accountNum" label="계좌번호" variant="solo" class="t-field3"></v-text-field>
                    </v-col>
                  </v-row>
                  
                </v-card>
            <br>
            <v-row class="d-flex justify-end">
            <v-btn v-bind:style="{background : '#EADBC8' , color : 'gray'}" variant="tonal" type="button" @click="deleteAccount" class="btn-close" style="margin-right:30px;">삭제</v-btn>
            <v-btn v-bind:style="{background : '#EADBC8' , color : 'gray'}" variant="tonal" type="button" @click="modal3=false" class="btn-close">닫기</v-btn>
          </v-row>
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
      numKorStack: null, //스택 담는 용
      numKor: null, // output 용

      accountId: "", // 아이디 입력 값
      bankName: "", // 은행명 입력 값
      accountNum: "", // 계좌번호 입력 값
      authNumConfirm:"", //입금자 인증번호 확인
      isAuthenticated: false, // 인증 여부
      accountNickname: "", // 계좌 별명 입력 값
      accountLimit: "", // Limit 입력 값


      modal : false, //알림 모달창 열고닫을때 씀
      modal2: false,
      modal3: false,
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

      //여기는 수정하지말기
      accountSeries: [44,55,13,33], //this.balancePercentageArr, //balancePercentageArr
      accountChartOptions: {
        chart: {
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
              show: false,
            }
          }
        }],
        legend: {
          show: true,
          position: 'right',
          offsetY: 0,
          height: 200,
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

      //todo 발표 시나리오 맞춰서 데이터 수정하기
      //오른쪽 차트 데이터들
      //getBankingListDaily
      //여기 수정하기
      bankingSeries: [{
        name: '잔고',
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
        //여기 수정하기 x축 값
        xaxis: {
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
  methods:{
    /*
    NOTICE_ID   NOT NULL VARCHAR2(20)
    MEMBER_ID   NOT NULL VARCHAR2(20)
    TIME                 TIMESTAMP(6)
    ACCOUNT_NUM          VARCHAR2(20)
    BANK_NAME            VARCHAR2(20)
    DEPOSIT              NUMBER
    WITHDRAWL            NUMBER
     */
    authenticateAccount() {
    console.log(11);
    try {
      // 여기에서 백엔드와 통신하여 입금자명 인증을 수행합니다.
      const response = axios.post('http://localhost:9999/asset/auth', {
        bankName: this.bankName,
        accountNum: this.accountNum,
      });

      console.log(response);
      // 인증이 성공하면 isAuthenticated 값을 true로 설정합니다.
      if (response.status===200) {
        //this.isAuthenticated = true;
        alert("계좌 정보가 성공적으로 인증되었습니다.");
      } else {
        // 인증 실패 시 메시지를 표시하거나 다른 처리를 수행할 수 있습니다.
        alert("계좌 정보를 확인하세요. 인증에 실패했습니다.");
      }
    } catch (error) {
      console.error("계좌 인증 중 오류 발생:", error);
      // 오류 처리를 원하는 대로 수행할 수 있습니다.
    }
  },

  confirmAuthNum() {
    console.log(22);
    axios.get('http://localhost:9999/asset/auth/confirm',{
      params:{
        authNumConfirm:this.authNumConfirm,
      }
    })
    .then((response)=>{
      if(response.status ===200){
        alert('입금자명 확인 성공');
        this.isAuthenticated = true;
      }
      else{
        alert('입금자명이 틀립니다!');
      }
    })
    .catch((error)=>{
      console.error('입금자명 찾기 오류', error);
    });
  },

  registAccount(){
    try{
      const response = axios.post('http://localhost:9999/asset/regist', {
        accountNickname: this.accountNickname,
        accountLimit: this.accountLimit,
        isAuthenticated:this.isAuthenticated,
        accountNum:this.accountNum,
      });
      console.log(response);
      if(response.status===200){
        alert('계좌 생성 완료');
      }
      else{
        alert('계좌 생성 오류 발생');
      }
    }catch(error){
      console.error('입금자 명이 일치하지 않습니다.!!', error);
      alert('계좌 등록 중 오류 발생');
    }
  },

  deleteAccount(){
    console.log(99);
    try{
      const response = axios.delete('http://localhost:9999/asset',{
        data:{ //delte요청은 data로 묶어서 보내야함
          memberId:this.memberId,
          bankName:this.bankName,
          accountNum:this.accountNum,
        },
      });
      console.log(response);

    }catch(error){
      console.error('계좌 삭제 중 오류 발생', error);
      alert('계좌 삭제 중 오류 발생');
    }
  },

    openaddlist(){
      this.modal2=true;
    },
    
    opendeletelist(){
      this.modal3=true;
    },
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

       //todo 발표 시나리오 맞춰서 데이터 수정하기
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
      /*var url = "http://localhost:9999/asset/dashboard/time";
      var data ={
        memberId : this.memberId,
        accountNum : this.accountNumDropdown,
        startdate : this.bankingStartDate,
        enddate : this.bankingEndDate
      };*/

      //console.log("getBankingDaily accountNumDropdown "+data.accountNum);
      //console.log("getBankingDaily startdate"+ data.startdate); //controller에서는 substring해서 쓰면 될듯
      //console.log("getBankingDaily enddate"+data.enddate);

      //테스트 데이타 백에서 넘어오는거 보고 수정하기
      //todo 발표 시나리오 맞춰서 데이터 수정하기
      this.bankingDateList = ["2023-09-10","2023-09-11","2023-09-12","2023-09-13","2023-09-14", "2023-09-15"];
      this.bankingDepositList = ["20000", null, "40000", null, "20000", null];
      this.bankingDepositNameList = ["cys", null, "cys2", null, "cys3", null];
      this.bankingWithdrawlList = [null,"10000", null, "20000", null, "100000"];
      this.bankingWithdrawlToList = [null,"ocl", null, "ocl2", null, "나쁜놈"];
      this.bankingBalanceList = ['120000', '110000', '150000','130000','150000','50000'];

      this.bankingChartOptions.xaxis.categories = this.bankingDateList;
      console.log("getBankingDaily bankingChartOptions.xaxis.categories : " + this.bankingChartOptions.xaxis.categories);

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

      /*var initurl = "http://localhost:9999/asset/dashboard/init";

      axios.get(initurl)
          .then(response => {
            this.bankingDateList = new Array(response.data.length);
            this.bankingDepositList = new Array(response.data.length);
            this.bankingDepositNameList = new Array(response.data.length);
            this.bankingWithdrawlList = new Array(response.data.length);
            this.bankingWithdrawlToList = new Array(response.data.length);
            this.bankingBalanceList = new Array(response.data.length);

            for(let i = 0; i<response.data.length; i++){
              this.bankingDateList[i] = response.data[i].bankingDate;
              this.bankingDepositList[i] = response.data[i].deposit;
              this.bankingDepositNameList[i] = response.data[i].depositName;
              this.bankingWithdrawlList[i] = response.data[i].withdrawal;
              this.bankingWithdrawlToList[i] = response.data[i].withdrawalTo;
              this.bankingBalanceList[i] = response.data[i].balance;
            }

            this.bankingChartOptions.xaxis.categories = this.bankingDateList;
            console.log("getBankingDaily bankingChartOptions.xaxis.categories : " + this.bankingChartOptions.xaxis.categories);

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
          })
          .catch(error => {

          });*/
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
    parseNumstrToKorean(numStr){
      class Stack {
        constructor() {
          this.storage = {};
          this.top = 0;
        }
        size() {
          return Object.keys(this.storage).length;
        }
        push(element) {
          this.storage[this.top] = element;
          this.top += 1;
        }
        pop() {
          // 빈 스택에 에러처리
          if (Object.keys(this.storage).length === 0) {
            return;
          }

          const result = this.storage[this.top-1];
          delete this.storage[this.top-1];
          this.top -= 1;

          return result;
        }
      }
      var splitedStr = numStr.split('');
      for(let i = 0; i<splitedStr.length; i++){
        splitedStr[i] = parseInt(splitedStr[i]);
      }
      this.numKorStack = new Stack();
      for(let j = splitedStr.length-1; j >= 0; j--){
        this.numKorStack.push(splitedStr[j])
      }
      var strrst = null;
      while(this.numKorStack.top !== 0){
        var rst = this.numKorStack.pop();

        if(rst === 1)
          strrst = "일"
        else if(rst === 2)
          strrst = "이"
        else if(rst === 3)
          strrst = "삼"
        else if(rst === 4)
          strrst = "사"
        else if(rst === 5)
          strrst = "오"
        else if(rst === 6)
          strrst = "육"
        else if(rst === 7)
          strrst = "칠"
        else if(rst === 8)
          strrst = "팔"
        else if(rst === 9)
          strrst = "구"

        if(rst !== 0) {
          if (this.numKorStack.size() % 4 === 3)
            strrst += "천";
          else if (this.numKorStack.size() % 4 === 2)
            strrst += "백";
          else if (this.numKorStack.size() % 4 === 1)
            strrst += "십";
        }
          if (this.numKorStack.size() / 4 === 1)
            strrst += "만";
          else if (this.numKorStack.size() / 4 === 2)
            strrst += "억";
          else if (this.numKorStack.size() / 4 === 3)
            strrst += "조";
      }

      return strrst;
    },
    //왼쪽 도넛 그래프(모든 계좌 총액에서 각자 계좌가 차지하는 비율 나타내는 그래프)랑
    // 아래 로그인한 사용자의 계좌별 정보(은행, 계좌번호, 잔고)
    getTotalAccountList(){
      var url = "http://localhost:9999/asset/dashboard?memberId="+this.memberId;
      /*var data = {
        memberId: this.memberId,
      };*/

      //todo 발표 시나리오 맞춰서 데이터 수정하기
      var accountNickArr = ['계좌 1', '계좌 2', '계좌 3','계좌 4'];
      this.accountNumArr = ['111111-11-111111', '222222-22-2222222', '333333-33-333333', '444444-44-444444'];
      this.accountChartOptions.labels = accountNickArr;
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

      this.numKor = new Array(this.accountNumArr.length);

      for(let z = 0; z<this.accountNumArr.length; z++){
        this.numKor[z] = this.parseNumstrToKorean(this.balanceArr[z]);
      }

      let k=0;
      for(; k< this.accountNumArr.length; k++){
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

        this.accountInfosList[k] = [this.bankImgArr[k], this.numKor[k]+"원", this.accountInfoList[k]];
      }
      //this.accountChartOptions.labels = this.accountNumArr;

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
  height: calc(100vh - 62vh);
  overflow-y: auto;
}
.banking-list {
  height: calc(100vh - 80vh);
  overflow-y: auto;
}
.outer-bg {
  width: 200%;
  height:200%;
  background: #102C57;
  position: fixed;
  padding: 20px;
}

.outer-bg2 {
  width: 200%;
  height:350%;
  background: #102C57;
  position: fixed;
  padding: 40px;
  margin-bottom: 30px;
}

.outer-bg3 {
  width: 200%;
  height:350%;
  background: #102C57;
  position: fixed;
  padding: 40px;
  margin-bottom: 30px;
}

.modal-bg{
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  background: #DAC0A3;
  border-radius: 8px;
  width: 500px;
  height: 400px;

  /* padding: 30px; */
}

.modal-bg2{
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  background: #DAC0A3;
  border-radius: 8px;
  width: 790px;

  padding-bottom: 30px;
  padding-left: 50px;
}

.modal-bg3{
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  background: #DAC0A3;
  border-radius: 8px;
  width: 790px;

  padding-bottom: 30px;
}


.t-field{
  width:550px;
  background-color: white;
  margin-top:0%;
  padding-left:40px;
}

.t-field3{
  width:550px;
  background-color: white;
  margin-top:0%;
  padding-left:100px;
}

.button{
  position:absolute;
  margin-top:5px;
}
.noscroll-content::-webkit-scrollbar{
  display: none;
}
</style>
  
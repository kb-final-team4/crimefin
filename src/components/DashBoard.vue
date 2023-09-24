<template>
  <v-app>
    <AppBar />

    <v-main>
      <v-container>
        <v-row>
          <v-col :cols="1"></v-col>
          <v-col :cols="7"></v-col>
          <v-col class="d-flex align-center justify-end" :cols="3">
            <v-img
              v-bind:style="{ marginLeft: '10px' }"
              src="../assets/bell.png"
              max-width="30px"
              max-height="30px"
              @click="openNoticelist"
            />
          </v-col>
          <v-col :cols="1"></v-col>
        </v-row>

        <v-row>
          <v-col :cols="1"></v-col>
          <v-col cols="3" class="item-box justify-center">
            <!-- 대시보드 1 :: 도넛 그래프 -->
            <v-card height="200px" width="300px">
              <div
                class="text-center"
                v-bind:style="{
                  fontSize: '25px',
                  marginTop: '-13px',
                  padding: '10px',
                }"
              >
                개인 계좌 종합
              </div>
              <div class="chart-wrap mx-auto">
                <div id="chart1">
                  <apexcharts
                    ref="donutChart"
                    id="myChart1"
                    type="donut"
                    width="280px"
                    height="200"
                    :options="accountChartOptions"
                    :series="accountSeries"
                  ></apexcharts>
                </div>
              </div>
            </v-card>
            <br />
            <v-divider></v-divider>
            <br />
            <!-- 대시보드 2 :: 전체 계좌 리스트 -->
            <v-card width="300px" height="420px">
              <v-card
                width="240px"
                class="mx-auto account-list noscroll-content"
                v-bind:style="{
                  background: '#9ba4b4',
                  color: 'gray',
                  paddingTop: '10px',
                }"
              >
                <div
                  class="v-card__text text-center white--text"
                  v-if="accountInfosList != null"
                  v-for="item in accountInfosList"
                  v-bind:style="{ height: '60px' }"
                >
                  <v-row class="justify-end">
                    <v-col :cols="3">
                      <img
                        :src="getImgUrl(item[0])"
                        v-bind:alt="item[0]"
                        height="40px"
                        width="40px"
                      />
                    </v-col>
                    <v-col :cols="8">
                      <v-row
                        v-bind:style="{ paddingTop: '10px', fontSize: '16px' }"
                      >
                        {{ item[1] }}<br />
                      </v-row>
                      <v-row v-bind:style="{ fontSize: '12px' }">
                        {{ item[2] }}
                      </v-row>
                    </v-col>
                  </v-row>
                </div>
              </v-card>
            </v-card>
          </v-col>

          <v-col cols="7" class="item-box">
            <!-- 대시보드 3 :: 계좌 거래내역 그래프 -->
            <v-card height="350px">
              <v-row>
                <v-col :cols="4">
                  <div class="v-card__title justify-center text--h6">
                    거래 내역
                  </div>
                </v-col>
                <v-col :cols="2"></v-col>
                <v-col :cols="5">
                  <v-select
                    v-model="accountNumDropdown"
                    v-if="this.accountNumArr != null"
                    label="계좌 선택"
                    :items="this.accountNumArr"
                  />
                </v-col>
                <v-col :cols="1"></v-col>
              </v-row>

              <v-row>
                <div
                  class="chart-wrap mx-auto"
                  v-bind:style="{ marginTop: '-10px' }"
                >
                  <div id="chart2">
                    <apexchart
                      ref="bankingChart"
                      id="bankingchart1"
                      type="line"
                      width="500"
                      height="250"
                      :options="bankingChartOptions"
                      :series="bankingSeries"
                    ></apexchart>
                  </div>
                </div>
              </v-row>
            </v-card>

            <br /><v-divider></v-divider><br />

            <!-- 대시보드 4 :: 계좌 거래내역 리스트 -->
            <v-card height="270px">
              <v-col>
                <v-form @submit.prevent="getBankingDaily">
                  <v-row>
                    <v-col :cols="4">
                      <v-text-field
                        type="datetime-local"
                        height="10px"
                        v-model="bankingStartDate"
                        label="시작일자"
                      ></v-text-field>
                    </v-col>
                    <v-col :cols="4">
                      <v-text-field
                        type="datetime-local"
                        height="10px"
                        v-model="bankingEndDate"
                        label="종료일자"
                      ></v-text-field>
                    </v-col>
                    <v-col :cols="4">
                      <v-btn
                        type="submit"
                        color="white"
                        v-bind:style="{ background: '#444766' }"
                        block
                        outlined
                        >조회하기</v-btn
                      >
                    </v-col>
                  </v-row>
                </v-form>
              </v-col>
              <v-col>
                <v-card
                  class="banking-list noscroll-content"
                  v-bind:style="{ background: '#9ba4b4', marginTop: '-20px' }"
                >
                  <v-row>
                    <v-col :cols="3"></v-col>
                    <v-col
                      :cols="1"
                      class="text-right"
                      v-bind:style="{ fontSize: '14px' }"
                    >
                      날짜
                    </v-col>
                    <v-col
                      :cols="2"
                      class="text-right"
                      v-bind:style="{ fontSize: '14px' }"
                    >
                      입/출금자
                    </v-col>
                    <v-col
                      :cols="2"
                      class="text-left"
                      v-bind:style="{ fontSize: '14px' }"
                    >
                      입/출금액
                    </v-col>
                    <v-col :cols="1" class="text-left"> 잔고 </v-col>
                    <v-col :cols="4"></v-col>
                  </v-row>
                  <v-row v-bind:style="{ marginTop: '-30px' }">
                    <div
                      v-bind:style="{ fontSize: '23px', marginTop: '-10px' }"
                      class="v-card__text white--text"
                      v-if="bankingInfoList != null"
                      v-for="item in bankingInfoList"
                    >
                      <!--                      <v-col :cols="2">
                        {{ item[0] }}
                      </v-col>
                      <v-col :cols="2">
                        {{ item[1] }}
                      </v-col>
                      <v-col :cols="2">
                        {{ item[2] }}
                      </v-col>
                      <v-col :cols="2">
                        {{ item[3] }}
                      </v-col>-->
                      {{ item }}
                    </div>
                  </v-row>
                </v-card>
              </v-col>
            </v-card>
          </v-col>
          <v-col :cols="1"></v-col>
        </v-row>

        <!-- 알림 리스트 -->
        <div class="outer-bg" v-if="this.modal !== false">
          <div class="modal-bg">
            <h4
              class="v-card__title text-center"
              v-bind:style="{ color: 'gray' }"
            >
              나의 알림 리스트
            </h4>
            <v-card>
              <div
                class="v-card__text"
                v-bind:style="{ background: '#F8F0E5', color: 'gray' }"
                v-if="noticeInfoList != null"
                v-for="notice in noticeInfoList"
              >
                {{ notice }}
              </div>
            </v-card>
            <br />
            <v-btn
              v-bind:style="{ background: '#EADBC8', color: 'gray' }"
              variant="tonal"
              type="button"
              @click="modal = false"
              class="btn-close"
              >닫기</v-btn
            >
          </div>
        </div>
      </v-container>
    </v-main>

    <!-- Footer -->
    <Footer />
  </v-app>
</template>

<script>
import AppBar from "../views/AppBar.vue";
import Footer from "../views/Footer.vue";
import VueApexCharts from "vue-apexcharts";
import axios from "axios";
export default {
  components: {
    AppBar,
    Footer,
    apexcharts: VueApexCharts, //차트 라이브러리
  },
  data: function () {
    return {
      numKorStack: null, //스택 담는 용
      numKor: null, // output 용

      accountId: "", // 아이디 입력 값
      bankName: "", // 은행명 입력 값
      accountNum: "", // 계좌번호 입력 값
      authNumConfirm: "", //입금자 인증번호 확인
      isAuthenticated: false, // 인증 여부
      accountNickname: "", // 계좌 별명 입력 값
      accountLimit: "", // Limit 입력 값

      modal: false, //알림 모달창 열고닫을때 씀
      modal2: false,
      modal3: false,
      noticeInfoList: null, //알림 표현용 리스트

      noticeTimeArr: null, //알림 시간 리스트
      noticeBankNameArr: null, //알림 은행명 리스트
      noticeDepositArr: null, //알림 입금 리스트
      noticeWithdrawlArr: null, //알림 출금 리스트

      memberId: this.$session.get("loginMemberId"),

      accountInfosList: null, // [bankImgArr[i], balanceArr[i], (bankNameArr[i]+accountNumArr[i])]
      accountInfoList: null, //계좌 정보 리턴받아서 (은행이름) 계좌번호 표시하는것
      balancePercentageArr: null, //계좌별 잔고 리턴받은거 다 합친 값중에 100%로 했을때 각자 얼마인지 계산한 int 배열
      //계좌 정보 리스트 받았을때 계좌 번호 들어가는 배열
      accountNumArr: null,
      bankNameArr: null, //계좌 정보 리스트 받았을 때 은행 명 들어가는 배열
      balanceArr: null, //계좌 정보 리스트 받았을 때 계좌 잔고 들어가는 배열
      nickNameArr: null, //계좌 정보 리스트 받았을 때 닉네임 들어가는 배열
      bankImgArr: null, //왼쪽에 계좌목록에 표기할 은행 아이콘 이름 리스트 가지고 있는 배열

      //왼쪽 차트 데이터들
      //왼쪽 도넛 차트랑 왼쪽 아래 조회 메서드 만들기 -> 했음
      //getTotalAccountList
      //세션에 바인딩되있는 memberId 보내고
      //은행, 계좌번호, 잔고 리턴받기
      //잔고 총합 구하고 계좌번호별로 분류해서 은행이랑 같이 아래 리스트에 표시하기
      //accountSeries에는 계좌번호별 퍼센티지 저장
      //accountChartOptions의 labels에는 계좌번호 저장
      accountSeries: [], //this.balancePercentageArr, //balancePercentageArr
      accountChartOptions: {
        chart: {
          width: 380,
          type: "donut",
        },
        labels: [], //this.accountNumArr, //accountNumArr
        dataLabels: {
          enabled: true,
        },
        responsive: [
          {
            breakpoint: 480,
            options: {
              chart: {
                width: 200,
              },
              legend: {
                show: false,
              },
            },
          },
        ],
        legend: {
          position: "right",
          offsetY: 0,
          height: 230,
        },
      },

      accountNumDropdown: null, // 드랍박스에서 계좌 고르고
      bankingStartDate: null, // 추적시작날짜 입력하고
      bankingEndDate: null, // 추적종료날짜 입력하기

      bankingInfosList: null, //리턴받아서 거래내역 스트링들 합쳐서 만든 프론트 표현용 스트링 배열
      bankingInfoList: null, //리턴받아서 스트링 합쳐서 만든 프론트 표현용 스트링 배열

      bankingDateList: null, //리턴받은 거래일자, bankingChartOptions.xaxis.categories에 넣기
      bankingBalanceList: null, //리턴받은 잔고, bankingSeries.data에 넣기
      bankingDepositList: null, // 리턴받은 예금액
      bankingDepositNameList: null, // 리턴받은 입금자 명 리스트
      bankingWithdrawlList: null, //리턴받은 출금액
      bankingWithdrawlToList: null, //리턴받은 출금 계좌 명 리스트

      //todo 발표 시나리오 맞춰서 데이터 수정하기
      //오른쪽 차트 데이터들
      //getBankingListDaily
      //세션에 바인딩된 memberId 보내고 시작시각 종료시각 입력한거 보내기
      //거래시각 별로 bankingSeries에 잔고 저장하고 xaxis의 categories에 배열로 거래시각 저장하기
      bankingSeries: [
        {
          name: "잔고",
          data: [],
        },
      ],
      bankingChartOptions: {
        chart: {
          height: 300,
          type: "line",
          zoom: {
            enabled: false,
          },
        },
        dataLabels: {
          enabled: false,
        },
        stroke: {
          curve: "straight",
        },
        grid: {
          row: {
            colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
            opacity: 0.5,
          },
        },
        //여기 수정하기 x축 값
        xaxis: {
          categories: [],
        },
      },
    };
  },
  created() {
    this.getTotalAccountList();
    //this.getBankingDaily();
  },
  mounted() {},
  methods: {
    //todo 아직 기능개발 안됨, 테스트 필요!!!!!!!!!!
    openNoticelist() {
      this.modal = true;

      var url = "http://localhost:9999/asset/dashboard/notice";
      var data = {
        memberId: this.memberId,
        accountNum: this.accountNumDropdown,
      };

      axios
        .post(url, data)
        .then((response) => {
          var notices = response.data;

          //this.noticeTimeArr = ["2023-09-10", "2023-09-13", "2023-09-15"];
          this.noticeTimeArr = new Array(notices.length);
          for (let i = 0; i < notices.length; i++) {
            this.noticeTimeArr[i] = notices[i].time;
          }

          //this.noticeBankNameArr = ["국민은행", "국민은행", "국민은행"];
          this.noticeBankNameArr = new Array(notices.length);
          for (let j = 0; j < notices.length; j++) {
            this.noticeBankNameArr[j] = notices[j].bankName;
          }
          //this.noticeDepositArr = [0, 0, 10000000];
          this.noticeDepositArr = new Array(notices.length);
          for (let k = 0; k < notices.length; k++) {
            this.noticeDepositArr[k] = notices[k].deposit;
          }
          //this.noticeWithdrawlArr = [9000000, 100000000, 0];
          this.noticeWithdrawlArr = new Array(notices.length);
          for (let l = 0; l < notices.length; l++) {
            this.noticeWithdrawlArr[l] = notices[l].withdrawal;
          }

          this.noticeInfoList = new Array(this.noticeTimeArr.length);
          for (let i = 0; i < this.noticeTimeArr.length; i++) {
            if (this.noticeDepositArr[i] !== 0)
              this.noticeInfoList[i] =
                this.noticeTimeArr[i] +
                " " +
                this.noticeBankNameArr[i] +
                " " +
                this.noticeDepositArr[i] +
                "원이 입금되었습니다.";
            else
              this.noticeInfoList[i] =
                this.noticeTimeArr[i] +
                " " +
                this.noticeBankNameArr[i] +
                " " +
                this.noticeWithdrawlArr[i] +
                "원이 빠져나갔습니다.";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //왼쪽 도넛 그래프(모든 계좌 총액에서 각자 계좌가 차지하는 비율 나타내는 그래프)랑
    // 아래 로그인한 사용자의 계좌별 정보(은행, 계좌번호, 잔고)
    getTotalAccountList() {
      var url =
        "http://localhost:9999/asset/dashboard?memberId=" + this.memberId;

      var accounts;

      axios
        .get(url)
        .then((response) => {
          //console.log("데이터 가져오기 성공: ", response);

          accounts = response.data;
          //console.log("accounts 길이: ", accounts);

          this.accountNumArr = new Array(accounts.length);
          for (let i = 0; i < this.accountNumArr.length; i++)
            this.accountNumArr[i] = accounts[i].accountNum;
          //console.log("accountNumArr: ", this.accountNumArr);

          this.bankNameArr = new Array(accounts.length);
          for (let i = 0; i < this.bankNameArr.length; i++)
            this.bankNameArr[i] = accounts[i].bank;
          //console.log("bankNameArr: ", this.bankNameArr);

          this.balanceArr = new Array(accounts.length);
          for (let i = 0; i < this.balanceArr.length; i++)
            this.balanceArr[i] = accounts[i].balance;
          //console.log("balanceArr: ", this.balanceArr);

          this.nickNameArr = new Array(accounts.length);
          for (let i = 0; i < this.nickNameArr.length; i++)
            this.nickNameArr[i] = accounts[i].nickName;
          //console.log("nickNameArr: ", this.nickNameArr);

          this.accountChartOptions.labels = this.nickNameArr;
          this.$refs.donutChart.refresh();

          let total = 0;
          for (let i = 0; i < this.balanceArr.length; i++)
            total += parseInt(this.balanceArr[i]);

          this.balancePercentageArr = new Array(this.accountNumArr.length);
          for (let j = 0; j < this.accountNumArr.length; j++)
            this.balancePercentageArr[j] =
              parseFloat(parseInt(this.balanceArr[j]) / total) * 100;

          this.accountSeries = this.balancePercentageArr; // 데이터 붙여줌

          //console.log("length: ", this.accountNumArr.length);

          this.accountInfoList = new Array(this.accountNumArr.length);
          this.accountInfosList = new Array(this.accountNumArr.length);
          this.bankImgArr = new Array(this.accountNumArr.length);

          this.numKor = new Array(this.accountNumArr.length);

          let k = 0;
          for (; k < this.accountNumArr.length; k++) {
            this.accountInfoList[k] =
              "(" + this.bankNameArr[k] + ") " + this.accountNumArr[k];

            if (this.bankNameArr[k] === "하나은행")
              this.bankImgArr[k] = "bank_hana";
            else if (this.bankNameArr[k] === "카카오뱅크")
              this.bankImgArr[k] = "bank_kakao";
            else if (this.bankNameArr[k] === "국민은행")
              this.bankImgArr[k] = "bank_kb";
            else if (this.bankNameArr[k] === "기업은행")
              this.bankImgArr[k] = "bank_kiub";
            else if (this.bankNameArr[k] === "농협은행")
              this.bankImgArr[k] = "bank_nonghyup";
            else if (this.bankNameArr[k] === "새마을은행")
              this.bankImgArr[k] = "bank_saemaeul";
            else if (this.bankNameArr[k] === "신한은행")
              this.bankImgArr[k] = "bank_shinhan";
            else if (this.bankNameArr[k] === "토스뱅크")
              this.bankImgArr[k] = "bank_toss";
            else if (this.bankNameArr[k] === "우리은행")
              this.bankImgArr[k] = "bank_woori";
            else this.bankImgArr[k] = "won";

            //여기서 this.balanceArr을 다른걸로
            this.accountInfosList[k] = [
              this.bankImgArr[k],
              this.balanceArr[k] + "원",
              this.accountInfoList[k],
            ];
          }

          //console.log("getTotalAccountList accountInfosList " + this.accountInfosList);

          for (let f = 0; f < this.nickNameArr.length; f++) {
            this.accountChartOptions.labels[f] = this.nickNameArr[f];
          }
          this.accountChartOptions.labels = this.nickNameArr;
          //console.log("도넛그래프 범례 : ", this.accountChartOptions.labels);

          this.$refs.donutChart.refresh();
        })
        .catch((error) => {
          console.error("getTotalAccountList() 에러 : ", error);
        });

      //console.log("test!!!!!!!!!!!!", accounts);
    },
    getBankingDaily() {
      var data = {
        accountNum: this.accountNumDropdown,
        startdate: this.bankingStartDate,
        enddate: this.bankingEndDate,
      };

      console.log("accountNum 확인해보기 : ", data);
      var url = "http://localhost:9999/asset/dashboard/time";

      var bankings;

      axios
        .post(url, data)
        .then((response) => {
          console.log("banking 내역 가져오기 : ", response);

          bankings = response.data;

          this.bankingDateList = new Array(bankings.length);
          for (let i = 0; i < this.bankingDateList.length; i++)
            this.bankingDateList[i] = bankings[i].bankingDate;
          console.log("bankingDateList: ", this.bankingDateList);

          this.bankingDepositList = new Array(bankings.length);
          for (let i = 0; i < this.bankingDepositList.length; i++)
            this.bankingDepositList[i] = bankings[i].deposit;
          console.log("bankingDepositList: ", this.bankingDepositList);

          this.bankingDepositNameList = new Array(bankings.length);
          for (let i = 0; i < this.bankingDepositNameList.length; i++)
            this.bankingDepositNameList[i] = bankings[i].depositName;
          console.log("bankingDepositNameList: ", this.bankingDepositNameList);

          this.bankingWithdrawlList = new Array(bankings.length);
          for (let i = 0; i < this.bankingWithdrawlList.length; i++)
            this.bankingWithdrawlList[i] = bankings[i].withdrawal;
          console.log("bankingWithdrawlList: ", this.bankingWithdrawlList);

          this.bankingWithdrawlToList = new Array(bankings.length);
          for (let i = 0; i < this.bankingWithdrawlToList.length; i++)
            this.bankingWithdrawlToList[i] = bankings[i].withdrawalTo;
          console.log("bankingWithdrawlToList: ", this.bankingWithdrawlToList);

          console.log(
            "getBankingDaily bankingChartOptions.xaxis.categories : " +
              this.bankingChartOptions.xaxis.categories
          );

          this.bankingBalanceList = new Array(bankings.length);
          for (let i = 0; i < this.bankingBalanceList.length; i++)
            this.bankingBalanceList[i] = bankings[i].balance;
          console.log("bankingBalanceList: ", this.bankingBalanceList);

          for (let j = 0; j < this.bankingBalanceList.length; j++) {
            this.bankingBalanceList[j] = parseInt(this.bankingBalanceList[j]);
          }

          this.$refs.bankingChart.updateSeries([
            {
              data: this.bankingBalanceList,
            },
          ]);
          this.$refs.bankingChart.updateOptions({
            xaxis: {
              categories: this.bankingDateList,
            },
          });

          this.bankingInfoList = new Array(this.bankingDateList.length);

          for (let i = 0; i < this.bankingDateList.length; i++) {
            if (this.bankingDepositNameList[i] != null) {
              console.log("getBankingList for문 안 위 i " + i);
              this.bankingInfoList[i] =
                this.bankingDateList[i] +
                " " +
                this.bankingDepositNameList[i] +
                " +" +
                this.bankingDepositList[i] +
                " " +
                this.bankingBalanceList[i];
            }
            //출금
            else if (this.bankingWithdrawlToList[i] != null) {
              console.log("getBankingList for문 안 아래 i " + i);
              this.bankingInfoList[i] =
                this.bankingDateList[i] +
                " " +
                this.bankingWithdrawlToList[i] +
                " -" +
                this.bankingWithdrawlList[i] +
                " " +
                this.bankingBalanceList[i];
            }
          }
          console.log(
            "getBankingList bankingInfoList : ",
            this.bankingInfoList
          );
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 모달 창을 닫는 메서드
    closeSuccessModal() {
      this.showSuccessModal = false;
    },
    //이미지 url 삽입용
    getImgUrl(bank) {
      var images = require.context("../assets/", false, /\.png$/);
      return images("./" + bank + ".png");
    },
    parseNumstrToKorean(numStr) {
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

          const result = this.storage[this.top - 1];
          delete this.storage[this.top - 1];
          this.top -= 1;

          return result;
        }
      }
      numStr = numStr.toString();
      console.log("numtoKOR numstr", numStr);
      //들어온 String형의 숫자를 한글자씩 자른다
      var splitedStr = numStr.split("");
      //그리고 각자 숫자로 변환해준다.
      for (let i = 0; i < splitedStr.length; i++) {
        splitedStr[i] = parseInt(splitedStr[i]);
      }
      this.numKorStack = new Stack();
      //뒤에서부터 스택에 push
      for (let j = splitedStr.length - 1; j >= 0; j--) {
        this.numKorStack.push(splitedStr[j]);
      }
      var strrst = null;
      while (this.numKorStack.top !== 0) {
        var rst = this.numKorStack.pop();

        if (rst === 1) strrst = "1";
        else if (rst === 2) strrst = "2";
        else if (rst === 3) strrst = "3";
        else if (rst === 4) strrst = "4";
        else if (rst === 5) strrst = "5";
        else if (rst === 6) strrst = "6";
        else if (rst === 7) strrst = "7";
        else if (rst === 8) strrst = "8";
        else if (rst === 9) strrst = "9";

        if (rst !== 0) {
          //천백십 더해주기
          if (this.numKorStack.size() % 4 === 3) strrst += "천";
          else if (this.numKorStack.size() % 4 === 2) strrst += "백";
          else if (this.numKorStack.size() % 4 === 1) strrst += "십";
        }
        //만억조 더해주기
        if (this.numKorStack.size() / 4 === 1) strrst += "만";
        else if (this.numKorStack.size() / 4 === 2) strrst += "억";
        else if (this.numKorStack.size() / 4 === 3) strrst += "조";
      }
      console.log("numtoKOR", strrst);
      return strrst;
    },
  },
};
</script>

<style>
p,
div,
v-app {
  font-family: "Pretendard-Regular", sans-serif;
}
.account-list {
  height: calc(100vh - 62vh);
  overflow-y: auto;
}
.banking-list {
  height: calc(100vh - 80vh);
  overflow-y: auto;
}
.outer-bg {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.8);
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

  background: #dac0a3;
  border-radius: 8px;
  width: 500px;
  height: 400px;

  /* padding: 30px; */
}
.noscroll-content::-webkit-scrollbar {
  display: none;
}
</style>

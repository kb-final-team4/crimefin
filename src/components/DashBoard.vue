<template>
  <v-app>
    <AppBar />

    <v-main>
      <v-container>
        <v-row>
          <v-col :cols="1"></v-col>
          <v-col :cols="7"></v-col>
          <v-col class="d-flex align-center justify-end" :cols="3">
            <v-btn
              @click="openaddlist"
              width="60px"
              height="30px"
              v-bind:style="{ background: '#9ba4b4', color: 'white' }"
              >계좌등록</v-btn
            >
            <v-btn
              @click="opendeletelist"
              width="60px"
              height="30px"
              v-bind:style="{
                marginLeft: '10px',
                background: '#9ba4b4',
                color: 'white',
              }"
              >계좌삭제</v-btn
            >
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

        <!-- 계좌 등록 -->
        <div class="outer-bg2" v-if="this.modal2 !== false">
          <div class="modal-bg2">
            <h4
              class="v-card__title text-center"
              v-bind:style="{ color: 'gray' }"
            >
              계좌 등록
            </h4>
            <v-card>
              <v-row>
                <v-col cols="8">
                  <v-text-field
                    v-model="accountId"
                    label="아이디"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="8">
                  <v-text-field
                    v-model="bankName"
                    label="은행명"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="8">
                  <v-text-field
                    v-model="accountNum"
                    label="계좌번호"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
                <v-col cols="1">
                  <v-btn
                    variant="tonal"
                    class="button"
                    @click="authenticateAccount"
                    >인증</v-btn
                  >
                </v-col>
              </v-row>

              <v-row>
                <!-- <div v-if="isAuthenticated"> -->
                <v-col cols="8">
                  <v-text-field
                    v-model="authNumConfirm"
                    label="입금자명"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
                <v-col cols="1">
                  <v-btn class="button" @click="confirmAuthNum">확인</v-btn>
                </v-col>
                <!-- </div> -->
              </v-row>
              <v-row>
                <v-col cols="8">
                  <v-text-field
                    v-model="accountNickname"
                    label="계좌별명"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="8">
                  <v-text-field
                    v-model="accountLimit"
                    label="limit"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-card>
            <br />
            <v-row class="d-flex justify-end">
              <v-btn
                v-bind:style="{ background: '#EADBC8', color: 'gray' }"
                variant="tonal"
                type="button"
                @click="registAccount"
                class="btn-close"
                style="margin-right: 30px"
                >등록</v-btn
              >
              <v-btn
                v-bind:style="{ background: '#EADBC8', color: 'gray' }"
                variant="tonal"
                type="button"
                @click="modal2 = false"
                class="btn-close"
                >닫기</v-btn
              >
            </v-row>
          </div>
        </div>

        <!-- 계좌 삭제 -->
        <div class="outer-bg3" v-if="this.modal3 !== false">
          <div class="modal-bg3">
            <h4
              class="v-card__title text-center"
              v-bind:style="{ color: 'gray' }"
            >
              계좌 삭제
            </h4>
            <v-card>
              <v-row>
                <v-col cols="10">
                  <v-text-field
                    v-model="memberId"
                    label="아이디"
                    variant="solo"
                    class="t-field3"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="10">
                  <v-text-field
                    v-model="bankName"
                    label="은행명"
                    variant="solo"
                    class="t-field3"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="10">
                  <v-text-field
                    v-model="accountNum"
                    label="계좌번호"
                    variant="solo"
                    class="t-field3"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-card>
            <br />
            <v-row class="d-flex justify-end">
              <v-btn
                v-bind:style="{ background: '#EADBC8', color: 'gray' }"
                variant="tonal"
                type="button"
                @click="deleteAccount"
                class="btn-close"
                style="margin-right: 30px"
                >삭제</v-btn
              >
              <v-btn
                v-bind:style="{ background: '#EADBC8', color: 'gray' }"
                variant="tonal"
                type="button"
                @click="modal3 = false"
                class="btn-close"
                >닫기</v-btn
              >
            </v-row>
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
          //data: [100000, 41000, 35000, 51000, 49000, 62000, 69000, 91000, 148000]
          // data: [
          //   "1020000",
          //   "1010000",
          //   "1050000",
          //   "1030000",
          //   "1050000",
          //   "50000",
          // ],
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
          //categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
          // categories: [
          //   "2023-09-10",
          //   "2023-09-11",
          //   "2023-09-12",
          //   "2023-09-13",
          //   "2023-09-14",
          //   "2023-09-15",
          // ],
          categories: [],
        },
      },
    };
  },

  created() {
    this.getTotalAccountList();
    this.getBankingDaily();
  },
  mounted() {},
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
    authenticateAccount() {
      console.log(11);
      try {
        // 여기에서 백엔드와 통신하여 입금자명 인증을 수행합니다.
        const response = axios.post("http://localhost:9999/asset/auth", {
          bankName: this.bankName,
          accountNum: this.accountNum,
        });

        console.log(response);
        // 인증이 성공하면 isAuthenticated 값을 true로 설정합니다.
        if (response.status === 200) {
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
      axios
        .get("http://localhost:9999/asset/auth/confirm", {
          params: {
            authNumConfirm: this.authNumConfirm,
          },
        })
        .then((response) => {
          if (response.status === 200) {
            alert("입금자명 확인 성공");
            this.isAuthenticated = true;
          } else {
            alert("입금자명이 틀립니다!");
          }
        })
        .catch((error) => {
          console.error("입금자명 찾기 오류", error);
        });
    },

    registAccount() {
      try {
        const response = axios.post("http://localhost:9999/asset/regist", {
          accountNickname: this.accountNickname,
          accountLimit: this.accountLimit,
          isAuthenticated: this.isAuthenticated,
          accountNum: this.accountNum,
        });
        console.log(response);
        if (response.status === 200) {
          alert("계좌 생성 완료");
        } else {
          alert("계좌 생성 오류 발생");
        }
      } catch (error) {
        console.error("입금자 명이 일치하지 않습니다.!!", error);
        alert("계좌 등록 중 오류 발생");
      }
    },

    deleteAccount() {
      console.log(99);
      try {
        const response = axios.delete("http://localhost:9999/asset", {
          data: {
            //delte요청은 data로 묶어서 보내야함
            memberId: this.memberId,
            bankName: this.bankName,
            accountNum: this.accountNum,
          },
        });
        console.log(response);
      } catch (error) {
        console.error("계좌 삭제 중 오류 발생", error);
        alert("계좌 삭제 중 오류 발생");
      }
    },

    openaddlist() {
      this.modal2 = true;
    },

    opendeletelist() {
      this.modal3 = true;
    },
    openNoticelist() {
      this.modal = true;
      /*
      noticeTimeArr : null, //알림 시간 리스트
      noticeBankNameArr : null, //알림 은행명 리스트
      noticeDepositArr : null, //알림 입금 리스트
      noticeWithdrawlArr : null, //알림 출금 리스트
       */
      var url = "http://localhost:9999/asset/dashboard/notice";
      var data = {
        memberId: this.memberId,
        accountNum: this.accountNumDropdown,
      };

      this.noticeTimeArr = ["2023-09-10", "2023-09-13", "2023-09-15"];
      this.noticeBankNameArr = ["국민은행", "국민은행", "국민은행"];
      this.noticeDepositArr = [null, null, 10000000];
      this.noticeWithdrawlArr = [9000000, 100000000, null];

      this.noticeInfoList = new Array(this.noticeTimeArr.length);
      for (let i = 0; i < this.noticeTimeArr.length; i++) {
        if (this.noticeDepositArr[i] !== null)
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

      /*axios.post(url,data)
          .then(response => {

          })
          .catch(error => {
            console.log(error);
          });*/
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
    //왼쪽 도넛 그래프(모든 계좌 총액에서 각자 계좌가 차지하는 비율 나타내는 그래프)랑
    // 아래 로그인한 사용자의 계좌별 정보(은행, 계좌번호, 잔고)
    getTotalAccountList() {
      var url =
        "http://localhost:9999/asset/dashboard?memberId=" + this.memberId;

      var accounts;

      axios
        .get(url)
        .then((response) => {
          console.log("데이터 가져오기 성공: ", response);

          accounts = response.data;
          console.log("accounts 길이: ", accounts);

          this.accountNumArr = new Array(accounts.length);
          for (let i = 0; i < this.accountNumArr.length; i++)
            this.accountNumArr[i] = accounts[i].accountNum;
          console.log("accountNumArr: ", this.accountNumArr);

          this.bankNameArr = new Array(accounts.length);
          for (let i = 0; i < this.bankNameArr.length; i++)
            this.bankNameArr[i] = accounts[i].bank;
          console.log("bankNameArr: ", this.bankNameArr);

          this.balanceArr = new Array(accounts.length);
          for (let i = 0; i < this.balanceArr.length; i++)
            this.balanceArr[i] = accounts[i].balance;
          console.log("balanceArr: ", this.balanceArr);

          this.nickNameArr = new Array(accounts.length);
          for (let i = 0; i < this.nickNameArr.length; i++)
            this.nickNameArr[i] = accounts[i].nickName;
          console.log("nickNameArr: ", this.nickNameArr);

          this.accountChartOptions.labels = this.nickNameArr;
          //console.log("labels 배열 : ", this.accountChartOptions.labels);

          //this.accountChartOptions.labels = new Array(this.nickNameArr.length);
          //for (let i = 0; i < this.nickNameArr.length; i++)
          //console.log("nickname 배열 : ", this.nickNameArr[i]);
          //this.accountChartOptions.labels[i] = this.nickNameArr[i];

          // this.accountChartOptions.legend = {
          //   position: "right",
          //   offsetY: 0,
          //   height: 230,
          //   labels: {
          //     useSeriesColors: false, // 범례 색상을 데이터 포인트 색상과 동일하게 사용하지 않도록 설정
          //     colors: this.nickNameArr, // 범례 항목의 이름을 this.nickNameArr의 값으로 설정
          //   },
          // };

          // this.accountChartOptions.series = this.nickNameArr.map(
          //   (name, index) => {
          //     return {
          //       name: name,
          //       data: this.nickNameArr, // 실제 데이터 포인트 값을 여기에 넣으세요
          //     };
          //   }
          // );

          // this.accountChartOptions.series = this.nickNameArr.map((name, index) => {
          //   return {
          //     name: name,
          //     data: this.nickNameArr, // 실제 데이터 포인트 값을 여기에 넣으세요
          //   };
          // });

          let total = 0;
          for (let i = 0; i < this.balanceArr.length; i++)
            total += parseInt(this.balanceArr[i]);

          this.balancePercentageArr = new Array(this.accountNumArr.length);
          for (let j = 0; j < this.accountNumArr.length; j++)
            this.balancePercentageArr[j] =
              parseFloat(parseInt(this.balanceArr[j]) / total) * 100;

          this.accountSeries = this.balancePercentageArr;

          console.log("length: ", this.accountNumArr.length);

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

          console.log(
            "getTotalAccountList accountInfosList " + this.accountInfosList
          );

          // 서버 응답 데이터를 accounts에 저장
          //this.rvo = response.data;
          //console.log("데이터 가져오기 성공!!!: ", rvo);
        })
        .catch((error) => {
          console.error("데이터를 가져오는 중 오류 발생:", error);
        });

      //console.log("test!!!!!!!!!!!!", accounts);
    },
    getBankingDaily() {
      console.log("accountNumArr iIIIIs : ", this.accountNumArr);

      //제일 잔고 많은 계좌번호 가져오기
      var url =
        "http://localhost:9999/asset/dashboard/main?memberId=" + this.memberId;

      var mainAccountNum;

      axios
        .get(url)
        .then((response) => {
          console.log("데이터 가져오기 성공: ", response);

          mainAccountNum = response.data;
          console.log("제일 잔고 많은 계좌번호: ", mainAccountNum);
        })
        .catch((error) => {
          console.error("데이터를 가져오는 중 오류 발생:", error);
        })
        .finally(() => {
          // axios 요청이 완료된 후에 mainAccountNum 값을 사용할 수 있음
          console.log("계좌번호 체크 : ", mainAccountNum);

          // mainAccountNum을 이용한 다른 작업을 수행할 수 있음
          if (mainAccountNum !== undefined) {
            // mainAccountNum을 사용한 작업
          } else {
            // mainAccountNum이 정의되지 않은 경우 처리
          }
        });

      var data = {
        //memberId: this.memberId,
        // accountNum: this.accountNumDropdown,
        //accountNum: mainAccountNum,
        accountNum: "9991111",
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

          this.bankingChartOptions.xaxis.categories = this.bankingDateList;
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
          //console.log("getBankingDaily bankingSeries.data before"+ this.bankingSeries.data);
          this.bankingSeries.data = this.bankingBalanceList;
          console.log(
            "getBankingDaily bankingSeries.data after" + this.bankingSeries.data
          );

          //this.$ref.bankingChart.refresh();

          this.$nextTick(() => {
            this.$refs.bankingChart.refresh();
          });

          this.bankingInfoList = new Array(this.bankingDateList.length);

          for (let i = 0; i < this.bankingDateList.length; i++) {
            if (this.bankingDepositList[i] != null) {
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
            else {
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
        })
        .catch((error) => {
          console.log("에러 발생");
        });

      //console.log("getBankingDaily accountNumDropdown " + data.accountNum);
      //console.log("getBankingDaily startdate" + data.startdate); //controller에서는 substring해서 쓰면 될듯
      //console.log("getBankingDaily enddate" + data.enddate);

      //테스트 데이타 백에서 넘어오는거 보고 수정하기
      // this.bankingDateList = [
      //       "2023-09-10",
      //       "2023-09-11",
      //       "2023-09-12",
      //       "2023-09-13",
      //       "2023-09-14",
      //       "2023-09-15",
      //     ];
      //     this.bankingDepositList = [
      //       "20000",
      //       null,
      //       "40000",
      //       null,
      //       "20000",
      //       null,
      //     ];
      //     this.bankingDepositNameList = [
      //       "cys",
      //       null,
      //       "cys2",
      //       null,
      //       "cys3",
      //       null,
      //     ];
      //     this.bankingWithdrawlList = [
      //       null,
      //       "10000",
      //       null,
      //       "20000",
      //       null,
      //       "1000000",
      //     ];
      //     this.bankingWithdrawlToList = [
      //       null,
      //       "ocl",
      //       null,
      //       "ocl2",
      //       null,
      //       "나쁜놈",
      //     ];

      //     this.bankingChartOptions.xaxis.categories = this.bankingDateList;
      //     console.log(
      //       "getBankingDaily bankingChartOptions.xaxis.categories : " +
      //         this.bankingChartOptions.xaxis.categories
      //     );

      //     this.bankingBalanceList = [
      //       "1020000",
      //       "1010000",
      //       "1050000",
      //       "1030000",
      //       "1050000",
      //       "50000",
      //     ];
      //     for (let j = 0; j < this.bankingBalanceList.length; j++) {
      //       this.bankingBalanceList[j] = parseInt(this.bankingBalanceList[j]);
      //     }
      //     //console.log("getBankingDaily bankingSeries.data before"+ this.bankingSeries.data);
      //     this.bankingSeries.data = this.bankingBalanceList;
      //     console.log(
      //       "getBankingDaily bankingSeries.data after" + this.bankingSeries.data
      //     );

      /*this.bankingInfosList = new Array(4);
      this.bankingInfosList[0] = new Array(this.bankingDateList.length);
      this.bankingInfosList[1] = new Array(this.bankingDateList.length);
      this.bankingInfosList[2] = new Array(this.bankingDateList.length);
      this.bankingInfosList[3] = new Array(this.bankingDateList.length);

      for (let i = 0; i < this.bankingDateList.length; i++) {
        this.bankingInfosList[0][i] = this.bankingDateList[i];
        this.bankingInfosList[3][i] = this.bankingBalanceList[i];
        //오른쪽차트 아래에 표기하는 곳
        //입금
        if (this.bankingDepositList[i] != null) {
          this.bankingInfosList[1][i] = this.bankingDepositNameList[i];
          this.bankingInfosList[2][i] = this.bankingDepositList[i];
        }
        //출금
        else {
          this.bankingInfosList[1][i] = this.bankingWithdrawlToList[i];
          this.bankingInfosList[2][i] = this.bankingWithdrawlList[i];
        }
      }*/

      // this.bankingInfoList = new Array(this.bankingDateList.length);

      // for (let i = 0; i < this.bankingDateList.length; i++) {
      //   if (this.bankingDepositList[i] != null) {
      //     this.bankingInfoList[i] =
      //       this.bankingDateList[i] +
      //       " " +
      //       this.bankingDepositNameList[i] +
      //       " +" +
      //       this.bankingDepositList[i] +
      //       " " +
      //       this.bankingBalanceList[i];
      //   }
      //   //출금
      //   else {
      //     this.bankingInfoList[i] =
      //       this.bankingDateList[i] +
      //       " " +
      //       this.bankingWithdrawlToList[i] +
      //       " -" +
      //       this.bankingWithdrawlList[i] +
      //       " " +
      //       this.bankingBalanceList[i];
      //   }
      // }

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
    // 모달 창을 닫는 메서드
    closeSuccessModal() {
      this.showSuccessModal = false;
    },
    //이미지 url 삽입용
    getImgUrl(bank) {
      var images = require.context("../assets/", false, /\.png$/);
      return images("./" + bank + ".png");
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
  width: 200%;
  height: 200%;
  background: #102c57;
  position: fixed;
  padding: 20px;
}

.outer-bg2 {
  width: 200%;
  height: 350%;
  background: #102c57;
  position: fixed;
  padding: 40px;
  margin-bottom: 30px;
}

.outer-bg3 {
  width: 200%;
  height: 350%;
  background: #102c57;
  position: fixed;
  padding: 40px;
  margin-bottom: 30px;
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

.modal-bg2 {
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
  width: 790px;

  padding-bottom: 30px;
  padding-left: 50px;
}

.modal-bg3 {
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
  width: 790px;

  padding-bottom: 30px;
}

.t-field {
  width: 550px;
  background-color: white;
  margin-top: 0%;
  padding-left: 40px;
}

.t-field3 {
  width: 550px;
  background-color: white;
  margin-top: 0%;
  padding-left: 100px;
}

.button {
  position: absolute;
  margin-top: 5px;
}
.noscroll-content::-webkit-scrollbar {
  display: none;
}
</style>

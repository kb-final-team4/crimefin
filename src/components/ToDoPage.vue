<template>
  <v-app>
    <AppBar/>
    <v-main>
      <v-container id="rightArea" fluid>
        <v-layout column align-center>
          <v-flex>
            <div class="btn-center">
              <v-col cols="auto">
                <v-btn class="red--text" @click="showManual" value="manualView">매뉴얼 보기</v-btn>
              </v-col>
              <v-col cols="auto">
                <v-btn @click="showChecklist" value="checklistView">체크리스트 보기</v-btn>
              </v-col>
              <v-col cols="auto">
                <v-btn class="blue--text" @click="showMap" value="mapView">가까운 기관 찾기</v-btn>
              </v-col>
            </div>
          </v-flex>
          <v-flex>
            <v-col>
              <v-row>
                <div class="white--text text-h6 mx-auto" v-show="policeMapDesc">
                  주변의 경찰서를 보여드리고 있습니다. 마우스 휠로 확대/축소 하세요.
                </div>
                <div class="white--text text-h6 mx-auto" v-show="bankMapDesc">
                  주변의 은행을 보여드리고 있습니다. 마우스 휠로 확대/축소 하세요.
                </div>
              </v-row>
              <v-row>
                <div v-show="isMap" class="toggleArea">
                  <div id="map"></div>
                  <div class = "btn-center">
                    <v-btn class="red--text" @click="showBankMap">은행 찾기</v-btn>
                    <v-btn class="blue--text" @click="showPoliceMap">경찰서 찾기</v-btn>
                  </div>
                </div>
              </v-row>
              <v-row>
              <div v-show="isManual" id="manual" class="v-card v-sheet theme--dark">
                <div class="v-card__title"><h2>대응 매뉴얼</h2></div>
                <div class="v-card__text my-list card-content">
                  <ul>
                    <li>1. 입금 금융회사 또는 송금 금융회사콜센터에 즉시 전화하여 피해신고 및 모든 계좌 지급정지 신청
                      (경찰청 112 및 금감원 1332)</li>
                    <li>2. 비행기 모드로 전환 후 휴대전화 초기화</li>
                    <li>
                      3. 다른 휴대전화 및 PC로 개인정보 노출사실 등록
                    </li>
                    <li>
                      4. 금감원 개인정보 노출자 사고예방시스템 (pd.fss.or.kr) 접속
                    </li>
                    <li>
                      5. 이용약관, 개인정보제공 등 동의 후 휴대전화 인증으로 본인 확인
                    </li>
                    <li>
                      6. 개인정보 노출사실을 등록하여 신규계좌 개설, 신용카드 발급 등 제한
                    </li>
                    <li>
                      7. 본인 계좌 지급정지(일괄 또는 부분) 신청
                    </li>
                    <li>
                      8. 금융결제원 계좌정보통합관리서비스(www.payinfo.or.kr) 접속
                    </li>
                    <li>
                      9. 본인 계좌 지급정지 메뉴에서 은행권, 제2금융권, 증권사 클릭
                    </li>
                    <li>
                      10. 공동인증서 및 휴대전화 인증(2중 인증)으로 본인 확인
                    </li>
                    <li>
                      11. 지급정지를 신청할 계좌를 선택 후 지급정지 신청
                    </li>
                    <li>
                      12 . 한국정보통신진흥협회 명의도용방지 서비스 (www.msafer.or.kr)접속
                    </li>
                    <li>
                      13. 공동인증서 등으로 로그인
                    </li>
                    <li>
                      14. 가입사실현황조회 서비스 메뉴 클릭, 본인명의로 개설된 휴대전화 개설 사건여부를 확인
                    </li>
                    <li>
                      15. 명의도용 휴대전화가 개통된 경우. 즉시 해당 이동통신사 등에 회선해지 신청 및 명의도용 신고
                    </li>
                    <li>
                      16. 가입제한 서비스 메뉴 클릭하여, 본인명의 휴대전화 신규개설 차단
                    </li>
                    <li>
                      17. 경찰서(사이버 수사대)에서 발급한 사건사고사실확인원 등 증빙서류와 함께 지급정지 신청한 영업점에 피해구제신청 서면접수
                    (신청일 3영업일이내, 필요서류는 방문 전 금융회사 또는 경찰서 문의)
                    </li>
                  </ul>
                </div>
              </div>
              </v-row>
              <v-row>
              <div v-show="isChecklist" class="v-card v-sheet theme--dark" id="checklist">
                <div class="v-card__title"><h2>체크 리스트</h2></div>
                <div class="v-card__text my-list card-content">
                <input type="checkbox" v-model="arr" value="1">
                1. 입금 금융회사 또는 송금 금융회사콜센터에 즉시 전화하여 피해신고 및 모든 계좌 지급정지  신청<br>
                <input type="checkbox" v-model="arr" value="2">
                2. 비행기 모드로 전환 후 휴대전화 초기화<br>
                <input type="checkbox" v-model="arr" value="3">
                3. 금감원 개인정보 노출자 사고예방시스템 (pd.fss.or.kr) 접속<br>
                <input type="checkbox" v-model="arr" value="4">
                4. 개인정보 노출사실을 등록, 본인 계좌 지급정지 신청<br>
                <input type="checkbox" v-model="arr" value="5">
                5. 금융결제원 계좌정보통합관리서비스(www.payinfo.or.kr) 접속<br>
                <input type="checkbox" v-model="arr" value="6">
                6. 본인 계좌 지급정지 메뉴에서 은행권, 제2금융권, 증권사 클릭, 지급정지를 신청할 계좌를 선택 후 지급정지 신청<br>
                <input type="checkbox" v-model="arr" value="7">
                7. 사건사고사실확인원 등 증빙서류와 함께 지급정지 신청한 영업점에 피해구제신청 서면접수<br>
                </div>
                <div id="checklistSaveBtn" class = "btn-center">
                  <v-btn @click="saveCheckList">체크리스트 저장</v-btn>
                </div>
              </div>
              </v-row>
            </v-col>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue';
import axios from "axios"; // 상단바 컴포넌트 import
export default {
  data() {
    return {
      policeMapDesc: false,
      bankMapDesc: false,
      isManual: false,
      isMap: true,
      isChecklist: false,
      //카카오맵 객체 담아올 변수
      kmap: null,
      //카카오 geocoder 객체 담아올 변수
      geocoder: null,
      //checklist value 담아서 length 메서드 파라미터로 활용
      arr: [],
      // 로그인된 사용자 주소 여기다 넣기
      //this.$session.set('user_address', response.data.address)
      userAddr: null,
      //사용자 주소 좌표로 변환한 값
      kcoords: null,
      ps: null,
      bounds: null,
      marker: null,
      kmemberId: null,
    };
  },
  props: ['result'],
  mounted() {
    if(window.kakao && window.kakao.maps){
      this.initMap();
    }
    else{
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.initMap);

      script.src ="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=efddc0d6a66242e7f24760eabe9e9174&libraries=services";
      script.addEventListener("load", () => {
        //전역객체지만 window를 통해서 접근해야함
        console.log("이미 로딩됨: ", window.kakao);
      })

      document.head.appendChild(script);
      console.log("카카오맵 api 로딩함");
    }
  },
  methods: {
    goToMainPage() {
      // MainPage.vue로 돌아가는 로직을 추가하세요.
      this.$router.push('/main');
    },
    saveCheckList() {
      var url = "http://localhost:9999/phishing/check";
      var typeToNum = 0;

      //이 페이지까지 맨 처음에 type이 타고 넘어왔다는 가정 하에 작성함.
      if(this.$route.params.type === 'phone')
        typeToNum = 1;
      else if(this.$route.params.type === 'message')
        typeToNum = 2;
      else
        typeToNum = 3;

      //this.kmemberId = this.$route.params.memberInfo.memberId;
      this.kmemberId = 'test';

      var data = {
        // this.$route.params.memberInfo.memberId
        // 로그인에서 쭉 받아왔으면 MainPage -> PhishingNumber -> PhishingNumberResult -> ai조회결과페이지 -> ToDoPage(여기)
        memberId: this.kmemberId,
        type: {typeToNum},
        //순서대로 체크하는 거니까 length + 1 받아오면 다음에 할 번호로는 저장됨
        //마이페이지에서 불러올땐 체크리스트 데이터를 v-show 사용해서 조건주기 (idx포함해서 끝까지 true, 나머지 false해서 안 뜨게)
        idx: this.arr.length + 1,
      };

      axios.post(url, data)
          .then(response => {
            //삽입이라 response 자체가 없긴할거임
            console.log(response);

          })
          .catch(error => {
            console.error(error);
          });
    },
    showMap(){
      this.policeMapDesc = false;
      this.bankMapDesc = false;

      this.isMap = true;
      this.isChecklist = false;
      this.isManual = false;

      var mapContainer = document.getElementById("map");
      const options = {
        center: this.kcoords,
        level: 4,
      };
      console.log("showmap coords after : "+this.kcoords);
      this.kmap = new kakao.maps.Map(mapContainer, options);

    },
    showBankMap(){
      this.userAddr = this.$session.get('loginMemberAddress');
      this.policeMapDesc = false;
      this.bankMapDesc = true;

      this.isMap = true;
      this.isChecklist = false;
      this.isManual = false;

      this.geocoder.addressSearch(this.userAddr, (result, status) => {
        if (status === kakao.maps.services.Status.OK)
          this.kcoords = new kakao.maps.LatLng(result[0].y, result[0].x);
      });
      console.log("showBankMap coords : "+this.kcoords);

      var mapContainer = document.getElementById("map");
      const options = {
        center: this.kcoords,
        level: 2,
      };
      //console.log("showmap coords after : "+this.kcoords);
      this.kmap = new kakao.maps.Map(mapContainer, options);

      var kkcoords = this.kcoords;
      var kkmap = this.kmap;
      var kmarker = this.marker;

      this.ps.keywordSearch(this.userAddr + '은행', placesSearchCB);

      function placesSearchCB (data, status, pagination) {

        /*console.log("placeSearchCB data "+ data);
        console.log("placeSearchCB status "+ status);
        console.log("placeSearchCB pagination "+ pagination);*/

        var mapContainer = document.getElementById("map");
        const koptions = {
          center: kkcoords,
          level: 2,
        };
        kkmap = new kakao.maps.Map(mapContainer, koptions);
        console.log("placeSearchCB this.map "+ kkmap);

        for (var i=0; i<data.length; i++) {
          kmarker = new kakao.maps.Marker({
            map: kkmap,
            position: new kakao.maps.LatLng(data[i].y, data[i].x)
          });
        }

      }
    },
    showPoliceMap(){
      this.userAddr = this.$session.get('loginMemberAddress');
      this.policeMapDesc = true;
      this.bankMapDesc = false;

      this.isMap = true;
      this.isChecklist = false;
      this.isManual = false;

      this.geocoder.addressSearch(this.userAddr, (result, status) => {
        if (status === kakao.maps.services.Status.OK)
          this.kcoords = new kakao.maps.LatLng(result[0].y, result[0].x);
      });
      console.log("showPoliceMap coords : "+this.kcoords);

      var mapContainer = document.getElementById("map");
      const options = {
        center: this.kcoords,
        level: 4,
      };
      //console.log("showmap coords after : "+this.kcoords);
      this.kmap = new kakao.maps.Map(mapContainer, options);

      var kkcoords = this.kcoords;
      var kkmap = this.kmap;
      var kmarker = this.marker;

      this.ps.keywordSearch(this.userAddr + '경찰서', placesSearchCB);

      function placesSearchCB (data, status, pagination) {

        //console.log("placeSearchCB data "+ data);
        //console.log("placeSearchCB status "+ status);
        //console.log("placeSearchCB pagination "+ pagination);

        var mapContainer = document.getElementById("map");
        const koptions = {
          center: kkcoords,
          level: 4,
        };
        kkmap = new kakao.maps.Map(mapContainer, koptions);
        //console.log("placeSearchCB this.map "+ kkmap);

        for (var i=0; i<data.length; i++) {
          kmarker = new kakao.maps.Marker({
            map: kkmap,
            position: new kakao.maps.LatLng(data[i].y, data[i].x)
          });
        }
      }
    },
    showManual(){
      this.policeMapDesc = false;
      this.bankMapDesc = false;
      this.isMap = false;
      this.isChecklist = false;
      this.isManual = true;
    },
    showChecklist(){
      this.policeMapDesc = false;
      this.bankMapDesc = false;
      this.isMap = false;
      this.isChecklist = true;
      this.isManual = false;
    },
    initMap() {
      this.policeMapDesc = false;
      this.bankMapDesc = false;
      const container = document.getElementById("map");
      console.log("initmap : " + container);
      const options = {
        center: new kakao.maps.LatLng(37.5033532547808, 127.049875769645),
        level: 4,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.kmap = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder();
      this.ps = new kakao.maps.services.Places();
      this.bounds = new kakao.maps.LatLngBounds();

      this.geocoder.addressSearch(this.userAddr, (result, status) => {
        if (status === kakao.maps.services.Status.OK)
          this.kcoords = new kakao.maps.LatLng(result[0].y, result[0].x);
      });
      console.log("initMap coords : "+this.kcoords);
    },
  },
  components: {
    AppBar, // 상단바 컴포넌트 등록
  },
};
</script>
<style>
#rightArea{
  background-color: #2c3e50;
}
#map{
  width: 1100px;
  justify-content: center;
  height: 500px;
}
#checklist{
  padding: 30px 30px 30px 30px;
}
.btn-center{
  margin: 20px;
  display: flex;
  justify-content: center;
}
.separate{
  margin: 10px 20px 100px 0px;
  color: whitesmoke;
  line-height: 1.8;
  font-size: 16px;
}
.my-list {
  width: 1100px;
  height: calc(100vh - 200px);
  overflow-y: auto;
}
.card-content{
  list-style: none;
  line-height: 1.8;
  font-size: 16px;
}
</style>
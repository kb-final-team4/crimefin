<template>
  <v-app>
    <AppBar />
    <v-main v-bind:style="{ background: '#F0F0F0' }">
      <v-container id="rightArea" fluid>
        <v-layout column align-center>
          <v-flex>
            <v-col>
              <!--멘트-->
              <v-row
                class="d-flex justify-center align-center"
                style="height: 100%"
              >
                <div
                  class="v-card__title justify-center subtitle text-h5"
                  v-bind:style="{ color: '#27374D' }"
                >
                  <b
                    >{{ loggedInUser }} 님 주변의 은행 및 경찰서를 보여드리고
                    있습니다.</b
                  >
                </div>
              </v-row>
              <v-row class="d-flex justify-center align-center">
                <div
                  class="v-card__title justify-center subtitle text-h7"
                  v-bind:style="{ color: '#27374D', marginTop: '-50px' }"
                >
                  마우스 휠을 이용해 확대, 축소 하세요.<br />
                </div>
              </v-row>

              <!--찾기 버튼-->
              <v-row class="d-flex justify-center align-center">
                <div
                  class="btn-center"
                  v-bind:style="{ marginTop: '-20px', marginBottom: '30px' }"
                >
                  <v-btn
                    id="v-btn"
                    class="mx-auto mr-2"
                    @click="showBankMap"
                    color="#14274E"
                    >은행 찾기</v-btn
                  >
                  <v-btn
                    id="v-btn"
                    class="mx-auto ml-2"
                    @click="showPoliceMap"
                    color="#14274E"
                    >경찰서 찾기</v-btn
                  >
                </div>
              </v-row>

              <!--지도-->
              <div v-show="isMap" class="toggleArea">
                <div id="map"></div>
              </div>
            </v-col>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import AppBar from "../views/AppBar.vue";
import Footer from "../views/Footer.vue";

// 지도
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
      userAddr: this.$session.get("loginMemberAddress"),
      //사용자 주소 좌표로 변환한 값
      kcoords: null,
      ps: null,
      bounds: null,
      marker: null,
      kmemberId: null,
      loggedInUser: this.$session.get("loginMemberName"), // 로그인한 사용자 이름 설정
    };
  },
  props: ["result"],
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.initMap);

      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=efddc0d6a66242e7f24760eabe9e9174&libraries=services";
      script.addEventListener("load", () => {
        console.log("이미 로딩됨: ", window.kakao);
      });

      document.head.appendChild(script);
      console.log("카카오맵 api 로딩함");
      this.userAddr = this.$session.get("loginMemberAddress");
    }
  },
  methods: {
    goToMainPage() {
      // MainPage.vue로 돌아가는 로직을 추가하세요.
      this.$router.push("/main");
    },

    showMap() {
      this.policeMapDesc = false;
      this.bankMapDesc = false;

      var mapContainer = document.getElementById("map");
      const options = {
        center: this.kcoords,
        level: 4,
      };
      console.log("showmap coords after : " + this.kcoords);
      this.kmap = new kakao.maps.Map(mapContainer, options);
    },
    showBankMap() {
      console.log("showBankMap userAddress", this.userAddr);
      this.showMap();
      this.policeMapDesc = false;
      this.bankMapDesc = true;

      var mapContainer = document.getElementById("map");
      const options = {
        center: this.kcoords,
        level: 3,
      };
      //console.log("showmap coords after : "+this.kcoords);
      this.kmap = new kakao.maps.Map(mapContainer, options);

      var kkcoords = this.kcoords;
      var kkmap = this.kmap;
      var kmarker = this.marker;

      this.ps.keywordSearch(this.userAddr + "은행", placesSearchCB);

      function placesSearchCB(data, status, pagination) {
        var mapContainer = document.getElementById("map");
        const koptions = {
          center: kkcoords,
          level: 3,
        };
        kkmap = new kakao.maps.Map(mapContainer, koptions);
        console.log("placeSearchCB this.map " + kkmap);

        for (var i = 0; i < data.length; i++) {
          kmarker = new kakao.maps.Marker({
            map: kkmap,
            position: new kakao.maps.LatLng(data[i].y, data[i].x),
          });
        }
      }
    },
    showPoliceMap() {
      console.log("showPoliceMap userAddress", this.userAddr);
      this.showMap();
      this.policeMapDesc = true;
      this.bankMapDesc = false;

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

      this.ps.keywordSearch(this.userAddr + "경찰서", placesSearchCB);

      function placesSearchCB(data, status, pagination) {
        var mapContainer = document.getElementById("map");
        const koptions = {
          center: kkcoords,
          level: 3,
        };
        kkmap = new kakao.maps.Map(mapContainer, koptions);

        for (var i = 0; i < data.length; i++) {
          kmarker = new kakao.maps.Marker({
            map: kkmap,
            position: new kakao.maps.LatLng(data[i].y, data[i].x),
          });
        }
      }
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

      console.log("initMap userAddr : " + this.userAddr);

      this.geocoder.addressSearch(this.userAddr, (result, status) => {
        if (status === kakao.maps.services.Status.OK)
          this.kcoords = new kakao.maps.LatLng(result[0].y, result[0].x);
      });
      console.log("initMap coords : " + this.kcoords);
    },
  },
  components: {
    AppBar, // 상단바 컴포넌트 등록
    Footer,
  },
};
</script>
<style>
#rightArea {
  background-color: #f0f0f0;
}
#map {
  width: 800px;
  justify-content: center;
  height: 400px;
}
#v-btn {
  background-color: #14274e;
  color: white;
  justify-content: center;
}
/*.btn-center {
  margin: 20px;
  display: flex;
  justify-content: center;
}*/
/*.separate {
  margin: 10px 20px 50px 0px;
  color: whitesmoke;
  line-height: 1.8;
  font-size: 16px;
} */
</style>

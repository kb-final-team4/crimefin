<template>
  <v-app>
    <AppBar/>
    <v-main>
      <v-container id="rightArea" fluid>
        <v-layout column align-center>
          <v-flex>
            <v-col>
              <div v-show="isMap" class="toggleArea">
                <div id="map"></div>
                <div class = "btn-center">
                  <div class="separate" v-show="policeMapDesc">
                    주변의 경찰서를 보여드리고 있습니다. 마우스 휠로 확대/축소 하세요.
                  </div>
                  <div class="separate" v-show="bankMapDesc">
                    주변의 은행을 보여드리고 있습니다. 마우스 휠로 확대/축소 하세요.
                  </div>
                  <v-btn class="red--text" @click="showBankMap">은행 찾기</v-btn>
                  <v-btn class="blue--text" @click="showPoliceMap">경찰서 찾기</v-btn>
                </div>
              </div>
            </v-col>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import AppBar from '../views/AppBar.vue';
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
      userAddr: this.$session.get('user_address'),
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

    showMap(){
      this.policeMapDesc = false;
      this.bankMapDesc = false;

      var mapContainer = document.getElementById("map");
      const options = {
        center: this.kcoords,
        level: 4,
      };
      console.log("showmap coords after : "+this.kcoords);
      this.kmap = new kakao.maps.Map(mapContainer, options);

    },
    showBankMap(){
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

      this.ps.keywordSearch(this.userAddr + '은행', placesSearchCB);

      function placesSearchCB (data, status, pagination) {

        var mapContainer = document.getElementById("map");
        const koptions = {
          center: kkcoords,
          level: 3,
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

      this.ps.keywordSearch(this.userAddr + '경찰서', placesSearchCB);

      function placesSearchCB (data, status, pagination) {

        var mapContainer = document.getElementById("map");
        const koptions = {
          center: kkcoords,
          level: 3,
        };
        kkmap = new kakao.maps.Map(mapContainer, koptions);

        for (var i=0; i<data.length; i++) {
          kmarker = new kakao.maps.Marker({
            map: kkmap,
            position: new kakao.maps.LatLng(data[i].y, data[i].x)
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
.btn-center{
  margin: 20px;
  display: flex;
  justify-content: center;
}
.separate{
  margin: 10px 20px 50px 0px;
  color: whitesmoke;
  line-height: 1.8;
  font-size: 16px;
}
</style>
<template>
    
        <div id="app">
        <v-app>
            <v-content>
                <v-container fluid>
                    <v-row>
                        <v-col>
                            <!-- 앱바 자리-->
                            <AppBar fixed app/>
                        </v-col>
                    </v-row>
                    <v-row no-gutters>
                        <!-- <v-row> -->
                        <v-col cols="2">
                            <v-card>
                                <v-navigation-drawer theme="dark" width="0px;" >
                                    <v-list nav>
                                        <v-list-item
                                            v-for="item in items"
                                            :key="item.title"
                                            :to="item.to"
                                            link
                                        >
                                        <v-list-item>
                                        <v-list-item-icon>
                                            <v-icon>{{ item.icon }}</v-icon>
                                        </v-list-item-icon>
                                        <v-list-item-content>
                                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                        </v-list-item>
                                    </v-list>
                                </v-navigation-drawer>
                                <v-main style="height: 1000px;"></v-main>
                            </v-card>
                        </v-col> 
                        <v-col cols="10" style="height: 1000px;">
                            <!-- <v-card> -->
                                
                                <div class="modal-bg2">
            <h4
              class="v-card__title text-center"
              v-bind:style="{ color: 'gray' }"
              style="margin-left: 170px"
            >
              계좌 등록
            </h4>
            <!-- <v-card> -->
                <div class="card">
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="accountId"
                    label="아이디"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="bankName"
                    label="은행명"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="accountNum"
                    label="계좌번호"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                
                </v-col>
                  <v-btn
                    variant="tonal"
                    class="button"
                    @click="authenticateAccount"
                    style="margin-left: 1110px;"
                    >인증</v-btn
                  >
                
              </v-row>

              <v-row>
                <!-- <div v-if="isAuthenticated"> -->
                <v-col cols="12">
                  <v-text-field
                    v-model="authNumConfirm"
                    label="입금자명"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
                
                  <v-btn class="button" @click="confirmAuthNum" style="margin-left: 1110px;">확인</v-btn>
                
                <!-- </div> -->
              </v-row>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="accountNickname"
                    label="계좌별명"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="accountLimit"
                    label="limit"
                    variant="solo"
                    class="t-field"
                  ></v-text-field>
                </v-col>
              </v-row>
              </div>
            <!-- </v-card> -->
            <br />
            <v-row class="d-flex justify-end">
              <router-link to="/main">
              <v-btn
                variant="tonal"
                type="button"
                @click="registAccount"
                class="btn-close"
                style="margin-left: 170px"
                >등록</v-btn
              >
              </router-link>
            </v-row>
          </div>

                                <!-- <div v-if="isRegistered">
                                    <p>계좌가 등록되었습니다!</p>
                                </div> -->
                            <!-- </v-card> -->
                        </v-col>   
                    </v-row>
                 </v-container>
            </v-content>
        </v-app>  
    </div>
</template>
<script>
import AppBar from '../views/AppBar.vue';
import axios from "axios";

export default {
    components: {
      AppBar, // 상단바 컴포넌트를 등록합니다.
    },  
    name:'App',
    data() {
      return {
        items: [
        { title: "계좌등록", to: "/addaccount", icon: "mdi-plus" },
        { title: "계좌삭제", to: "/deleteaccount", icon: "mdi-delete-empty-outline" },

        // 다른 아이템을 필요에 따라 추가할 수 있습니다.
      ],

        
      accountId: '',
      bankName:'',
      accountNum:'',
      authNumConfirm:'',
      isAuthenticated: false, // 인증 여부
      accountNickname:'',
      accountLimit:'',
      };
    },
    methods:{
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
            alert("계좌 정보가 성공적으로 인증되었습니다.");//성공
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
            alert("계좌 생성 완료");//성공
          }
        } catch (error) {
          console.error("입금자 명이 일치하지 않습니다.!!", error);
          alert("계좌 등록 중 오류 발생");
        }
      },
    }
}
</script>
<style>
    .modal-bg2{
        background-color: white;

        position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    border: 4px solid #444766; /* 테두리 스타일, 두께 및 색상 설정 */
    padding:45px;
    padding-right: 200px;
    
    margin-left: 100px;

    }
    .no-shadow {
        box-shadow: none !important;
    }

    .card{
        margin-left:150px;
    }
    .button{
        position: fixed;
        left:-680px;
    }
</style>
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
                            <v-card class="no-shadow">
                                
                                <div class="modal-bg2">
            <h4
              class="v-card__title text-center"
              v-bind:style="{ color: 'gray' }"
            >
              계좌 등록
            </h4>
            <!-- <v-card> -->
                <div class="card">
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
                  <v-btn
                    variant="tonal"
                    class="button"
                    @click="authenticateAccount"
                    >인증</v-btn
                  >
                
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
                
                  <v-btn class="button" @click="confirmAuthNum">확인</v-btn>
                
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
              </div>
            <!-- </v-card> -->
            <br />
            <v-row class="d-flex justify-end">
              <v-btn
                variant="tonal"
                type="button"
                @click="registAccount"
                class="btn-close"
                style="margin-right: 30px"
                >등록</v-btn
              >
            </v-row>
          </div>

                                <div v-if="isRegistered">
                                    <p>계좌가 등록되었습니다!</p>
                                </div>
                            </v-card>
                        </v-col>   
                    </v-row>
                 </v-container>
            </v-content>
        </v-app>  
    </div>
</template>
<script>
import AppBar from '../views/AppBar.vue';
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

        user: {
        accountId: '',
        bankName:'',
        accountNum:'',
        authNumConfirm:'',
        accountNickname:'',
        accountLimit:'',
        },
      };

      
    },
}
</script>
<style>
    .modal-bg2{
        background-color: white;
    }
    .no-shadow {
        box-shadow: none !important;
    }
    .button {
        margin-right: 1000px;
    }
    .card{
        margin-left:150px;
    }
</style>
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
                                <div class="modal-bg3">
            <h4
              class="v-card__title text-center"
              v-bind:style="{ color: 'gray' }"
            >
              계좌 삭제
            </h4>
            <!-- <v-card> -->
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
            <!-- </v-card> -->
            <br />
            <v-row class="d-flex justify-end">
              <v-btn
                variant="tonal"
                type="button"
                @click="deleteAccount"
                class="btn-close"
                style="margin-right: 30px"
                >삭제</v-btn
              >
              <v-btn
                variant="tonal"
                type="button"
                @click="modal3 = false"
                class="btn-close"
                >닫기</v-btn
              >
            </v-row>
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
    .modal-bg3{
        background-color: white;
    }
</style>

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
                                <v-main style="height: 850px;"></v-main>
                            </v-card>
                        </v-col> 
                       
                        <v-col cols="5" style="height: 1000px;">
                            <div class="background">
                            <h4 class="title">비밀번호 변경</h4>
                                <v-col cols="7">
                                <v-text-field
                                    label="현재 비밀번호"
                                    id="password"
                                    v-model="password"
                                    required
                                    outlined
                                    class="t-field"
                                ></v-text-field>
                                </v-col>

                                <v-col cols="7">
                                <v-text-field
                                    label="새로운 비밀번호"
                                    id="newPassword"
                                    v-model="newPassword"
                                    required
                                    outlined
                                    class="t-field"
                                ></v-text-field>
                                </v-col>

                                <v-col cols="7">
                                <v-text-field
                                    label="비밀번호 확인"
                                    id="passwordConfirm"
                                    v-model="passwordConfirm"
                                    required
                                    outlined
                                    class="t-field"
                                ></v-text-field>
                                </v-col>

                                <v-btn variant="tonal" type="button" @click="changePassword" class="btn-change"
                                >비밀번호 변경</v-btn>
                            </div>
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
        { title: "회원정보 수정", to: "/changeinfo", icon: "mdi-pencil" },
        { title: "비밀번호 변경", to: "/delete-password", icon: "mdi-lock" },
        { title: "회원탈퇴", to: "/memberresign", icon: "mdi-close" }

        // 다른 아이템을 필요에 따라 추가할 수 있습니다.
      ],

        user: {
        password: '',
        newpassword: '',
        passwordConfirm: '',
        },
      };

      
    },

    
    methods: {
    async changePassword() {
      // 현재 비밀번호, 새로운 비밀번호, 비밀번호 확인을 가져옴
      const currentPassword = this.password;
      const newPassword = this.newPassword;
      const confirmPassword = this.passwordConfirm;

      // 여기에서 비밀번호 변경 로직을 구현합니다.
      // 예를 들어, 서버로 요청을 보내고 비밀번호 변경을 처리합니다.
      try {
        const response = await axios.post('/api/changePassword', {
          currentPassword,
          newPassword,
          confirmPassword,
        });

        if (response.status === 200) {
          alert('비밀번호가 성공적으로 변경되었습니다.');
          this.modal4 = false; // 모달을 닫는다거나 다른 작업을 수행할 수 있음
        } else {
          alert('비밀번호 변경에 실패했습니다.');
        }
      } catch (error) {
        console.error('비밀번호 변경 중 오류 발생:', error);
        alert('비밀번호 변경 중 오류 발생');
      }
    },
  },
}
</script>
<style>
    .userInfo{
        margin-top: 0px;
    }

    .background {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            
            background: white;
            border-radius: 8px;
            width: 790px;

            padding-top:30px;
            padding-bottom:20px;
            
        }
</style>

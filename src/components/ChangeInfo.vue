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
                            <v-card>
                            <v-main class="userInfo">
                                <h2>회원정보 수정</h2>
                                <v-form @submit.prevent="signUpUser">
                                    
                                    
                                    <v-row justify="center" class="mt-8">
                                    <v-col cols="2" md="200">
                                        <!--<v-label for="memberId">아이디 </v-label>-->
                                        <v-text-field label="아이디" id="memberId" v-model="user.memberId" required outlined disabled></v-text-field>
                                    </v-col>
                                    <!-- <v-btn variant="tonal" class="button" type="button" @click="checkDuplicateId">
                                    중복확인
                                    </v-btn> -->
                                    </v-row>
                                    

                                    <v-row justify="center" class="pa-0">
                                    <!--<label for="password">비밀번호 </label>-->
                                    <v-col cols="2" >
                                        <v-text-field label="비밀번호" id="password" v-model="user.password" required outlined></v-text-field>
                                    </v-col>
                                    </v-row>

                                    <v-row justify="center" class="pa-0">
                                    <!-- <label for="passwordCheck">비밀번호 확인</label> -->
                                    <v-col cols="2" >
                                        <v-text-field label="비밀번호 확인" id="passwordCheck" v-model="user.passwordCheck" required outlined></v-text-field>
                                    </v-col>
                                    </v-row>

                                    <div v-if="!isPasswordMatching">
                                    <p>비밀번호와 비밀번호 확인이 일치하지 않습니다. 다시 확인하세요.</p>
                                    </div>       

                                    <v-row justify="center" class="pa-0">
                                    <!-- <label for="username">이름</label> -->
                                    <v-col cols="2" >
                                        <v-text-field label="이름" id="username" v-model="user.username" required outlined></v-text-field>
                                    </v-col>
                                    </v-row>

                                    <v-row justify="center" class="pa-0">
                                    <!-- <label for="phone">전화번호</label> -->
                                    <v-col cols="2" >
                                        <v-text-field label="전화번호" id="phone" v-model="user.phone" required outlined @input="formatPhoneNumber"></v-text-field>
                                    </v-col>
                                    </v-row>

                                    <v-row justify="center" class="pa-0">
                                    <!-- <label for="phone">전화번호</label> -->
                                    <v-col cols="2" class="email-input">
                                        <v-text-field label="이메일" id="email" v-model="user.email" required outlined></v-text-field>
                                    </v-col>
                                    <v-col cols="1" class="button-col">
                                        <v-btn variant="tonal" type="button" @click="checkDuplicateEmail">
                                    중복확인
                                    </v-btn>
                                    </v-col>
                                    </v-row>
                                    

                                    <v-row justify="center" class="pa-0">
                                    <!-- <label for="address">주소</label> -->
                                    <v-col cols="2" >
                                        <v-text-field label="주소" id="address" v-model="user.address" required outlined></v-text-field>
                                    </v-col>
                                    </v-row>

                                    

                                    <v-row justify="center" class="ma-2">
                                    <v-col cols="30" md="30">
                                    <v-radio-group v-model="user.sex" row>
                                    <v-label for="sex">성별 </v-label>
                                    <v-radio label="남성" value="1" checked></v-radio>
                                    <v-radio label="여성" value="2"></v-radio>
                                    </v-radio-group>
                                    </v-col>
                                    </v-row>


                                    <v-row justify="center" class="ma-2">
                                    <v-col cols="30" md="30">
                                    <v-radio-group v-model="user.messageOk" row>
                                    <v-label for="messageOk">메시지 수신 동의 </v-label>
                                    <v-radio label="동의" value="1" checked></v-radio>
                                    <v-radio label="비동의" value="2"></v-radio>
                                    </v-radio-group>
                                    </v-col>
                                    </v-row>

                                    <v-btn variant="tonal" type="submit">
                                    정보수정
                                    </v-btn>
                                </v-form>
                                </v-main>
                                <div v-if="isRegistered">
                                    <p>정보 수정이 완료되었습니다. 환영합니다!</p>
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
        { title: "회원정보 수정", to: "/changeinfo", icon: "mdi-pencil" },
        { title: "비밀번호 변경", to: "/delete-password", icon: "mdi-lock" },
        { title: "회원탈퇴", to: "/memberresign", icon: "mdi-close" }

        // 다른 아이템을 필요에 따라 추가할 수 있습니다.
      ],

        user: {
          memberId:'',
          password: '',
          passwordCheck:'',
          username: '',
          email: '',
          phone: '',
          address:'',
          sex:'',
          messageOk:'1',
        },
        isRegistered: false
      };

      
    },

    computed: {
    isPasswordMatching() {
      return this.user.password === this.user.passwordCheck;
    },
  },
  created(){
    this.fetchMemverInfo();
  },
  methods:{
    async fetchMemverInfo(){
        try{
            const apiUrl = 'api/member-info'; // 이부분을 실제 엔드포인트 url로 수정해야 함

            const response= await axios.get(apiUrl);

            const memberInfo = response.data;
            this.user.memberId = memberInfo.user_id;
            this.user.password = memberInfo.user_password;
            this.user.username = memberInfo.user_name;
            this.user.email = memberInfo.user_email;
            this.user.phone = memberInfo.user_phone;
            this.user.address = memberInfo.user_address;

            if (memberInfo.user_gender === '남성') {
            this.user.sex = '1';
            } else if (memberInfo.user_gender === '여성') {
            this.user.sex = '2';
            }

            this.user.messageOk = memberInfo.user_message_consent ? '1' : '2';


        }catch(error){
            console.error('회원 정보를 가져오는 중 오류 발생:',error);
        }
    },
    signUpUser(){
        console.log('회원 정보 수정 데이터:', this.user);
    },
  },
}
</script>
<style>
    .userInfo{
        margin-top: 0px;
    }
</style>

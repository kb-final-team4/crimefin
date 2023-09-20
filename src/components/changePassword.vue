<!-- 찐코드 // 마이페이지 비밀번호 수정-->
<template>
  <div>
    <v-navigation-drawer app>
      <v-list>
        <v-list-item>
          <v-list-item-avatar>
            <v-img src="logo.png"></v-img>
          </v-list-item-avatar>
          <v-list-item-title>CrimeFin</v-list-item-title>
        </v-list-item>

        <v-divider></v-divider>

        <v-list-item link to="/changeinfo">
        <v-list-item-icon>
          <v-icon>mdi-account</v-icon>
        </v-list-item-icon>
        <v-list-item-title class="larger-text">회원정보 변경</v-list-item-title>
      
      </v-list-item>

        <v-list-item link to="/changepassword">
          <v-list-item-icon>
            <v-icon>mdi-lock</v-icon>
          </v-list-item-icon>
          <v-list-item-title class="larger-text">비밀번호 수정</v-list-item-title>
        </v-list-item>

        <v-list-item link to="/memberresign">
          <v-list-item-icon>
            <v-icon>mdi-cog</v-icon>
          </v-list-item-icon>
          <v-list-item-title class="larger-text">회원탈퇴</v-list-item-title>
        </v-list-item>
        <!-- 추가 메뉴 아이템 작성 -->
      </v-list>
    </v-navigation-drawer>
    <div class="black-bg4" v-if="modal4 == true">
      <div class="white-bg4">
        <h4 class="title">비밀번호 변경</h4>
        <v-col cols="8">
          <v-text-field
            label="현재 비밀번호"
            id="password"
            v-model="password"
            required
            outlined
            class="t-field"
          ></v-text-field>
        </v-col>

        <v-col cols="8">
          <v-text-field
            label="새로운 비밀번호"
            id="newPassword"
            v-model="newPassword"
            required
            outlined
            class="t-field"
          ></v-text-field>
        </v-col>

        <v-col cols="8">
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
          >비밀번호 변경</v-btn
        >
      </div>
    </div>
    <app-bar />
  </div>
</template>
<script>
import axios from 'axios';
import AppBar from '../views/AppBar.vue';

export default {
  components: {
    'app-bar': AppBar, // 상단바 컴포넌트를 등록합니다.
  },

  name: 'App',
  data() {
    return {
      modal4: true,
      password: '',
      newpassword: '',
      passwordConfirm: '',
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
};
</script>
<style>
.form {
  margin: 0 auto;
}
body {
  margin: 0;
}
div {
  box-sizing: border-box;
}
.black-bg4 {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;
}
/* .white-bg4 {
        align-items: center;
        justify-content: center;
        display: flex;
        flex-direction: column;
        background: white;
        border-radius: 8px;
    }    */

.white-bg4 {
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

.t-field {
  display: flex;
  justify-content: center;
  text-align: center;
}

v-container {
  flex: 1;
}

/*  .white-bg4 {
        position: fixed;
        top: 50%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        -moz-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        -o-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        align-items: center;
        justify-content: center;
    background: white;
    border-radius: 8px;
    padding: 20px;
    padding-left: 200px; 
    margin-left:700px; } */

#name {
  position: absolute;
  top: 30px;
  bottom: 50px;
}
</style>

<!-- 클릭하면 열려야 할 곳에 @click="modal==true" 넣기 -->

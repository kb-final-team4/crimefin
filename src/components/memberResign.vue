<!-- 마이페이지 속 회원탈퇴-->
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
          <v-list-item-title class="larger-text">회원정보 수정</v-list-item-title>
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

    <div class="black-bg4" v-if="modal4 == true"></div>
    <div class="white-bg6" v-if="modal6 == true">
      <h4 class="title6">탈퇴하시겠습니까?</h4>
      <v-col cols="8">
        <v-text-field
          label="비밀번호 입력"
          id="password"
          v-model="password"
          class="password-form"
          required
          outlined
        ></v-text-field>
      </v-col>
      <v-btn variant="tonal" type="button" @click="confirmDelete" class="btn-delete"
        >회원 탈퇴</v-btn
      >
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
  data() {
    return {
      members: [],
      password: '',
      modal4: true,
      modal6: true,
    };
  },

  mounted() {
    // 백엔드 API 엔드포인트 URL
    const apiUrl = 'https://your-backend-api.com/members';

    // Axios를 사용하여 API 호출
    axios
      .get(apiUrl)
      .then(response => {
        // API 응답으로부터 회원 데이터를 가져와 members에 할당
        this.members = response.data;
      })
      .catch(error => {
        console.error('회원 데이터를 가져오는 중 오류 발생:', error);
      });
  },

  methods: {
    confirmDelete() {
      const passwordToCheck = this.password;

      const member = this.members.find(member => member.password === passwordToCheck);

      if (!member) {
        alert('회원을 찾을 수 없습니다.');
        return;
      }

      if (member.password !== passwordToCheck) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      const index = this.members.indexOf(member);
      this.members.splice(index, 1);
      alert('회원이 성공적으로 탈퇴되었습니다.');
    },
  },
};
</script>

<style>
.black-bg4 {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;
}

.white-bg6 {
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
}

/* .white-bg6 {
    position:absolute;
    top:400px;
    width: 35%; 
    background: white;
    border-radius: 8px;
    padding: 20px;
    padding-left: 200px; 
    margin-left:700px;
    }  */

/*     
    .btn-change{
    margin-left:110px;
  }
  .title6{
    margin-left:110px;
    
  }
  .btn-delete{
    position:absolute;
    left:120px;
  }

  .password-form{
    position:absolute;
    top:15px;
  } */
</style>

<template>
  <div>
  <h2>회원탈퇴</h2>
    
        <label for="password">비밀번호 입력</label>
        <input type="password" id="password" v-model="password" required>
        <button @click="confirmDelete">회원 탈퇴</button>

    </div>

</template>

<script>
import axios from 'axios';
export default {
  data(){
    return{
      members:[],
      password:'',
    };
  },

  mounted() {
    // 백엔드 API 엔드포인트 URL
    const apiUrl = 'https://your-backend-api.com/members';

    // Axios를 사용하여 API 호출
    axios.get(apiUrl)
      .then(response => {
        // API 응답으로부터 회원 데이터를 가져와 members에 할당
        this.members = response.data;
      })
      .catch(error => {
        console.error('회원 데이터를 가져오는 중 오류 발생:', error);
      });
  },

  methods:{
    confirmDelete(){
      const passwordToCheck = this.password;

      const member = this.members.find(member=>member.password===passwordToCheck)

      if(!member){
        alert('회원을 찾을 수 없습니다.');
        return;
      }

      if(member.password!==passwordToCheck){
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      const index = this.members.indexOf(member);
      this.members.splice(index,1);
      alert('회원이 성공적으로 탈퇴되었습니다.');
    },

  },

};

</script>

<style>

</style>
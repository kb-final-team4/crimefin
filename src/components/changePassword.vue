<template>
    <div class="menu">
        <img alt="Vue logo" src="../assets/CRIME.png">
        <a v-for="a in menuBar" :key="a">{{ a}}</a>
    
      
      <h2>비밀번호 변경</h2>
    <form @submit.prevent="changePassword">  
      
      
      <div>
        <label for="password">현재 비밀번호</label>
        <input type="password" id="password" v-model="user.password" required>
      </div>
      <div>
        <label for="newPassword">새로운 비밀번호</label>
        <input type="password" id="newPassword" v-model="user.newPassword" required>
      </div>
      <div>
        <label for="passwordConfirm">비밀번호 확인</label>
        <input type="password" id="passwordConfirm" v-model="user.passwordConfirm" required>

      </div>
      <div>
        <button type="submit">비밀번호 변경</button>
      </div>
    </form>
    </div>
    
  
</template>


<script>
import axios from 'axios';
export default {
  data() {
    return {
      menuBar:['CRIME FIN?', '경찰서/은행 찾기', '디지털 체험관'],
      user: {
        password: '',
        newPassword:'',
        passwordConfirm:'',
      },
      isRegistered: false
    };
  },
  methods: {
   async changePassword(){
    try{
      if(this.user.newPassword !== this.user.passwordConfirm){
        alert('새 비밀번호와 확인 비밀번호가 일치하지 않습니다.');
        return;
      }

      const passwordData = {
        password : this.user.password,
        newPassword : this.user.newPassword,
      };

      const response = await axios.post('api/change-password', passwordData); // 백엔드랑 연결할 부분

      if(response.status===200){
        alert('비밀번호 변경 성공');
        this.$router.push('/login');
      }else{
        alert('비밀번호 변경 실패');
      }
    }catch(error){
      console.error('비밀번호 변경 중 오류 발생:', error);
      alert('비밀번호 변경 중 오류 발생');
    }
   },
  },
};
</script>

<style>
.menu{
  background: #000080;
  padding: 15px;
  border-radius: 5px;
}

.menu a{
  color: white;
  padding: 10px;
}
</style>

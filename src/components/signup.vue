<template>
  <div>
    <div class="menu">
      <img alt="crimefin logo" src="../assets/CRIME.png">
      <a v-for="a in menuBar" :key="a">{{ a}}</a>
    </div>
  
    
      <h2>회원가입</h2>
      <form @submit.prevent="signUpUser">
          <div>
          <label for="memberId">아이디 </label>
          <input type="text" id="memberId" v-model="user.memberId" required>
          <button type="submit">중복확인</button>
        </div>
        <div>
          <label for="password">비밀번호</label>
          <input type="password" id="password" v-model="user.password" required>
        </div>
        <div>
          <label for="passwordCheck">비밀번호 확인</label>
          <input type="password" id="passwordCheck" v-model="user.passwordCheck" required>
        </div>
        <div>
          <label for="username">이름 </label>
          <input type="text" id="username" v-model="user.username" required>
        </div>
        <div>
          <label for="email">이메일</label>
          <input type="email" id="email" v-model="user.email" required>
          <button type="submit">중복확인</button>
        </div>
        <div>
          <label for="phone">전화번호</label>
          <input type="phone" id="phone" v-model="user.phone" required>
        </div>
        <div>
          <label for="address">주소</label>
          <input type="address" id="address" v-model="user.address" required>
        </div>
        <div>
          <label for="sex">성별</label>
          <input type="radio" name="sex" value="1" checked> 남자
          <input type="radio" name="sex" value="2" > 여자
        </div>
        <div>
          <label for="messageOk">메시지 수신 동의</label>
          <input type="radio" name="messageOk" value="1" checked> 동의
          <input type="radio" name="messageOk" value="2" > 비동의
        </div>
        <div>
          <button type="submit">가입하기</button>
        </div>
      </form>
      <div v-if="isRegistered">
        <p>가입이 완료되었습니다. 환영합니다!</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        menuBar:['CRIME FIN?', '경찰서/은행 찾기', '디지털 체험관'],
        formData: {
          memberId:'',
          password: '',
          passwordCheck:'',
          username: '',
          email: '',
          phone: '',
          address:'',
          sex:'',
          messageOk:'',
        },
        isRegistered: false
      };
    },
    methods: {
      validateMemberId(){
        const pattern=/^[a-zA-Z]+$/; // 영문자만 허용하는 정규식 패턴

        if(!pattern.test(this.memberId)){
          alert('아이디는 영문으로만 입력하세요.');
          this.memberId=''; // 입력 필드 비우기
        }
      },

      async signUpUser() {
        // 여기에서 회원가입 로직을 구현합니다.
        // 예를 들어, 사용자 데이터를 서버에 전송하고 응답을 처리하는 등의 작업을 수행합니다.
        try{
          const joinUser={
            memberId: this.user.memberId,
            password: this.user.password,
            passwordCheck:this.user.passwordCheck,
            username: this.user.username,
            email: this.user.email,
            phone: this.user.phone,
            address:this.user.address,
            sex:this.user.sex,
            messageOk:this.user.messageOk,
          };
  
        const response = await axios.post('/api/users', joinUser); //api확인
  
        if(response.status ===200){
          alert('회원 가입 성공');
          this.$router.push('/login');
        }else{
          alert('회원 가입 실패');
        }
        }catch(error){
          console.error('회원가입 중 오류 발생:',error);
          alert('회원가입 중 오류 발생');
        }
        // 가입이 성공했다고 가정하면:
       // this.isRegistered = true;
  
        // 가입이 완료되었다는 메시지를 몇 초 후에 숨깁니다.
        //setTimeout(() => {
        //  this.isRegistered = false;
        //}, 5000);
      }
    }
  };
  </script>
  
  <style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  
  .menu{
    background: #000080;
    padding: 15px;
    border-radius: 5px;
  }
  
  .menu a{
    color: white;
    padding: 10px;
  }
  
  /*#memberId{
    box-sizing: border-box;
  
  position: absolute;
  width: 554px;
  height: 69px;
  left: 481px;
  top: 206px;
  
  background: #FFFFFF;
  border: 2px solid #021C55;
  }*/
  </style>
  
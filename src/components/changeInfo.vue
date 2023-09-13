<template>
    <div class="menu">
      <img alt="crimefin logo" src="../assets/CRIME.png">
      <a v-for="a in menuBar" :key="a">{{ a}}</a>
   
      <h2>회원정보 수정</h2>
      <form @submit.prevent="changeInfo">
          <div>
          <label for="memberId">아이디 </label>
          <input type="text" id="memberId" v-model="user.memberId" required>
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
          <button @click="updateUserInfo">정보수정</button>
        </div>
      </form>
      <div v-if="isRegistered">
        <p>회원정보가 수정되었습니다.</p>
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

    mounted(){
      this.getUserInfo();
    },

    methods: {
      getUserInfo(){
        const apiUrl='https://api.example.com/user'; //url 변경해야 함

        axios
        .get(apiUrl)
        .then((response)=>{
          this.user = response.data;
        })
        .catch((error)=>{
          console.error('회원 정보 가져오기 실패:',error);
        });

      },
      updateUserInfo(){
          const apiUrl = "https://api.example.com/user"; //url 변경해야 함
          
          axios
          .put(apiUrl, this.user)
          .then((response)=>{
            console.log('회원 정보가 성공적으로 수정되었습니다');
          })
          .catch((error)=>{
            console.error('회원 정보 업데이트 실패:', error);

          });
      }
      },

     
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
  
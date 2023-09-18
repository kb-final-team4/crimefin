<template>
  <v-app>
    <AppBar fixed/>
    <div>
    <!-- <div class="menu">
      <img alt="crimefin logo" src="../assets/CRIME.png">
      <a v-for="a in menuBar" :key="a">{{ a}}</a>
    </div> -->



      <v-main>
      <!--<h2>회원가입</h2>-->
      <v-form @submit.prevent="registerUser">
        <v-row justify="center" class="mt-8">
          <v-col cols="2" md="200">
            <!--<v-label for="memberId">아이디 </v-label>-->
            <v-text-field label="아이디" id="memberId" v-model="user.memberId" required outlined ></v-text-field>
          </v-col>
          <v-btn variant="tonal" class="button" type="button" @click="checkDuplicateId">
          중복확인
        </v-btn>
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
    </v-row>
    

    <v-row justify="center" class="pa-0">
      <!-- <label for="address">주소</label> -->
      <v-col cols="2" >
        <input type="text" placeholder="우편번호" v-model="zonecode" readonly/>
        <button type="button" id="postcode" @click="openPostcode">검색</button><br>
        <input type="text" v-model="user.address" placeholder="주소" readonly/> 
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
      가입하기
    </v-btn>
  </v-form>
</v-main>
  <div v-if="isRegistered">
    <p>가입이 완료되었습니다. 환영합니다!</p>
  </div>
</div>
  </v-app>


    
    
  </template>

  <script>
  import axios from 'axios';
  import AppBar from '../views/AppBar.vue'; // 상단바 컴포넌트를 import 합니다.

  export default {
    components: {
      AppBar, // 상단바 컴포넌트를 등록합니다.
    },
    name:'App',
    data() {
      return {
        menuBar:['CRIME FIN?', '경찰서/은행 찾기', '디지털 체험관'],
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
        isRegistered: false,
        zonecode: ""
      };
    },

computed: {
isPasswordMatching() {
  return this.user.password === this.user.passwordCheck;
},
  },

methods: {
  async checkDuplicateId(){
    try{
      const memberId=this.user.memberId;

      //const response=await axios.post('api/check-duplicate',{memberId});
      axios.get('http://localhost:9999/duplicate', {
        params:{
          memberId : memberId,
        },
      })
        .then((response)=>{
          console.log(response);

          if(response.data=="0"){
            alert('사용가능한 아이디입니다.');
          }else{
            alert('아이디가 이미 사용 중입니다.');
          }
        })
    }catch(error){
      console.error('중복 확인 중 오류 발생:',error);
      alert('중복 확인 중 오류 발생');
    }
  },
  openPostcode(){
      console.log(1);
      new window.daum.Postcode({
        oncomplete:(data)=>{
          console.log(data);
          this.zonecode=data.zonecode;
          this.user.address=data.address;
        }
      }).open();
  },
  registerUser(){
      var joinUser = {
          memberId: this.user.memberId,
          password: this.user.password,
          passwordCheck: this.user.passwordCheck,
          username: this.user.username,
          email: this.user.email,
          phone: this.user.phone,
          address: this.user.address,
          sex: this.user.sex,
          messageOk: this.user.messageOk,
        };
      
      console.log(joinUser);
      var url="http://localhost:9999/user";
      
      axios
        .post(url, joinUser)
        .then((response)=>{
          console.log(response);
          console.log(1);
          this.$router.push({name: "Login"}); //로그인 페이지로 이동
        })
        .catch((error)=>{
          console.log("에러 발생");
        });
    },

    formatPhoneNumber(){
    let phoneNumber = this.user.phone.replace(/[^0-9]/g, '');
    // 원하는 전화번호 형식으로 가공 (예: 010-1234-5678)
    if (phoneNumber.length >= 4 && phoneNumber.length <= 7) {
            phoneNumber = phoneNumber.replace(/(\d{3})(\d+)/, '$1-$2');
          } else if (phoneNumber.length >= 8) {
            phoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d+)/, '$1-$2-$3');
          }

    // 가공한 전화번호를 다시 입력 필드에 설정
    this.user.phone = phoneNumber;
    },
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

  .text-center{
    align-items: center;
  }

.email-input {
  display: flex;
  align-items: center; 
}

.button-col {
  display: flex;
  align-items: center; 
  margin-left: 10px; 
  margin-right: -190px;
  margin-bottom: 45px;
}

.button{
  margin-left: 10px; 
  margin-right: -110px;
  margin-bottom: -45px;
  margin-top: 20px;
}

</style>
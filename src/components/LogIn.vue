<template>
  <v-container fluid class="d-flex flex-column align-center justify-center h-100">
    <v-row justify="center" align="center" >
      <v-col width="50%" height="80%">
        <v-card >
          <v-card-title class="text-center" >
            <div id="logo" height="50px"><v-img src="../assets/CRIME.png"></v-img></div>
            <!-- <h2 class="mb-4">LOGIN</h2> -->
          </v-card-title>

          <v-card-text>

            <v-form @submit.prevent="login" class="mx-auto" max-width="80%">
              <v-text-field
                v-model="memberId"
                label="아이디"
                outlined
                dense
                required
              ></v-text-field>
              <v-text-field
                v-model="password"
                label="비밀번호"
                outlined
                dense
                required
                type="password"
              ></v-text-field>
              <v-btn
                type="submit"
                color="primary"
                class="mt-4"
                block
                outlined
                depressed
              >
                로그인
              </v-btn>
              <!-- 임시 로그인 버튼 -->
              <router-link to="/main"><v-btn
                color="primary"
                class="mt-4"
                block
                outlined
                depressed
              >
                로그인
              </v-btn></router-link>
            </v-form>
          </v-card-text>
          <v-card-actions class="justify-center">
            <v-btn text color="primary" class="mr-2" @click="modal=true">아이디 찾기</v-btn><br>
          </v-card-actions>
          <v-card-actions class="justify-center">
            <v-btn text color="primary" class="mr-2" @click="modal2=true">비밀번호 찾기</v-btn><br>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>

    <div class="black-bg" v-if="modal==true" >
            <div class="white-bg">
                <h4 class="title">아이디 찾기</h4>
                <v-col cols="8" >
                <v-text-field label="이름" id="name" v-model="name" required outlined></v-text-field>
                </v-col>
                <v-col cols="8" >
                <v-text-field label="전화번호" id="phone" v-model="phone" required outlined></v-text-field>
                </v-col>
                <v-row class="d-flex justify-center">
                <v-btn variant="tonal" type="button" @click="findUserId" class="btn-findId" style="margin-right:30px;">아이디 찾기</v-btn>
                <v-btn variant="tonal" type="button" @click="modal=false" class="btn-close">닫기</v-btn>
              </v-row>
              </div>
        </div>

        <div class="black-bg2" v-if="modal2==true" >
            <div class="white-bg2">
                <h4 class="title2">비밀번호 찾기</h4>
                <v-col cols="8" >
                <v-text-field label="아이디" id="id" v-model="memberId" required outlined></v-text-field>
                </v-col>
                <v-col cols="8" >
                <v-text-field label="전화번호" id="phone" v-model="phone" required outlined></v-text-field>
                <v-btn variant="tonal" type="button" @click="requestVerificationCode" class="btn-verificate" >인증번호 받기</v-btn>
                </v-col>
                <v-col cols="8" >
                <v-text-field label="인증번호 입력" id="verification" class="verification" v-model="verification" required outlined></v-text-field>
                <v-btn variant="tonal" type="button" @click="confirmVerficationCode" class="btn-permit" style="margin-right:30px;">확인</v-btn>
                <v-btn variant="tonal" type="button" @click="modal2=false" class="btn-close2">닫기</v-btn>
                </v-col>
                <!-- 실제 실행시킬 떄는 @click="navigate" 로 바꾸기 -->
            </div>
        </div>

        <div class="black-bg-success" v-if="showSuccessModal" @click="showSuccessModal = false">
          <div class="white-bg-success">
            <h4 class="title-success">아이디 찾기 성공</h4>
            <p>아이디: {{ foundUserId }}</p>
            <v-btn variant="tonal" type="button" @click="closeSuccessModal" class="btn-close-success">닫기</v-btn>
          </div>
        </div>

        <div class="black-bg4" v-if="modal4==true" >
            <div class="white-bg4">
                <h4 class="title">비밀번호 변경</h4>
                <v-col cols="8" >
                <v-text-field label="아이디" id="id" v-model="memberId" required outlined></v-text-field>
                </v-col>

                <v-col cols="8" >
                <v-text-field label="새로운 비밀번호" id="newPassword" v-model="newPassword" required outlined></v-text-field>
                </v-col>

                <v-col cols="8" >
                <v-text-field label="비밀번호 확인" id="passwordConfirm" v-model="passwordConfirm" required outlined></v-text-field>
                </v-col>

                <v-btn variant="tonal" type="button" @click="changePassword" class="btn-change">비밀번호 변경</v-btn>

            </div>
        </div>

  </v-container>




</template>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      memberId: '',
      password: '',
      modal : false,
      modal2: false,
      name:'',
      phone:'',
      userId:'',
      verification:'',
      foundUserId: '', // 아이디 찾기 성공 후 표시할 아이디
      showSuccessModal: false, // 성공 모달 창을 보여주기 위한 상태 변수
      modal4 : false,
      password:'',
      newpassword:'',
      passwordConfirm:'',
    };
  },

  methods: {
    async changePassword() {
      // 현재 비밀번호, 새로운 비밀번호, 비밀번호 확인을 가져옴
      const memberId = this.memberId;
      const newPassword = this.newPassword;
      const confirmPassword = this.passwordConfirm;

      // 여기에서 비밀번호 변경 로직을 구현합니다.
      // 예를 들어, 서버로 요청을 보내고 비밀번호 변경을 처리합니다.
      try {
        const response = await axios.put('http://localhost:9999/user/pwd', {
          memberId,
          newPassword,
          confirmPassword,
        });
        console.log(memberId);
        if (response.status === 200) {
          alert('비밀번호가 성공적으로 변경되었습니다.');
          this.modal4 = false; // 모달을 닫는다거나 다른 작업을 수행할 수 있음
          this.modal2=false;
        } else {
          alert('비밀번호 변경에 실패했습니다.');
        }
      } catch (error) {
        console.error('비밀번호 변경 중 오류 발생:', error);
        alert('비밀번호 변경 중 오류 발생');
      }
        },


    async findUserId() {
        axios.get('http://localhost:9999/user', {
          params:{
            name: this.name,
            phone: this.phone,
          },
        })
          .then((response)=>{
            if (response.status === 200) {
              this.userId = response.data;
              console.log(this.userId);
              // 아이디를 찾았을 때 foundUserId에 값을 설정하고 모달 창을 보이도록 상태 변수 설정
              this.foundUserId = this.userId;
              this.showSuccessModal = true;
              this.modal=false;
            }
            else {
              alert('아이디를 찾을 수 없습니다.');
            }
            console.log('아이디 찾기 성공', response);
          })
          .catch((error)=>{
            console.error('아이디찾기 오류', error);
          });
    },
    


    // 모달 창을 닫는 메서드
    closeSuccessModal() {
      this.showSuccessModal = false;
    },

    async navigate(){
      if(this.verification==this.verificationcode){
        this.modal4 = true;
      }else{
        alert('인증번호가 일치하지 않습니다.');
      }
    },

    //인증 번호 요청
    async requestVerificationCode(){
      try{
        const response = await axios.post('http://localhost:9999/user/sendNum',{
          memberId: this.memberId,
          phone:this.phone,
        },
        {
          withCredentials:true
        });

        if(response.status===200){
          console.log(response);
          //this.memberId = memberId;
          console.log("아이디는 " + this.memberId);
          alert('인증번호를 받았습니다:'+this.verificationcode);
        }else{
          alert('인증번호를 받을 수 없습니다.');
        }

      }catch(error){
        console.error('인증번호 요청 중 오류 발생:',error);
        alert('인증번호 요청 중 오류 발생');
      }
    },
    //인증번호 확인
    async confirmVerficationCode() {
        axios.get('http://localhost:9999/user/checkNum', {
          params:{
            authNum:this.verification,
          },
          withCredentials:true
        })
          .then((response)=>{
            if (response.status === 200) {
              alert('인증 번호 확인 성공');
              // 아이디를 찾았을 때 foundUserId에 값을 설정하고 모달 창을 보이도록 상태 변수 설정
              //this.foundUserId = this.userId;
              this.modal4=true;
            }
            else {
              alert('인증번호가 틀립니다');
            }
            console.log('인증번호 확인 성공', response);
          })
          .catch((error)=>{
            console.error('인증번호 찾기 오류', error);
          });
    },
    
    login() {
      console.log(this.memberId, this.password);
      var url = "http://localhost:9999/user/login";
      var data = {
        memberId: this.memberId,
        password: this.password
      };
      
      axios.post(url, data)
        .then(response => {
          console.log(response);

          // 로그인 성공 시 MainPage.vue로 이동
          if (response.data.memberId) {
            var name = response.data.name
            this.$router.push({ name: 'MainPage'  , params: {name}}); // 이동할 페이지의 이름, 이름 넘겨주기
            this.$session.set('loginMemberId', response.data.memberId);
            this.$session.set('loginMemberEmail', response.data.email);
            this.$session.set('loginMemberAddress', response.data.address);
            this.$session.set('loginMemberName', response.data.name);
            this.$session.set('loginMemberPhone', response.data.phone);
            this.$session.set('loginMemberMessageOk', response.data.messageOk);
          }
        })
        .catch(error => {
          console.error(error);
        });
    }
  }
};
</script>


<style scoped>
.logo{
  display: inline-block;
}

.d-flex {
  display: flex;
}

.flex-column {
  flex-direction: column;
}

.align-center {
  align-items: center;
}

.justify-center {
  justify-content: center;
}

.h-100 {
  height: 100vh;
}


body{
        margin:0;
    }
    div{
        box-sizing: border-box;
    }
  .black-bg {
    width: 100%; 
  height:100%;
  background: rgba(0,0,0,0.5);
  position: fixed; 
  padding: 20px;
}

.black-bg2{
  width: 100%; 
  height:100%;
  background: rgba(0,0,0,0.5);
  position: fixed; 
  padding: 20px;

}

nav{
  text-align: center;
}
.white-bg {
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

      padding-bottom: 30px;
      padding-top:30px;
}

.white-bg-success{
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

.white-bg2 {


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

    padding-top:20px;

} 


.black-bg-success {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;
  z-index: 9999; 
}


#name{
    position: absolute;
    top:30px;
    bottom:50px;
}


.black-bg4 {
    width: 100%; height:100%;
    background: rgba(0,0,0,0.5);
    position: fixed; padding: 20px;
    }
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
    }

.title{
    justify-content: center;
}


#name{
    position: absolute;
    top:30px;
    bottom:50px;
}




</style>
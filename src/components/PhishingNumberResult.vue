<template>
  <v-card-text :value="showResultModal" @input="$emit('update:showResultModal', $event)">
  <!--<v-card-text v-model="showResultModal" class="total">-->

    <!-- 보이스 피싱 :: 스팸 번호 -->
    <div v-if="phishingtype == 'Phone' && responsedata">
      <v-col >
        <div class="resultTitle">전화번호 조회 결과</div>
      </v-col>

      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div v-if="resultData == 1">
            <img src="../assets/danger.png" alt="Danger Icon" class="icon">
            <p>번호 조회 결과, 해당 번호는 스팸 번호로 등록되어 있습니다. <br><b>위험한 번호</b>로 판단됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              보이스피싱 범죄 관련<br> <b>대응 메뉴얼</b>을 제공해드리고 있어요.<br>확인하시겠어요?<br>
              <v-btn @click="navigateToManual(phishingtype)"  color="#14274E">메뉴얼 보기</v-btn>
            </p>
            <p>
              통화 내용을 분석해드립니다. <br><b>AI로 더 조회</b>해 보시겠어요?<br>
              <v-btn @click="navigateToAICheck(phishingtype)"  color="#14274E">AI로 조회하기</v-btn>
            </p>
          </div>
          <v-card-actions><v-spacer/><v-btn @click="closeModal()" color="#14274E">닫기</v-btn></v-card-actions>

        </v-col>
      </v-row>
    </div>

    <!-- 보이스 피싱 :: 안전 번호 -->
    <div v-else-if="phishingtype == 'Phone' && !responsedata">
      <v-col >
        <div class="resultTitle">전화번호 조회 결과</div>
      </v-col>
      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div>
            <img src="../assets/safe.png" alt="Safe Icon" class="icon">
            <p>번호 조회 결과 해당 번호는 스팸 번호로 등록된 번호가 아닙니다. <br><b>안전한 번호</b>로 판단됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              통화 내용을 분석해드립니다. <br><b>AI로 더 조회</b>해 보시겠어요?<br>
              <v-btn @click="navigateToAICheck(phishingtype)"  color="#14274E">AI로 조회하기</v-btn>
            </p>
          </div>
          <v-card-actions><v-spacer/><v-btn @click="closeModal()" color="#14274E">닫기</v-btn></v-card-actions>

        </v-col>
      </v-row>
      
    </div>

    <!-- 스미싱 :: 스팸 번호 -->
    <div v-else-if="phishingtype == 'Message' && responsedata">
      <v-col >
        <div class="resultTitle">문자 번호 조회 결과</div>
      </v-col>

      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div v-if="resultData == 1">
            <img src="../assets/danger.png" alt="Danger Icon" class="icon">
            <p>번호 조회 결과, 해당 번호는 스팸 번호로 등록되어 있습니다. <br><b>위험한 번호</b>로 판단됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              스미싱 범죄 관련<br> <b>대응 메뉴얼</b>을 제공해드리고 있어요.<br>확인하시겠어요?<br>
              <v-btn @click="navigateToManual(phishingtype)"  color="#14274E">메뉴얼 보기</v-btn>
            </p>
            <p>
              문자에 링크가 포함되어있나요? <br><b>악성링크</b>인지 판단해드립니다.<br>
              <v-btn @click="navigateToLinkCheck(phishingtype)"  color="#14274E">링크 조회하기</v-btn>
            </p>
            <p>
              문자 내용을 분석해드립니다. <br><b>AI로 더 조회</b>해 보시겠어요?<br>
              <v-btn @click="navigateToAICheck(phishingtype)"  color="#14274E">AI로 조회하기</v-btn>
            </p>
          </div>
          <v-card-actions><v-spacer/><v-btn @click="closeModal()" color="#14274E">닫기</v-btn></v-card-actions>

        </v-col>
      </v-row>

    </div>

    <!-- 스미싱 :: 안전 번호 -->
    <div v-else-if="phishingtype == 'Message' && !responsedata">
      <v-col >
        <div class="resultTitle">문자 번호 조회 결과</div>
      </v-col>

      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div>
            <img src="../assets/safe.png" alt="Safe Icon" class="icon">
            <p>번호 조회 결과 해당 번호는 스팸 번호로 등록된 번호가 아닙니다. <br><b>안전한 번호</b>로 판단됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              문자에 링크가 포함되어있나요? <br><b>악성링크</b>인지 판단해드립니다.<br>
              <v-btn @click="navigateToLinkCheck(phishingtype)"  color="#14274E">링크 조회하기</v-btn>
            </p>
            <p>
              문자 내용을 분석해드립니다. <br><b>AI로 더 조회</b>해 보시겠어요?<br>
              <v-btn @click="navigateToAICheck(phishingtype)"  color="#14274E">AI로 조회하기</v-btn>
            </p>
          </div>
          <v-card-actions><v-spacer/><v-btn @click="closeModal()" color="#14274E">닫기</v-btn></v-card-actions>

        </v-col>
      </v-row>
    </div>
    
  </v-card-text>
</template>
        
        
<script>
export default {
  props: {
    phishingtype: String,
    responsedata: Object,
    showResultModal: Boolean,
    phoneNum:String
  },
  data() {
    return {
      resultData: this.responsedata.phishingId,
    };
  },
  methods: {
    closeModal() {
      this.showResultModal = false;
      this.$emit("update:showResultModal", false);
    },
    navigateToAICheck(phishingtype) {
      this.$router.push({ name: 'AICheck', params: {phishingtype} });
    },
    navigateToLinkCheck(phishingtype) {
      this.$router.push({ name: 'PhishingLink', params: {phishingtype} });
    },
    navigateToManual(phishingtype){
      this.$router.push({ name: 'ToDoPage', params: {phishingtype} });
    }
  },
};
</script>

<style scoped>
div{
  font-family: 'Pretendard-Regular'
}
p{
  font-size: 22px;
  line-height: 1.4;
}
.resultTitle {
  margin: 3%;
  font-size: 30px;
  color: white;
  font-weight:bolder; 
  text-align: center;
}
.resultContent{
  border-radius: 15px;
  margin: 1%;
}
.left-section {
  display: flex;
  flex-direction: column;
  background-color: white;
  padding-top: 40px;
  border-top-left-radius: 15px;
  border-bottom-left-radius: 15px;
  
}
.v-btn { 
   background-color:#14274E;
   color:white ;  
   margin: 10px;
} 


.sol{
  height: 80%;
}

.icon {
  width: 40%;
}

.right-section {
  background-color: white;
  padding-top: 40px;
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
  
}
.total{
  background-color: #14274E;
  font-size:20px; 
  justify-content: center;
  border-radius: 15px;
}
.bordered {
    border-right: 5px dotted #14274E;
    
}

</style>
        

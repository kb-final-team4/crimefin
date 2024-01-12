<template>
  <v-card-text :value="showResultModal" @input="$emit('update:showResultModal', $event)">
  <!--<v-card-text v-model="showResultModal" class="total">-->
    <div v-if="resultData == 1">
      <v-col >
          <div class="resultTitle">링크 분석 결과</div>
      </v-col>
      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div>
            <img src="../assets/danger.png" alt="Danger Icon" class="icon">
            <p>링크 분석 결과, 해당 링크는 <b>악성 링크</b>로 판단됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              해당 범죄 관련<br> <b>대응 메뉴얼</b>을 제공해드리고 있어요.<br>확인하시겠어요?<br>
              <v-btn @click="navigateToManual(phishingtype)"  color="#14274E">메뉴얼 보기</v-btn>
            </p>
            <p>
              내용을 분석해드립니다. <br><b>AI로 더 조회</b>해 보시겠어요?<br>
              <v-btn @click="navigateToAICheck(phishingtype)"  color="#14274E">AI로 조회하기</v-btn>
            </p>
          </div>
          <v-card-actions><v-spacer/><v-btn @click="closeModal()" color="#14274E">닫기</v-btn></v-card-actions>

        </v-col>
      </v-row>
    </div>
    
    <div v-if="resultData == 2">
      <v-col >
          <div class="resultTitle">링크 분석 결과</div>
      </v-col>
      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div>
            <img src="../assets/warning.png" alt="Warning Icon" class="icon">
            <p>링크 분석 결과, 해당 링크는 <b>악성 링크</b>로 의심됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              해당 범죄 관련<br> <b>대응 메뉴얼</b>을 제공해드리고 있어요.<br>확인하시겠어요?<br>
              <v-btn @click="navigateToManual(phishingtype)"  color="#14274E">메뉴얼 보기</v-btn>
            </p>
            <p>
              내용을 분석해드립니다. <br><b>AI로 더 조회</b>해 보시겠어요?<br>
              <v-btn @click="navigateToAICheck(phishingtype)"  color="#14274E">AI로 조회하기</v-btn>
            </p>
          </div>
          <v-card-actions><v-spacer/><v-btn @click="closeModal()" color="#14274E">닫기</v-btn></v-card-actions>

        </v-col>
      </v-row>
    </div>
    <div v-if="resultData == 3">
      <v-col >
          <div class="resultTitle">링크 분석 결과</div>
      </v-col>
      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div>
            <img src="../assets/safe.png" alt="safe Icon" class="icon">
            <p>링크 분석 결과, 해당 링크는 <b>일반 링크</b>로 판단됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              내용을 분석해드립니다. <br><b>AI로 더 조회</b>해 보시겠어요?<br>
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
    },
    data() {
      return {
        resultData: this.responsedata,
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
        

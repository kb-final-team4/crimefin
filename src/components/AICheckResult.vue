<template>
    
    <v-card-text v-model="showResultModal" class="total">
    <div v-if="resultData == '위험'">
      <v-col >
          <div class="resultTitle">AI 분석 결과</div>
      </v-col>
      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div>
            <img src="../assets/danger.png" alt="Danger Icon" class="icon">
            <p>AI 분석 결과, 해당 내용은 <b>피싱 위험</b>으로 판단됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              해당 범죄 관련<br> <b>대응 메뉴얼</b>을 제공해드리고 있어요.<br>확인하시겠어요?<br>
              <v-btn @click="navigateToManual(phishingtype)"  color="#14274E">메뉴얼 보기</v-btn>
            </p>
          </div>
          <v-card-actions><v-spacer/><v-btn @click="closeModal()" color="#14274E">닫기</v-btn></v-card-actions>

        </v-col>
      </v-row>
    </div>
    
    <div v-if="resultData == '주의'">
      <v-col >
          <div class="resultTitle">AI 분석 결과</div>
      </v-col>
      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div>
            <img src="../assets/warning.png" alt="Warning Icon" class="icon">
            <p>AI 분석 결과, 해당 내용은 <b>피싱 주의</b>로 의심됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            <p>
              해당 범죄 관련<br> <b>대응 메뉴얼</b>을 제공해드리고 있어요.<br>확인하시겠어요?<br>
              <v-btn @click="navigateToManual(phishingtype)"  color="#14274E">메뉴얼 보기</v-btn>
            </p>
          </div>
          <v-card-actions><v-spacer/><v-btn @click="closeModal()" color="#14274E">닫기</v-btn></v-card-actions>

        </v-col>
      </v-row>
    </div>
    <div v-if="resultData == '안전'">
      <v-col >
          <div class="resultTitle">AI 분석 결과</div>
      </v-col>
      <v-row class="resultContent">
        <v-col cols="7" class="left-section bordered" >
      
          <div>
            <img src="../assets/safe.png" alt="safe Icon" class="icon">
            <p>AI 분석 결과, 해당 내용은 <b>일반 문맥</b>으로 판단됩니다.</p>
          </div>

        </v-col>
    
        <v-col cols="5" class="right-section">

          <div class="sol  mx-auto my-auto">
            
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
      navigateToManual(phishingtype){
        this.$router.push({ name: 'ToDoPage', params: {phishingtype} });
      }
  },
};
</script>

<style scoped>
    .total{
        background-color: white ;
        padding: 20px; 
        font-size:20px; 
        text-align: center;
    }
    .resultTitle{
        padding: 20px; 
        margin: 20px; 
        font-size:30px; 
        font-weight:bolder; 
        text-align: center;
    }
    v-img{
  width: 50px;
}
</style>
          
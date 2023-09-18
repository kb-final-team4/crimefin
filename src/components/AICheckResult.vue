<template>
    
    <v-card-text v-model="showResultModal" class="total">
        
    <div class="resultTitle">AI 테스트 결과</div>

    <h3>{{resultData}}</h3>
    <div v-if="resultData == '위험'">
      <p>조회하신 내용은 피싱 위험으로 분류되었습니다.</p>
      <v-img src="../assets/red_signal.png" alt="빨간 신호등" max-width="60%" max-height="60%"></v-img>
      <p>메뉴얼을 보시겠어요?</p>
      <v-btn @click="navigateToManual(phishingtype)">메뉴얼 보기</v-btn>

      <div style="margin: 30px;">
      <v-btn @click="closeModal">닫기</v-btn>
      </div>
    </div>
    <div v-else-if="resultData == '주의'">
        <p>조회하신 내용은 피싱 주의로 분류되었습니다.</p>
      <v-img src="../assets/orange_signal.png" alt="주황 신호등" max-width="60%" max-height="60%"></v-img>
      <p>메뉴얼을 보시겠어요?</p>
      <v-btn @click="navigateToManual(phishingtype)">메뉴얼 보기</v-btn>
    
      <div style="margin: 30px;">
      <v-btn @click="closeModal">닫기</v-btn>
      </div>
    </div>
    <div v-else-if="resultData == '안전'">
      <p>조회하신 내용은 피싱으로부터 안전합니다!!</p>
      <v-img src="../assets/green_signal.png" alt="녹색 신호등" max-width="60%" max-height="60%"></v-img>
      
      <div style="margin: 30px;">
      <v-btn @click="closeModal">닫기</v-btn>
      </div>
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
          
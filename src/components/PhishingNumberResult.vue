        <!--{{ this.phishingtype }} -->
        <!-- {{ phishingtype === 'Message' ? 'Message :: 문자 번호 조회 결과' : '전화 번호 조회 결과' }} -->
      
<template>
  <v-card-text v-model="showResultModal" class="total">
    <div v-if="phishingtype === 'Phone' && responsedata">
      <div class="resultTitle">전화 번호 조회 결과</div>
      <p v-if="resultData === 0">
        번호 조회 결과 해당 번호는 스팸 번호로 등록되어있지 않습니다. 안전합니다.
      </p>
      <p v-else>
        번호 조회 결과 해당 번호는 스팸 번호로 등록되어 있습니다. 위험한 번호로 판단됩니다.
      </p>
      <p>메뉴얼을 보시겠어요?</p>
      <v-btn @click="navigateToManual(phishingtype)" style="margin: 15px;">메뉴얼 보기</v-btn>
      <p>AI로 더 조회해 보시겠어요?</p>
      <v-btn @click="navigateToAICheck(phishingtype)" style="margin: 15px;">AI로 조회하기</v-btn>
      <div style="margin: 30px;">
      <v-btn @click="closeModal">닫기</v-btn>
      </div>
    </div>
    <div v-else-if="phishingtype === 'Phone' && !responsedata">
      <div class="resultTitle">전화 번호 조회 결과</div>
      <p>
        번호 조회 결과 해당 번호는 스팸 번호로 등록된 번호가 아닙니다.
      </p>
      <p>AI로 더 조회해 보시겠어요?</p>
      <v-btn @click="navigateToAICheck(phishingtype)">AI로 조회하기</v-btn>
      
      <v-btn @click="closeModal">닫기</v-btn>
    </div>
    <div v-else-if="phishingtype === 'Message' && responsedata">
      <div class="resultTitle">Message :: 문자 번호 조회 결과</div>
      <p>
        번호 조회 결과 해당 번호는 스팸 번호로 등록되어 있습니다. 위험한 번호로 판단됩니다.
      </p>
      <p>메뉴얼을 보시겠어요?</p>
      <v-btn @click="navigateToManual(phishingtype)">메뉴얼 보기</v-btn>
      <p>AI로 더 조회해 보시겠어요?</p>
      <v-btn @click="navigateToAICheck(phishingtype)">AI로 조회하기</v-btn>
      <p>링크가 있으신가요?</p>
      <v-btn @click="navigateToLinkCheck(phishingtype)">링크 조회하기</v-btn>
      <p>닫기</p>
      <v-btn @click="closeModal">닫기</v-btn>
    </div>
    <div v-else-if="phishingtype === 'Message' && !responsedata">
      <div class="resultTitle">Message :: 문자 번호 조회 결과</div>
      <p>
        번호 조회 결과 해당 번호는 스팸 번호로 등록된 번호가 아닙니다.
      </p>
      <p>링크가 있으신가요?</p>
      <v-btn @click="navigateToLinkCheck(phishingtype)">링크 조회하기</v-btn>
      <p>AI로 더 조회해 보시겠어요?</p>
      <v-btn @click="navigateToAICheck(phishingtype)">AI로 조회하기</v-btn>
      
      <v-btn @click="closeModal">닫기</v-btn>
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
      resultData: this.responsedata.phishingId,
    };
  },
  methods: {
    closeModal() {
      this.showResultModal = false;
      this.$emit("update:showResultModal", false);
    },
    navigateToAICheck(phishingtype) {
      // AI로 조회하기 페이지로 이동하는 로직을 추가
      this.$router.push({ name: 'AICheck', params: {phishingtype} });
    },
    navigateToLinkCheck(phishingtype) {
      // 링크 조회하기 페이지로 이동하는 로직을 추가
      this.$router.push({ name: 'PhishingLink', params: {phishingtype} });
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
.total{

}
</style>

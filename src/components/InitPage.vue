<template>
  <v-app class="black-bg">
    <v-row>
      <v-col cols="8" class="scrollable-section">
        <InitService/>
      </v-col>

      <v-col cols="4" class="non-scrollable-section">
        <InitStart/> 
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
import InitService from '../components/InitService.vue'
import InitStart from './InitStart.vue'; 

export default {
  components: {
    InitService, InitStart
  },
};
</script>

<style>

.scrollable-section {
  background-color: black; 
  overflow-y: scroll; 
  height: 105vh; 
  width:100%;

}

.scrollable-section::-webkit-scrollbar {
    display: none; 
}

.non-scrollable-section {
  background-color: black; 
  height: 105vh; 
  
}

body::-webkit-scrollbar {
    display: none; 
    
}
body{
  overflow-y: hidden;
}
</style>
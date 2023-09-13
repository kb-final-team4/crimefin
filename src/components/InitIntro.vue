<template>
  <v-app>
  <InitService/>

</v-app>
</template>

<script>
  import InitService from '../components/InitService.vue'

  export default {
    components: {
      InitService,
    },

    
  }
</script>
<style scoped>
.content {
  background-color: black;
}
</style>
<template>
  <v-container fluid fill-height>
    <v-row align="center" justify="center" >
      <v-col cols="12" sm="6" md="4">
        
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="6" md="4">
        <h1 class="white-text text-center">Get Started</h1>
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="6" md="4">
        <router-link to="/login">
          <v-btn class="custom-button" dark>Sign In</v-btn>
        </router-link>
        <router-link to="/signup">
          <v-btn class="custom-button" dark>Join</v-btn>
        </router-link>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.white-text {
  color: white;
}

.custom-button {
  background-color: #445069;
  color: white;
  margin: 5px;
}
</style>

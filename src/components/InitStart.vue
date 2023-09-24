<template>
  <v-container fluid fill-height class="container">
    <v-row align="center" justify="center" >
      <v-col cols="12" sm="6" md="4">
        
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="6" md="4">
        <p class="white-text text-center">Get Started</p>
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
p, div{
  font-family: 'TheJamsil5Bold';
}
.container{
  /* background-image: url("../assets/init_bg.png"); */
  background-size: cover;
  background-position: center;
}
.white-text {
  color: white;
  font-weight: bolder;
  font-size: 40px;
}

.custom-button {
  background-color: #445069;
  color: white;
  margin: 5px;
  width: 100px;
}

</style>
<template>
  <v-app>
    <AppBar />

    <v-main>
      <v-container fluid>
        <div class="d-flex justify-center">

          <v-card class="mx-2 mt-6 mr-2 cover1" width="25%" tile>
            <v-card-title>
              <v-card class="mx-auto inner-cover" width="90%" height="60%" tile>
                <v-img
                  src="../assets/12.png"
                  class="mx-auto"
                  width="90%"
                  height="90%"
                ></v-img>
              </v-card>
            </v-card-title>
            <v-card-actions class="justify-center">
              <v-btn color="" @click="redirectToPhishingNumber('Phone')"
                >바로가기</v-btn
              >
            </v-card-actions>
          </v-card>

          <v-card class="mx-2 mt-6 mr-2 cover2" width="25%"  tile>
            <v-card-title>
              <v-card class="mx-auto inner-cover" width="90%" height="60%" tile>
                <v-img
                  src="../assets/13.png"
                  class="mx-auto"
                  width="90%"
                  height="90%"
                ></v-img>
              </v-card>
            </v-card-title>
            <v-card-actions class="justify-center">
              <v-btn
                color="primary"
                @click="redirectToPhishingNumber('Message')"
                >바로가기</v-btn
              >
            </v-card-actions>
          </v-card>

          <v-card class="mx-2 mt-6 mr-2 cover3" width="25%" tile>
            <v-card-title>
              <v-card class="mx-auto inner-cover" width="90%" height="60%" tile>
                <v-img
                  src="../assets/14.png"
                  class="mx-auto"
                  width="90%"
                  height="90%"
                ></v-img>
              </v-card>
            </v-card-title>
            <v-card-actions class="justify-center">
              <v-btn color="" @click="redirectToPhishingLink('SNS')"
                >바로가기</v-btn
              >
            </v-card-actions>
          </v-card>
        </div>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import AppBar from "../views/AppBar.vue"; 

export default {
  components: {
    AppBar, 
  },
  methods: {
    redirectToPhishingNumber(phishingtype) {
      this.$router.push({ name: 'PhishingNumber', params: {phishingtype} })
    },
    redirectToPhishingLink(phishingtype) {
      this.$router.push({ name: "PhishingLink", params: {phishingtype}});
    },
  },
};
</script>

<style scoped>
.cover1, .cover3 {
  background-color: #14274E !important;
  border-radius: 25px !important;
  margin: 2% !important;
}

.cover2 {
  background-color: #9BA4B4 !important;
  border-radius: 25px !important;
  margin: 2% !important;
}
.inner-cover{
  background-color: #F1F6F9;
  border-radius: 25px !important;
  margin-top: 1% !important;
}

.cover1, .cover2, .cover3 {
  margin-top: 5% !important;
  transition: all .3s ease;
}

.cover1:hover,
.cover2:hover,
.cover3:hover {
  transform: translateY(-5px);
  box-shadow: 0px 10px 20px rgba(0,0,0,.5);
}
</style>

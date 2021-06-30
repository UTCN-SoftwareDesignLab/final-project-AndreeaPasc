<template>
  <v-card>
    <v-card-title>
      Recipes
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="order">Order</v-btn>
      <input v-model= "message" placeholder="Email Here">
      <v-btn @click="send">Send</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="recipes"
        :search="search"
    ></v-data-table>
    <iframe
        width="600"
        height="450"
        style="border:0"
        loading="lazy"
        allowfullscreen
        src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJUyN2aONHRkcRRFNd1oakVRs&key=AIzaSyBR8cxgxePm65q9HH1C7xas6yQPix1E9h4">
    </iframe>
    <RecipeDialog
        :opened="dialogVisible"
        :recipe="selectedRecipe"
        @refresh="refreshList"
    ></RecipeDialog>
  </v-card>
</template>

<script>
import api from "../api";
import RecipeDialog from "../components/RecipeDialog";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import router from "../router";

export default {
  name: "RecipeList",
  components: { RecipeDialog },
  data() {
    return {
      recipes: [],
      search: "",
      message: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Ingredients", value: "ingredients" },
        { text: "Quantity (g)", value: "quantity" },
      ],
      dialogVisible: false,
      selectedRecipe: {},
    };
  },
  methods: {
    send(){
      api.customers.sendMail(this.message)
          .then(() => this.$emit("refresh"));
    },
    order(){
        var socket = new SockJS("http://localhost:8088/api/websocket");
        var stompClient = Stomp.over(socket);
        stompClient.connect(
            {},
            // eslint-disable-next-line no-unused-vars
            frame =>{

              stompClient.send("/messages", JSON.stringify({message: "New order has been placed"}))

              //var username = this.$store.getters["auth/getUsername"];
            }
        )
    },
    logout() {
      this.$store.dispatch("auth/logout");
      router.push("/");
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedRecipe = {};
      this.recipes = await api.recipes.allRecipes();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
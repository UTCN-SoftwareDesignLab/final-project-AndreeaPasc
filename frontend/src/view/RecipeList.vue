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
      <v-btn @click="addRecipe">Add Recipe</v-btn>
      <v-btn @click="downloadRecipes">Download</v-btn>

    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="recipes"
        :search="search"
        @click:row="editRecipe"
    ></v-data-table>
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
export default {
  name: "RecipeList",
  components: { RecipeDialog },
  data() {
    return {
      recipes: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Ingredients", value: "ingredients" },
        { text: "Quantity (g)", value: "quantity" },
        { text: "Price", value: "price" },
      ],
      dialogVisible: false,
      selectedRecipe: {},
    };
  },
  methods: {

    downloadRecipes(){
      api.recipes.download();
    },

    editRecipe(recipe) {
      this.selectedRecipe = recipe;
      this.dialogVisible = true;
    },
    addRecipe() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedRecipe = {};
      this.recipes = await api.recipes.allRecipes();
    },
  },
  created() {
    var socket = new SockJS("http://localhost:8088/api/websocket");
    var stompClient = Stomp.over(socket);
    stompClient.connect(
        {},
        // eslint-disable-next-line no-unused-vars
        frame =>{

          stompClient.subscribe("/api/recipes/messages", tick =>{
            console.log(tick);
            alert(JSON.parse(tick.body).content);
          })

          //var username = this.$store.getters["auth/getUsername"];
        }
    )
    this.refreshList();
  },
};
</script>

<style scoped></style>
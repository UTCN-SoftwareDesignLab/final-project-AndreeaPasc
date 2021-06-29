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
      ],
      dialogVisible: false,
      selectedRecipe: {},
    };
  },
  methods: {
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
    this.refreshList();
  },
};
</script>

<style scoped></style>
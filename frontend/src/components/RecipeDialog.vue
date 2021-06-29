<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create recipe" : "Edit recipe" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="recipe.title" label="Title" />
          <v-text-field v-model="recipe.ingredients" label="Ingredients" />
          <v-text-field v-model="recipe.quantity" label="Quantity (g)" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deleteRecipe">Delete Recipe</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
export default {
  name: "RecipeDialog",
  props: {
    recipe: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.recipes
            .create({
              title: this.recipe.title,
              ingredients: this.recipe.ingredients,
              quantity: this.recipe.quantity,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.recipes
            .update({
              id: this.recipe.id,
              title: this.recipe.title,
              ingredients: this.recipe.ingredients,
              quantity: this.recipe.quantity,
            })
            .then(() => this.$emit("refresh"));
      }
    },

    deleteRecipe(){
      api.recipes
          .delete({
            id: this.recipe.id,
          })
          .then(() => this.$emit("refresh"));
    },
  },

  computed: {
    isNew: function () {
      return !this.recipe.id;
    },
  },
};
</script>

<style scoped></style>
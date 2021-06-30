<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-form>
          <v-text-field v-model="recipe.title" label="Title" />
          <v-text-field v-model="recipe.ingredients" label="Ingredients" />
          <v-text-field v-model="recipe.quantity" label="Quantity (g)" />
          <v-text-field v-model="recipe.price" label="Price" />
        </v-form>
        <v-card-actions>
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
  },

  computed: {

  },
};
</script>

<style scoped></style>
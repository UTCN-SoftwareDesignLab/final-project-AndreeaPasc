<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create user" : "Edit user" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="user.username" label="Username" />
          <v-text-field v-model="user.email" label= "Email" />
          <v-text-field v-model="user.password" label= "Password" />
          <v-text-field v-model="user.role" label= "Role" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deleteUser">Delete User</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
export default {
  name: "UserDialog",
  props: {
    user: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.users
            .create({
              name: this.user.username,
              email: this.user.email,
              password: this.user.password,
              role: this.user.role,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.users
            .update({
              id: this.user.id,
              name: this.user.username,
              email: this.user.email,
              role: this.user.role,
            })
            .then(() => this.$emit("refresh"));
      }
    },

  deleteUser(){
    api.users
        .delete({
          id: this.user.id,
        })
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.user.id;
    },
  },
};
</script>

<style scoped></style>
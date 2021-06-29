import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../view/UserList.vue";
import RecipeList from "../view/RecipeList.vue";
import { auth as store } from "../store/auth.module";
import Login from "../view/Login";
import CustomerList from "@/view/CustomerList";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Login",
        component: Login,
    },
    {
        path: "/users",
        name: "Users",
        component: UserList,
        beforeEnter: (to, from, next) => {
            if (store.getters.isAdmin) {
                next();
            } else {
                next({ name: "Home" });
            }
        },
    },
    {
        path: "/recipes",
        name: "Recipes",
        component: RecipeList,
        beforeEnter: (to, from, next) => {
            if (store.state.status.loggedIn) {
                next();
            } else {
                next({ name: "Home" });
            }
        },
    },

    {
        path: "/customers",
        name: "Customers",
        component: CustomerList,
        beforeEnter: (to, from, next) => {
            if (store.state.status.loggedIn) {
                next();
            } else {
                next({ name: "Home" });
            }
        },
    },

    {
        path: "/about",
        name: "About",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "about" */ "../view/About.vue"),
    },
];

const router = new VueRouter({
    routes,
});

export default router;
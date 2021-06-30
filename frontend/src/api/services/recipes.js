import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allRecipes() {
        return HTTP.get(BASE_URL + "/recipes", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(recipe) {
        return HTTP.post(BASE_URL + "/recipes", recipe, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    update(recipe) {
        return HTTP.put(BASE_URL + `/recipes/${recipe.id}`, recipe, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    delete(recipe){
        return HTTP.delete(BASE_URL + `/recipes/${recipe.id}`, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

};
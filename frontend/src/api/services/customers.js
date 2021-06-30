import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    sendMail(message) {
        return HTTP.post(BASE_URL + "/customers", message, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
}
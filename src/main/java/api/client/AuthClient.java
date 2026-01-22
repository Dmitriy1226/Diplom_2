package api.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient extends RestClient {

    private static final String REGISTER = "/api/auth/register";
    private static final String LOGIN = "/api/auth/login";
    private static final String USER = "/api/auth/user";
    private static final String LOGOUT = "/api/auth/logout";

    public Response register(Object body) {
        return given()
                .spec(baseSpec())
                .body(body)
                .when()
                .post(REGISTER);
    }

    public Response login(Object body) {
        return given()
                .spec(baseSpec())
                .body(body)
                .when()
                .post(LOGIN);
    }

    public Response getUser(String accessToken) {
        return given()
                .spec(authSpec(accessToken))
                .when()
                .get(USER);
    }

    public Response patchUser(String accessToken, Object body) {
        return given()
                .spec(authSpec(accessToken))
                .body(body)
                .when()
                .patch(USER);
    }

    public Response deleteUser(String accessToken) {
        return given()
                .spec(authSpec(accessToken))
                .when()
                .delete(USER);
    }

    public Response logout(Object body) {
        return given()
                .spec(baseSpec())
                .body(body)
                .when()
                .post(LOGOUT);
    }
}


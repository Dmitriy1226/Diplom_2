package api.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class IngredientsClient extends RestClient {

    private static final String INGREDIENTS = "/api/ingredients";

    public Response getIngredients() {
        return given()
                .spec(baseSpec())
                .when()
                .get(INGREDIENTS);
    }
}

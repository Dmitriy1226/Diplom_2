package api.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrdersClient extends RestClient {

    private static final String ORDERS = "/api/orders";
    private static final String ALL_ORDERS = "/api/orders/all";

    public Response createOrder(Object body) {
        return given()
                .spec(baseSpec())
                .body(body)
                .when()
                .post(ORDERS);
    }

    public Response createOrderWithAuth(String accessToken, Object body) {
        return given()
                .spec(authSpec(accessToken))
                .body(body)
                .when()
                .post(ORDERS);
    }

    public Response getUserOrders(String accessToken) {
        return given()
                .spec(authSpec(accessToken))
                .when()
                .get(ORDERS);
    }

    public Response getAllOrders() {
        return given()
                .spec(baseSpec())
                .when()
                .get(ALL_ORDERS);
    }
}


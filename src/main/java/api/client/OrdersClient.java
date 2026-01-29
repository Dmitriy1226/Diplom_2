package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrdersClient extends RestClient {

    private static final String ORDERS = "/api/orders";
    private static final String ALL_ORDERS = "/api/orders/all";

    @Step("Создание заказа без авторизации")
    public Response createOrder(Object body) {
        return given()
                .spec(baseSpec())
                .body(body)
                .when()
                .post(ORDERS);
    }

    @Step("Создание заказа с авторизацией")
    public Response createOrderWithAuth(String accessToken, Object body) {
        return given()
                .spec(authSpec(accessToken))
                .body(body)
                .when()
                .post(ORDERS);
    }

    @Step("Получение заказов пользователя")
    public Response getUserOrders(String accessToken) {
        return given()
                .spec(authSpec(accessToken))
                .when()
                .get(ORDERS);
    }

    @Step("Получение всех заказов")
    public Response getAllOrders() {
        return given()
                .spec(baseSpec())
                .when()
                .get(ALL_ORDERS);
    }
}

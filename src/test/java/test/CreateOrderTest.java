package test;

import io.restassured.response.Response;
import model.OrderRequest;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class CreateOrderTest extends BaseTest {

    private List<String> getTwoIngredientIds() {
        Response response = ingredientsClient.getIngredients();

        String firstIngredientId = response.then().extract().path("data[0]._id");
        String secondIngredientId = response.then().extract().path("data[1]._id");

        return Arrays.asList(firstIngredientId, secondIngredientId);
    }

    @Test
    public void createOrderWithoutAuthShouldReturn200WhenValidIngredients() {
        List<String> ingredientIds = getTwoIngredientIds();

        ordersClient.createOrder(new OrderRequest(ingredientIds))
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("order.number", notNullValue());
    }

    @Test
    public void createOrderShouldReturn400WhenNoIngredients() {
        ordersClient.createOrder(new OrderRequest(null))
                .then()
                .statusCode(400)
                .body("success", is(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    public void createOrderShouldReturn500WhenIngredientIdIsInvalid() {
        List<String> invalidIngredientIds = List.of("invalid_ingredient_id");

        ordersClient.createOrder(new OrderRequest(invalidIngredientIds))
                .then()
                .statusCode(500)
                // на 500 часто приходит не JSON, поэтому проверяем текст ответа
                .body(containsString("Internal Server Error"));
    }
}

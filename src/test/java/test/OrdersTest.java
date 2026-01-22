package test;

import io.restassured.response.Response;
import model.OrderRequest;
import model.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class OrdersTest extends BaseTest {

    private List<String> getTwoIngredientIds() {
        Response r = ingredientsClient.getIngredients();

        // В доке обычно: data[0]._id, data[1]._id
        String id1 = r.then().extract().path("data[0]._id");
        String id2 = r.then().extract().path("data[1]._id");

        return Arrays.asList(id1, id2);
    }

    @Test
    public void createOrder_shouldReturn200_whenValidIngredients() {
        List<String> ids = getTwoIngredientIds();

        ordersClient.createOrder(new OrderRequest(ids))
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("order.number", notNullValue());
    }

    @Test
    public void createOrder_shouldReturn400_whenNoIngredients() {
        ordersClient.createOrder(new OrderRequest(null))
                .then()
                .statusCode(400)
                .body("success", is(false));
    }

    @Test
    public void getUserOrders_shouldReturn401_whenNoAuth() {
        // без токена
        ordersClient.getUserOrders("Bearer ")
                .then()
                .statusCode(401);
    }

    @Test
    public void getUserOrders_shouldReturn200_whenAuthorized() {
        User user = randomUser();
        registerAndLogin(user);

        ordersClient.getUserOrders(accessToken)
                .then()
                .statusCode(200)
                .body("success", is(true));
    }
}

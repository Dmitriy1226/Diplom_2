package test;

import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class GetUserOrdersTest extends BaseTest {

    @Override
    protected boolean isUserRequired() {
        return true;
    }

    @Test
    public void getUserOrdersShouldReturn401WhenNoAuth() {
        ordersClient.getUserOrders("Bearer ")
                .then()
                .statusCode(401);
    }

    @Test
    public void getUserOrdersShouldReturn200WhenAuthorized() {
        ordersClient.getUserOrders(accessToken)
                .then()
                .statusCode(200)
                .body("success", is(true));
    }
}

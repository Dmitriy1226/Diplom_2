package test;

import api.client.AuthClient;
import api.client.IngredientsClient;
import api.client.OrdersClient;
import io.restassured.response.Response;
import model.Credentials;
import model.User;
import org.junit.After;

import java.util.Random;

public class BaseTest {

    protected final AuthClient authClient = new AuthClient();
    protected final IngredientsClient ingredientsClient = new IngredientsClient();
    protected final OrdersClient ordersClient = new OrdersClient();

    protected String accessToken; // "Bearer ..."
    protected String refreshToken;

    protected User randomUser() {
        int n = new Random().nextInt(1_000_000);
        return new User("dmitry" + n + "@yandex.ru", "password123", "Dmitry");
    }

    protected void registerAndLogin(User user) {
        Response r = authClient.register(user);
        // достаём токены (в ответе обычно есть accessToken/refreshToken)
        accessToken = r.then().extract().path("accessToken");
        refreshToken = r.then().extract().path("refreshToken");

        // если вдруг register не вернул токены, попробуем login
        if (accessToken == null) {
            Response login = authClient.login(new Credentials(user.getEmail(), user.getPassword()));
            accessToken = login.then().extract().path("accessToken");
            refreshToken = login.then().extract().path("refreshToken");
        }
    }

    @After
    public void tearDown() {
        // аккуратно: удаляем пользователя, если успели получить токен
        if (accessToken != null) {
            authClient.deleteUser(accessToken);
        }
    }
}

package test;

import model.User;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class RegisterTest extends BaseTest {

    @Test
    public void registerShouldReturnSuccessTrue() {
        User user = randomUser();

        authClient.register(user)
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()));
    }

    @Test
    public void registerExistingUserShouldReturn403AndErrorMessage() {
        User user = randomUser();
        authClient.register(user).then().statusCode(200);

        authClient.register(user)
                .then()
                .statusCode(403)
                .body("success", is(false))
                // ревьюер просил проверять ошибку в теле ответа
                .body("message", notNullValue());
    }
}

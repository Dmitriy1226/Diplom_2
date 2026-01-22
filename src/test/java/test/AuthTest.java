package test;

import model.Credentials;
import model.User;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class AuthTest extends BaseTest {

    @Test
    public void register_shouldReturnSuccessTrue() {
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
    public void register_existingUser_shouldReturn403() {
        User user = randomUser();
        authClient.register(user).then().statusCode(200);

        authClient.register(user)
                .then()
                .statusCode(403)
                .body("success", is(false));
    }

    @Test
    public void login_shouldReturn200_whenValidCredentials() {
        User user = randomUser();
        authClient.register(user).then().statusCode(200);

        authClient.login(new Credentials(user.getEmail(), user.getPassword()))
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("accessToken", notNullValue());
    }
}


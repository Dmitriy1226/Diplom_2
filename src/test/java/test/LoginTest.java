package test;

import model.Credentials;
import model.User;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class LoginTest extends BaseTest {

    @Test
    public void loginShouldReturn200WhenValidCredentials() {
        User user = randomUser();
        authClient.register(user).then().statusCode(200);

        authClient.login(new Credentials(user.getEmail(), user.getPassword()))
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("accessToken", notNullValue());
    }

    @Test
    public void loginWithWrongEmailShouldReturn401() {
        User user = randomUser();
        authClient.register(user).then().statusCode(200);

        authClient.login(new Credentials("wrong_" + user.getEmail(), user.getPassword()))
                .then()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    public void loginWithWrongPasswordShouldReturn401() {
        User user = randomUser();
        authClient.register(user).then().statusCode(200);

        authClient.login(new Credentials(user.getEmail(), "wrong_password"))
                .then()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}

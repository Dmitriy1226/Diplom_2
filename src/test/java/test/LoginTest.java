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
}

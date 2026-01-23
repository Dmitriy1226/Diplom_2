package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pages.RegisterPage;

public class RegistrationTest extends BaseUiTest {

    @Test
    @Description("Успешная регистрация пользователя")
    public void successfulRegistration() {
        driver.get("https://stellarburgers.education-services.ru/register");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForPageLoaded();

        String email = System.currentTimeMillis() + "@test.ru";

        registerPage.setName("Test User");
        registerPage.setEmail(email);
        registerPage.setPassword("123456");
        registerPage.clickRegisterButton();

        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("login"));
    }
}

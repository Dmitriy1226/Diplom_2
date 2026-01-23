package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Поле Email
    private final By emailInput = By.xpath("//input[@name='name']");

    // Поле Пароль
    private final By passwordInput = By.xpath("//input[@name='Пароль']");

    // Кнопка Войти
    private final By loginButton = By.xpath("//button[text()='Войти']");

    // Ссылка Зарегистрироваться
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");

    // Ссылка Восстановить пароль
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    @Step("Ввод email")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Перейти к регистрации")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Перейти к восстановлению пароля")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }
}

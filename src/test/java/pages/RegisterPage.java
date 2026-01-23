package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Поле Имя
    private final By nameInput =
            By.xpath("//label[text()='Имя']/parent::div//input");

    // Поле Email
    private final By emailInput =
            By.xpath("//label[text()='Email']/parent::div//input");

    // Поле Пароль
    private final By passwordInput =
            By.xpath("//label[text()='Пароль']/parent::div//input");

    // Кнопка Зарегистрироваться
    private final By registerButton =
            By.xpath("//button[text()='Зарегистрироваться']");

    // Ошибка под паролем
    private final By passwordError =
            By.xpath("//p[contains(@class,'input__error')]");

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
    }

    @Step("Ввод имени")
    public void setName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameInput)).sendKeys(name);
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }
}

package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;

    // Кнопки входа
    private final By loginButton = By.xpath(".//button[contains(text(),'Войти в аккаунт')]");
    private final By personalAccountButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    // Вкладки конструктора
    private final By bunsTab = By.xpath(".//span[contains(text(),'Булки')]");
    private final By saucesTab = By.xpath(".//span[contains(text(),'Соусы')]");
    private final By fillingsTab = By.xpath(".//span[contains(text(),'Начинки')]");

    // Заголовки разделов (для проверок)
    private final By bunsHeader = By.xpath(".//h2[contains(text(),'Булки')]");
    private final By saucesHeader = By.xpath(".//h2[contains(text(),'Соусы')]");
    private final By fillingsHeader = By.xpath(".//h2[contains(text(),'Начинки')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    @Step("Нажать кнопку 'Войти в аккаунт' на главной")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Перейти в раздел 'Булки'")
    public void clickBuns() {
        driver.findElement(bunsTab).click();
    }

    @Step("Перейти в раздел 'Соусы'")
    public void clickSauces() {
        driver.findElement(saucesTab).click();
    }

    @Step("Перейти в раздел 'Начинки'")
    public void clickFillings() {
        driver.findElement(fillingsTab).click();
    }

    // Локаторы для ожиданий в тестах
    public By bunsHeaderLocator() {
        return bunsHeader;
    }

    public By saucesHeaderLocator() {
        return saucesHeader;
    }

    public By fillingsHeaderLocator() {
        return fillingsHeader;
    }
}

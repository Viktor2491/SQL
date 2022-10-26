package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.util.Objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    SelenideElement login = $("[data-test-id='login'] input");
    SelenideElement pass = $("[data-test-id='password'] input");
    SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    SelenideElement errorEmptyLogin = $("[data-test-id='login'] .input__sub");
    SelenideElement errorEmptyPass = $("[data-test-id='password'] .input__sub");
    SelenideElement button = $("[data-test-id='action-login']");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        clearInput();
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        clearInput();
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        button.click();
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
    }

    public void invalidPassword(DataHelper.AuthInfo info) {
        clearInput();
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        button.click();
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
    }

    public void emptyLoginOrPass(DataHelper.AuthInfo info) {
        clearInput();
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        button.click();
        if (Objects.requireNonNull(login.getValue()).isEmpty()) {
            errorEmptyLogin.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
        }
        if (Objects.requireNonNull(pass.getValue()).isEmpty()) {
            errorEmptyPass.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
        }
    }

    public void invalidPasswordTripleEntry(DataHelper.AuthInfo info) {
        for (int i = 0; i < 3; i++) {
            invalidPassword(info);
            if (i < 2) {
                errorNotification.getText().equals("Ошибка! " +
                        "Неверно указан логин или пароль");
            } else {
                errorNotification.getText().equals("Ошибка! " +
                        "Превышено количество попыток входа. Пользователь заблокирован");
            }
        }
    }

    private void clearInput() {
        login.sendKeys(Keys.CONTROL + "A");
        login.sendKeys(Keys.DELETE);
        pass.sendKeys(Keys.CONTROL + "A");
        pass.sendKeys(Keys.DELETE);
    }
}
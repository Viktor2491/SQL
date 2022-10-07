package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataHelper.getInvalidCode;
import static ru.netology.data.SQLHelper.getVerifyCode;

public class VerificationPage {

    SelenideElement codeInput = $("[data-test-id='code'] input");
    SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    SelenideElement errorEmptyCode = $("[data-test-id='code'] .input__sub");
    SelenideElement button = $("[data-test-id='action-verify']");
    SelenideElement header = $(".paragraph");

    public void accessPage() {
        header.shouldBe(visible).shouldHave(text("Необходимо подтверждение"));
    }

    public void validCode() {
        clearInput();
        codeInput.setValue(getVerifyCode());
        button.click();
        new DashboardPage();
    }

    public void invalidCode() {
        clearInput();
        codeInput.setValue(getInvalidCode());
        button.click();
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка"));
    }

    public void emptyCode() {
        clearInput();
        codeInput.setValue("");
        button.click();
        errorEmptyCode.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    private void clearInput() {
        codeInput.sendKeys(Keys.CONTROL + "A");
        codeInput.sendKeys(Keys.DELETE);
    }
}

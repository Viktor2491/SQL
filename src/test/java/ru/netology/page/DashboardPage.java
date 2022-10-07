package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    SelenideElement dashboard = $("[data-test-id='dashboard']");

    public void accessLogin() {
        dashboard.shouldBe(visible).shouldHave(text("Личный кабинет"));
    }
}
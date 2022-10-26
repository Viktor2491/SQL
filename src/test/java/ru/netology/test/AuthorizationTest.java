package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.deleteAllDB;
import static ru.netology.data.SQLHelper.deleteCodes;

public class AuthorizationTest {

    @BeforeAll
    static void setUpAll() {
        Configuration.headless = true;
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterEach
    void setDown() {
        deleteCodes();
    }

    @AfterAll
    static void setDownAll() {
        deleteAllDB();
    }

    @Test
    @DisplayName("Успешная авотризация 1")
    void shouldReturnAccessWithFirstValidLogin() {
        new LoginPage().validLogin(getFirstAuthInfo()).validCode();
        new DashboardPage().accessLogin();
    }

    @Test
    @DisplayName("Успешная авотризация 2")
    void shouldReturnAccessWithSecondValidLogin() {
        new LoginPage().validLogin(getSecondAuthInfo()).validCode();
        new DashboardPage().accessLogin();
    }

    @Test
    @DisplayName("Ошибка, неверный логин")
    void shouldReturnFailWithInvalidLogin() {
        new LoginPage().invalidLogin(getInvalidAuthInfo());
    }

    @Test
    @DisplayName("Ошибка, неверный пароль")
    void shouldReturnFailWithInvalidPassword() {
        new LoginPage().invalidPassword(getInvalidAuthInfo());
    }

    @Test
    @DisplayName("Ошибка, пустое поле логина")
    void shouldReturnFailWithEmptyLogin() {
        new LoginPage().emptyLoginOrPass(getEmptyLogin());
    }

    @Test
    @DisplayName("Ошибка, пустое поле пароля")
    void shouldReturnFailWithEmptyPass() {
        new LoginPage().emptyLoginOrPass(getEmptyPass());
    }

    @Test
    @DisplayName("Ошибка, пустое поле логина и пароля")
    void shouldReturnFailWithEmptyLoginAndPass() {
        new LoginPage().emptyLoginOrPass(getEmptyAuthInfo());
    }

    @Test
    @DisplayName("Ошибка, неверный код подтверждения")
    void shouldReturnFailWithInvalidCode() {
        new LoginPage().validLogin(getFirstAuthInfo()).invalidCode();
    }

    @Test
    @DisplayName("Ошибка, пустое поле кода подтверждения")
    void shouldReturnFailWithEmptyCode() {
        new LoginPage().validLogin(getSecondAuthInfo()).emptyCode();
    }


    @Test
    @DisplayName("Блокировка, введен неверный пароль 3 раза")
    void shouldReturnFailAfterTripleEntry() {
        new LoginPage().invalidPasswordTripleEntry(getInvalidPassFirstAuthInfo());
    }
}
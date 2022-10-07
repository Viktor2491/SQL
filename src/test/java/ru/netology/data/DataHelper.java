package ru. netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

public class DataHelper {

    private static final Faker faker = new Faker();

    @Data
    @AllArgsConstructor
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getFirstAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getSecondAuthInfo() {
        return new AuthInfo("petya", "123qwerty");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo(faker.name().username(), faker.internet().password());
    }

    public static AuthInfo getInvalidPassFirstAuthInfo() {
        return new AuthInfo("vasya", faker.internet().password());
    }

    public static AuthInfo getEmptyAuthInfo() {
        return new AuthInfo("", "");
    }

    public static AuthInfo getEmptyLogin() {
        return new AuthInfo("", faker.internet().password());
    }

    public static AuthInfo getEmptyPass() {
        return new AuthInfo(faker.name().username(), "");
    }

    public static String getInvalidCode() {
        return faker.code().gtin13();
    }
}
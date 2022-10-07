package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;

import static java.sql.DriverManager.getConnection;

public class SQLHelper {
    private final static QueryRunner queryRunner = new QueryRunner();
    private final static Connection conn = connection();
    private static final String url = "jdbc:mysql://185.119.57.176:3306/base";
    private static final String user = "user";
    private static final String password = "qwerty";
    static String code;

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection connection() {
        return getConnection(url, user, password);
    }

    @SneakyThrows
    public static String getVerifyCode() {
        code = queryRunner.query(conn,
                "SELECT ac.code, ac.created, u.id, ac.user_id FROM auth_codes ac, users u WHERE u.id = ac.user_id ORDER BY created DESC",
                new ScalarHandler<>());
        return code;
    }

    @SneakyThrows
    public static void deleteCodes() {
        queryRunner.update(conn, "DELETE FROM auth_codes");
    }

    @SneakyThrows
    public static void deleteAllDB() {
        queryRunner.update(conn, "DELETE FROM auth_codes");
        queryRunner.update(conn, "DELETE FROM card_transactions");
        queryRunner.update(conn, "DELETE FROM cards");
        queryRunner.update(conn, "DELETE FROM users");
    }
}
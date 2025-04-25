package user;

import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import model.Credentials;
import model.User;
import io.restassured.response.ValidatableResponse;

public class UserService {

    private String baseURI;

    public UserService(String baseURI) {
        this.baseURI = baseURI;
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .baseUri(baseURI)
                .header("Content-Type", "application/json")
                .body(user)
                .post("/api/auth/register")
                .then()
                .log()
                .all();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .baseUri(baseURI)
                .header("Authorization", accessToken)
                .delete("/api/auth/user")
                .then()
                .log()
                .all();
    }

    @Step("Логин и получение токена")
    public String loginAndGetToken(Credentials credentials) {
        return given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .baseUri(baseURI)
                .header("Content-Type", "application/json")
                .body(credentials)
                .post("/api/auth/login")
                .then()
                .extract()
                .path("accessToken");
    }
}
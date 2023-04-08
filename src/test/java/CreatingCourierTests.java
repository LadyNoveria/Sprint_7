import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class CreatingCourierTests extends CourierProvider {

    private static final String LOGIN = "Flash";
    private static final String PASSWORD = "theFastest";
    private static final String FIRST_NAME = "Bartholomew Henry";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void deleteData() {
        deleteCourier(loginCourier(LOGIN, PASSWORD, null)
                .then()
                .extract()
                .body().path("id"));
    }

    @Test
    @DisplayName("201 OK: Successful courier creation")
    public void successfulCourierCreation() {
        Response response = createCourier(LOGIN, PASSWORD, FIRST_NAME);
        response.then().assertThat().body("ok", equalTo(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("409 Сonflict: creating identical couriers")
    public void conflictCreatingIdenticalCouriers() {
        createCourier(LOGIN, PASSWORD, FIRST_NAME);
        Response response = createCourier(LOGIN, PASSWORD, FIRST_NAME);
        response.then().assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and().statusCode(409);
    }

    @Test
    @DisplayName("409 Сonflict: creating identical couriers")
    public void conflictCreatingCouriersWithTheSameLogins() {
        createCourier(LOGIN, PASSWORD, FIRST_NAME);
        Response response = createCourier(LOGIN, "onlyTheThird", "Wally West");
        response.then().assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and().statusCode(409);
    }

    @Test
    @DisplayName("200 OK: creating couriers without first Name")
    public void badRequestCreatingCourierWithoutFirstName() {
        Response response = createCourier(LOGIN, PASSWORD, null);
        response.then().assertThat().body("ok", equalTo(true)).and().statusCode(201);
    }
}

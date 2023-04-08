import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.*;

import static org.hamcrest.core.IsNull.notNullValue;

public class CourierLoginTests extends CourierProvider{
    private static final String LOGIN = "Flash";
    private static final String PASSWORD = "theFastest";
    private static final String FIRST_NAME = "Bartholomew Henry";
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        createCourier(LOGIN, PASSWORD, FIRST_NAME);
    }

    @After
    public void deleteData(){
        deleteCourier(loginCourier(LOGIN, PASSWORD, null)
                .then()
                .extract()
                .body().path("id"));
    }

    @Test
    @DisplayName("200 OK: successful courier login")
    public void successfulCourierLogin(){
        Response response = loginCourier(LOGIN, PASSWORD, null);
        response.then().assertThat().statusCode(200).and().body("id", notNullValue());
    }
}



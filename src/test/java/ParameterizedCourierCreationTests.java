import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(Parameterized.class)
public class ParameterizedCourierCreationTests extends CourierProvider{

    private final String login;
    private final String password;
    public ParameterizedCourierCreationTests(String login, String password){
        this.login = login;
        this.password = password;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Parameterized.Parameters(name = "login: {0}, password: {1}")
    public static Object[][] getCourierData(){
        return new Object[][]{
                {null, "theFastest"},
                {"Flash", null},
                {null, null}
        };
    }

    @Test()
    @DisplayName("400 Bad Request: creating couriers without required fields")
    public void badRequestCreatingCourierWithoutRequiredFields() {
        Response response = createCourier(login, password, "");
        System.out.println(response.body().asString());
        response.then().assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and().statusCode(400);
    }
}
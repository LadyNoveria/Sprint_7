import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(Parameterized.class)
public class ParameterizedCourierLoginTests extends CourierProvider{
    private final String login;
    private final String password;
    private final String messageError;
    private final int code;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    public ParameterizedCourierLoginTests(String login, String password, String messageError, int code){
        this.login = login;
        this.password = password;
        this.messageError = messageError;
        this.code = code;
    }

    @Parameterized.Parameters(name = "login: {0}, password: {1}")
    public static Object[][] getCourierData(){
        return new Object[][]{
                {null, "theFastest", "Недостаточно данных для входа", 400},
                {"Flash", "", "Недостаточно данных для входа", 400},
                {null, "", "Недостаточно данных для входа", 400},
                {"login", "theFastest", "Учетная запись не найдена", 404},
                {"Flash", "password", "Учетная запись не найдена", 404},
                {"ThirdFlash", "onlyTheThird", "Учетная запись не найдена", 404}
        };
    }

    @Test
    @DisplayName("400 Bad Request: creating couriers without required fields")
    public void badRequestCourierLoginWithoutRequiredFields(){
        Response response = loginCourier(login, password, null);
        response.then().assertThat().body("message", equalTo(messageError))
                .and().statusCode(code);
    }
}

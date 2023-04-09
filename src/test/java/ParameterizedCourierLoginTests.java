import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(Parameterized.class)
public class ParameterizedCourierLoginTests {
    private final String login;
    private final String password;
    private final String messageError;
    private final int code;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
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
                {null, "theFastest", "Недостаточно данных для входа", SC_BAD_REQUEST},
                {"Flash", "", "Недостаточно данных для входа", SC_BAD_REQUEST},
                {null, "", "Недостаточно данных для входа", SC_BAD_REQUEST},
                {"login", "theFastest", "Учетная запись не найдена", SC_NOT_FOUND},
                {"Flash", "password", "Учетная запись не найдена", SC_NOT_FOUND},
                {"ThirdFlash", "onlyTheThird", "Учетная запись не найдена", SC_NOT_FOUND}
        };
    }

    @Test
    @DisplayName("400 Bad Request: creating couriers without required fields")
    public void badRequestCourierLoginWithoutRequiredFields(){
        Response response = courierClient.login(new CourierCreds(login, password));
        response.then().assertThat().body("message", equalTo(messageError))
                .and().statusCode(code);
    }
}

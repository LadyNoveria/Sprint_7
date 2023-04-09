import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.IsNull.notNullValue;

public class CourierLoginTests {

    private CourierClient courierClient;
    private Courier courier;

    @Before
    public void setUp() {
        courier = Courier.courierGenerator();
        courierClient = new CourierClient();
        courierClient.create(courier);
    }

    @Test
    @DisplayName("200 OK: successful courier login")
    public void successfulCourierLogin(){
        Response response = courierClient.login(CourierCreds.getCredsFrom(courier));
        response.then().assertThat().statusCode(SC_OK).and().body("id", notNullValue());
    }

    @After
    public void deleteData(){
        courierClient.delete(courierClient.login(CourierCreds.getCredsFrom(courier))
                .then()
                .extract()
                .body().path("id"));
    }
}



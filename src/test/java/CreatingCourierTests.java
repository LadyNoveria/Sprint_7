import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreatingCourierTests {

    private CourierClient courierClient;
    private Courier courier;

    @Before
    public void setUp() {
        courier = Courier.courierGenerator();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("201 OK: Successful courier creation")
    public void successfulCourierCreation() {
        Response response = courierClient.create(courier);
        response.then().assertThat().body("ok", equalTo(true)).and().statusCode(SC_CREATED);
    }

    @Test
    @DisplayName("409 Сonflict: creating identical couriers")
    public void conflictCreatingIdenticalCouriers() {
        courierClient.create(courier);
        Response response = courierClient.create(courier);
        response.then().assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and().statusCode(409);
    }

    @Test
    @DisplayName("409 Сonflict: creating identical couriers")
    public void conflictCreatingCouriersWithTheSameLogins() {
        courierClient.create(courier);
        Response response = courierClient.create(new Courier(courier.getLogin(), "password", "First name"));
        response.then().assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and().statusCode(409);
    }

    @Test
    @DisplayName("200 OK: creating couriers without first Name")
    public void badRequestCreatingCourierWithoutFirstName() {
        Response response = courierClient.create(new Courier(courier.getLogin(), courier.getPassword(), null));
        response.then().assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and().statusCode(SC_BAD_REQUEST);
    }

    @After
    public void tearDown() {
        courierClient.delete(courierClient.login(CourierCreds.getCredsFrom(courier))
                .then()
                .extract()
                .body().path("id"));
    }
}

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationTests {

    private final String[] color;
    private OrderClient orderClient;
    private OrderRequest orderRequest;

    public OrderCreationTests(String[] color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
        orderRequest = OrderRequest.orderGenerator();
    }

    @Parameterized.Parameters()
    public static Object[][] getOrderData(){
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {null}
        };
    }

    @Test
    @DisplayName("201 Created: successful order creation")
    public void successfulOrderCreation(){
        orderRequest.setColor(color);
        Response response = orderClient.create(orderRequest);
        response.then().assertThat().body("track", notNullValue()).and().statusCode(201);
    }
}

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationTests extends CourierProvider{

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] colorOptional;

    public OrderCreationTests(String firstName, String lastName, String address, String metroStation, String phone,
                              int rentTime, String deliveryDate, String comment, String[] colorOptional) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colorOptional = colorOptional;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Parameterized.Parameters()
    public static Object[][] getOrderData(){
        return new Object[][]{
                {"Bartholomew Henry", "Allen", "Central City", "7", "+1233122234", 5,
                        "2023-04-06", "My name is Barry Allen and I am the fastest person on earth.", new String[]{"BLACK"}},
                {"Jason Peter", "Garrick", "Earth-2, Keystone City", "11", "+123", 1, "2023-05-05", "No comments",
                        new String[]{"GREY"}},
                {"Wally", "West", "Central City", "1", "+1111111111", 3, "2023-12-10", "What to say? I am a hero.",
                        new String[]{"BLACK", "GREY"}},
                {"Iris", "West Allen", "Central City", "7", "+1234567890", 5, "2023-04-06",
                        "I'd rather run with you than be without you.", null}
        };
    }

    @Test
    @DisplayName("201 Created: successful order creation")
    public void successfulOrderCreation(){
        OrderCreationDto order = new OrderCreationDto(firstName, lastName, address, metroStation, phone,
                rentTime, deliveryDate, comment, colorOptional);
        Response response = orderCreation(order);
        response.then().assertThat().body("track", notNullValue()).and().statusCode(201);
    }
}

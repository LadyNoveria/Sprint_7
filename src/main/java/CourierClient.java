import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    private static final String CREATE_PATH = "api/v1/courier";
    private static final String LOGIN_PATH = "api/v1/courier/login";
    private static final String DELETE_PATH = "api/v1/courier/%s";

    public CourierClient(){
        RestAssured.baseURI = BASE_URI;
    }
    @Step("Create courier")
    public Response create(Courier courier){
        return given()
                .header("Content-type","application/json")
                .body(courier)
                .when()
                .post(CREATE_PATH);
    }

    @Step("Login courier")
    public Response login(CourierCreds courierCreds){
        return given()
                .header("Content-type","application/json")
                .body(courierCreds)
                .when()
                .post(LOGIN_PATH);
    }

    @Step("Delete courier")
    public void delete(int id){
        given().header("Content-type","application/json").delete(DELETE_PATH + id);
    }
}

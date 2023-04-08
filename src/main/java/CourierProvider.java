import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class CourierProvider {

    @Step("Create courier")
    public Response createCourier(String login, String password, String firstName){
        return executePost(getCourier(login, password, firstName), "api/v1/courier");
    }

    @Step("Login Courier")
    public Response loginCourier(String login, String password, String firstName) {
        return executePost(getCourier(login, password, firstName), "api/v1/courier/login");
    }

    @Step("Delete Courier")
    public void deleteCourier(int id) {
        given().header("Content-type","application/json").delete(getIdForDelete(id));
    }

    @Step("Order Creation")
    public Response orderCreation(OrderCreationDto order) {
        return executePost(order, "api/v1/orders");
    }

    @Step("Return Courier")
    public CreateCourierDto getCourier(String login, String password, String firstName) {
        return new CreateCourierDto(login, password, firstName);
    }

    @Step("Return handle for delete")
    public String getIdForDelete(int id) {
        return format("api/v1/courier/%s", id);
    }

    @Step
    public OrderListDto getOrderList(OrderListFilter filter){
        return given().header("Content-type","application/json")
                .formParams(getOrderListParam(filter))
                .get("api/v1/orders").body().as(OrderListDto.class);
    }

    @Step("Execute Post")
    private <T> Response executePost(T body, String handle){
        return given()
                .header("Content-type","application/json")
                .body(body)
                .when()
                .post(handle);
    }

    private Map<String, String> getOrderListParam(OrderListFilter filter){
        HashMap<String, String> map = new HashMap<>();
        map.computeIfAbsent("courierId", k -> String.valueOf(filter.getCourierId()));
        map.computeIfAbsent("nearestStation", k -> filter.getNearestStation());
        map.computeIfAbsent("limit", k -> String.valueOf(filter.getLimit()));
        map.computeIfAbsent("page", k -> String.valueOf(filter.getPage()));
        return map;
    }

}

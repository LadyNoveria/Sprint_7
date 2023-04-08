import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class OrderListTests extends CourierProvider {
    //Список заказов
    //Проверь, что в тело ответа возвращается список заказов.
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }
    //new OrderListFilter(courierId, null, null, null)
    //new OrderListFilter(courierId, nearestStation, null, null)
    //new OrderListFilter(courierId, nearestStation, limit, null)
    //new OrderListFilter(courierId, nearestStation, limit, page)

    //GET /api/v1/orders

    //созать курьера
    //залогиниться и получить его id
    //создать заказы 2 штуки
    //сохранить их track из боди
    //найти id по трекам с помощью метода Получить заказ по его номеру GET /api/v1/orders/track с параметром t (track)
    // GET http://qa-scooter.praktikum-services.ru/api/v1/orders/track?t=352095
    //принять заказ PUT /api/v1/orders/accept/:id, в качестве параметров предаются courierId и id (номер заказа)
    // PUT http://qa-scooter.praktikum-services.ru/api/v1/orders/accept/136280?courierId=178786 (id заказа?id курьера)
    //завершить заказ PUT http://qa-scooter.praktikum-services.ru/api/v1/orders/finish/136280 (id заказа)
    //удалить курьера




}

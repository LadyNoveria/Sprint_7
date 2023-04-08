
public class OrderListDto {
    private Orders orders;
    private PageInfo pageInfo;
    private AvailableStations availableStations;

    public OrderListDto(Orders orders, PageInfo pageInfo, AvailableStations availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public OrderListDto(){}

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public AvailableStations getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(AvailableStations availableStations) {
        this.availableStations = availableStations;
    }
}

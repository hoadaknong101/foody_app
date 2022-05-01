package hcmute.edu.vn.phamdinhquochoa.foodyapp.beans;

public class OrderDetail {
    private Integer orderId;
    private Integer foodId;
    private Integer size;
    private Double price;

    public OrderDetail() {
    }

    public OrderDetail(Integer orderId, Integer foodId, Integer size, Double price) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.size = size;
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId=" + orderId +
                ", foodId=" + foodId +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}

package hcmute.edu.vn.phamdinhquochoa.foodyapp.beans;

public class Cart {
    private Integer id;
    private Integer foodId;
    private Integer userId;
    private Double total;

    public Cart(){

    }
    public Cart(Integer id, Integer foodId, Integer userId, Double total) {
        this.id = id;
        this.foodId = foodId;
        this.userId = userId;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", foodId=" + foodId +
                ", userId=" + userId +
                ", total=" + total +
                '}';
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}

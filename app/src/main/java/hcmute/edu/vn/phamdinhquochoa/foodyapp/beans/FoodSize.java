package hcmute.edu.vn.phamdinhquochoa.foodyapp.beans;

import java.io.Serializable;

public class FoodSize implements Serializable {
    private Integer foodId;
    private Integer size;
    private Double price;

    public FoodSize() {
    }

    public FoodSize(Integer foodId, Integer size, Double price) {
        this.foodId = foodId;
        this.size = size;
        this.price = price;
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
}

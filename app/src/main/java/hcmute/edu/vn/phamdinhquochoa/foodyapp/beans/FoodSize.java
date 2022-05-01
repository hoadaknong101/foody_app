package hcmute.edu.vn.phamdinhquochoa.foodyapp.beans;

import java.io.Serializable;

public class FoodSize implements Serializable {
    private Integer foodId;
    private Integer size;
    private Double price;
    private Boolean isSave;

    public FoodSize() {
    }

    public FoodSize(Integer foodId, Integer size, Double price, Boolean isSave) {
        this.foodId = foodId;
        this.size = size;
        this.price = price;
        this.isSave = isSave;
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

    public Boolean getSave() {
        return isSave;
    }

    public void setSave(Boolean save) {
        isSave = save;
    }

    @Override
    public String toString() {
        return "FoodSize{" +
                "foodId=" + foodId +
                ", size=" + size +
                ", price=" + price +
                ", isSave=" + isSave +
                '}';
    }
}

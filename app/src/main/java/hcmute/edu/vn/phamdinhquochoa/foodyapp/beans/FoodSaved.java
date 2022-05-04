package hcmute.edu.vn.phamdinhquochoa.foodyapp.beans;

public class FoodSaved {
    private Integer foodId;
    private Integer size;
    private Integer userId;

    public FoodSaved(Integer foodId, Integer size, Integer userId){
        this.foodId = foodId;
        this.size = size;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

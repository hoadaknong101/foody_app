package hcmute.edu.vn.phamdinhquochoa.foodyapp.beans;

import java.io.Serializable;

public class Food implements Serializable {

    private Integer id;
    private String name;
    private Double price;
    private Double rate;
    private String description;

    public Food(){

    }

    public Food(Integer id, String name, Double price, Double rate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.description = description;
    }

    public Food(Integer id, String name, Double price, Double rate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                '}';
    }
}

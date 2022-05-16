package hcmute.edu.vn.phamdinhquochoa.foodyapp.beans;

import java.io.Serializable;

public class Order implements Serializable {

    private Integer id;
    private Integer userId;
    private String address;
    private String dateOfOrder;
    private Double totalValue;
    private String status;


    public Order(Integer id, Integer userId, String address, String dateOfOrder, Double totalValue, String status) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.dateOfOrder = dateOfOrder;
        this.totalValue = totalValue;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

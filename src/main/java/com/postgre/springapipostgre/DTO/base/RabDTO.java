package com.postgre.springapipostgre.DTO.base;

public class RabDTO {
    private Long id;
    private String customer;
    private String product;
    private boolean isPln;
    private long revenue;
    private long cost;
    private String costDesc;
    private String type;
    private Long pksId; // Optional: You can keep the PKS ID for reference

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public boolean isPln() {
        return isPln;
    }

    public void setPln(boolean pln) {
        isPln = pln;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getCostDesc() {
        return costDesc;
    }

    public void setCostDesc(String costDesc) {
        this.costDesc = costDesc;
    }

    public Long getPksId() {
        return pksId;
    }

    public void setPksId(Long pksId) {
        this.pksId = pksId;
    }
}

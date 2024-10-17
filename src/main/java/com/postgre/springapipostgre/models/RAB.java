package com.postgre.springapipostgre.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rabs")
public class RAB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer;
    private String product;
    private boolean isPln = false;
    private long revenue;
    private long cost;
    private String costDesc;

    @ManyToOne(optional = true)
    @JoinColumn(name = "pks_id", nullable = true)
    private PKS pks;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PKS getPks() {
        return pks;
    }

    public void setPks(PKS pks) {
        this.pks = pks;
    }
}

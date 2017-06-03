package com.springapp.mvc.entities;

import java.util.List;

public class Order {

    private String good;
    private OrderStates orderStates;
    private long userId;
    private long id;

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public OrderStates getOrderStates() {
        return orderStates;
    }

    public void setOrderStates(OrderStates orderStates) {
        this.orderStates = orderStates;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

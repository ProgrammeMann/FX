package com.springapp.mvc.common.hibernateEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne                                  // определяет отношение многие к одному
            (cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "h_order_item")
    private OrderItem orderItem;
    private String city;
    private String postCode;
    private String address;
    private String orderStates;
    @Column(columnDefinition = "TEXT")
    private String comments;

    public Order(Customer customer, OrderItem orderItem, String city, String postCode, String address, String orderStates, String comments) {
        this.customer = customer;
        this.orderItem = orderItem;
        this.city = city;
        this.postCode = postCode;
        this.address = address;
        this.orderStates = orderStates;
        this.comments = comments;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOrderStates() {
        return orderStates;
    }

    public void setOrderStates(String orderStates) {
        this.orderStates = orderStates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
}

package com.springapp.mvc.common.hibernateEntity;

import javax.persistence.*;

@Entity
@Table(name = "h_stores")
public class Store {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;

    private String address;

    public Store() {

    }

    public Store(String city, String address) {
        this.city = city;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

package com.springapp.mvc.entities;

public class Store {
    private Long id;
    private String address;
    private String city;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String street) {
        this.address = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Store{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

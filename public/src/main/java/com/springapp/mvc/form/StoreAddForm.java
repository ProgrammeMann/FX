package com.springapp.mvc.form;

import org.hibernate.validator.constraints.NotEmpty;

public class StoreAddForm {

    @NotEmpty
    private String address;
    @NotEmpty
    private String city;

    public StoreAddForm() {

    }

    public StoreAddForm(String address, String city) {
        this.address = address;
        this.city = city;
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

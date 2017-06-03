package com.springapp.mvc.form;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * Checkout form. All fields except the comment are required
 */
public class CheckoutForm {


    @NotEmpty(message = "You should enter your city")
    private String city;

    @NotEmpty(message = "You should enter your postal code")
    private String postCode;

    @NotEmpty(message = "You should enter your address")
    private String address;

    private String comments;

    public CheckoutForm() {
    }

    public CheckoutForm(String city, String postCode, String address, String comments) {
        this.city = city;
        this.postCode = postCode;
        this.address = address;
        this.comments = comments;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
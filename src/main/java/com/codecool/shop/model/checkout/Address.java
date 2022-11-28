package com.codecool.shop.model.checkout;

public class Address {
    private int userId;
    private String country;
    private String city;
    private int zipCode;
    private String address;

    public Address(int userId, String country, String city, int zipCode, String address) {
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

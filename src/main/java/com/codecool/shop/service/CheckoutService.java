package com.codecool.shop.service;

import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.UserDao;

public class CheckoutService {
    private UserDao userDao;
    private AddressDao addressDao;


    public CheckoutService(UserDao userDao, AddressDao addressDao) {
        this.userDao = userDao;
        this.addressDao = addressDao;
    }
}

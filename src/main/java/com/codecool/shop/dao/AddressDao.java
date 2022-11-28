package com.codecool.shop.dao;

import com.codecool.shop.model.checkout.Address;
import java.util.List;

public interface AddressDao {
    void add(Address address);

    Address find(int userId);

    void remove(int userId);

    List<Address> getAll();
}

package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Supplier amazon = new Supplier("Amazon", "We sell everything, even a boat.");
        supplierDataStore.add(amazon);
        Supplier johajobt = new Supplier("Jó Ha Jó Bt.", "Jó hajók");
        supplierDataStore.add(johajobt);
        Supplier mahart = new Supplier("Mahart Zrt.", "We are hungarian.");
        supplierDataStore.add(mahart);
        Supplier csokishajobt = new Supplier("Csokis Hajó Bt.", "We are selling chocolate flavour boats. Yummy.");
        supplierDataStore.add(csokishajobt);
        Supplier laciEsTarsaiBt = new Supplier("Laci és Társai Bt", "We are selling boats. One boat to be exact. A BIG ASS BOAT.");
        supplierDataStore.add(laciEsTarsaiBt);

        //setting up a new product category
        ProductCategory big = new ProductCategory("Big", "Ship", "This category contains the big size ships.");
        ProductCategory medium = new ProductCategory("Medium", "Ship", "This category contains the medium size ships.");
        ProductCategory small = new ProductCategory("Small", "Ship", "This category contains the small size ships.");
        productCategoryDataStore.add(big);
        productCategoryDataStore.add(medium);
        productCategoryDataStore.add(small);

        Product bigBoat = new Product("GIGA MEGA BOAT", new BigDecimal("49999"), "USD", "GIGAMEGA BOAT! WANT TO COMPENSATE? THIS BOAT IS SO BIG IT IS NOT EVEN FITTING FOR ONE PICTURE", big, amazon);
        Product mediumBoat = new Product("Loser Normal Boat", new BigDecimal("479"), "USD", "Everyday boat you see on the sea. Nothing special...", medium, johajobt);
        Product smallBoat = new Product("Paddle Boat", new BigDecimal("89"), "USD", "No motor, no comfort, no rooms and no room service. It's a ship for beginner ship users.", small, mahart);
        Product chocolateBoat = new Product("Chocolate Boat", new BigDecimal("12"), "USD", "This is a chocholateboat. You can taste it.", medium, csokishajobt);
        Product bigAssBoat = new Product("BIG ASS BOAT", new BigDecimal("1999"), "USD", "This is a BIG ASS BOAT, the BEST boat money can buy. Do not hesitate.", big, laciEsTarsaiBt);
        Product deluxeBoat = new Product("De Luxe", new BigDecimal("420"), "USD", "Famous painting of Ha Jónás", medium, mahart);
        Product titanicShip = new Product("Iceberg Proof Ship", new BigDecimal("999"), "USD", "It's like Titanic but better! [Emergency boats not included!]", big, johajobt);
        Product shipShipper = new Product("Ship Shipping Ship", new BigDecimal("1999"), "USD", "The all the time waited Ship Shipping ship is here! [Small boat not included!]", big, mahart);


        productDataStore.add(bigBoat);
        productDataStore.add(mediumBoat);
        productDataStore.add(smallBoat);
        productDataStore.add(chocolateBoat);
        productDataStore.add(bigAssBoat);
        productDataStore.add(deluxeBoat);
        productDataStore.add(titanicShip);
        productDataStore.add(shipShipper);

    }
}

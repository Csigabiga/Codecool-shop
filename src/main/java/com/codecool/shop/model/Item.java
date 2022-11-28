package com.codecool.shop.model;

import java.math.BigDecimal;

public class Item {

    Product product;
    int productId;
    int quantity;
    BigDecimal totalPrice = BigDecimal.ZERO;


    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = getTotalPrice();
        this.productId = product.id;
    }
    public BigDecimal getTotalPrice(){
        return this.totalPrice = product.getDefaultPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "Item{" +
                "product=" + product +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Product getProduct() {
        return product;
    }

}

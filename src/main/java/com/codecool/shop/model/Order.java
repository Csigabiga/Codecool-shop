package com.codecool.shop.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<Item> cart;

    private static Order instance = null;

    private Order() {
        this.cart = new ArrayList<>();
    }

    public List<Item> getCart() {return cart;}

    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item cartItem:
             cart) {
            totalPrice = totalPrice.add(cartItem.totalPrice.multiply(BigDecimal.valueOf(cartItem.quantity)));
        }
        return totalPrice;
    }

    public void addToCart(Product product, Integer quantity){
        for (Item cartItem:
             cart) {
            if (cartItem.product == product){
                cartItem.quantity += quantity;
                cartItem.totalPrice = cartItem.getTotalPrice();
                return;
            }
        }
        cart.add(new Item(product,quantity));
    }
    public void changeProductQuantity( Product product, Integer quantity){
        for (Item cartItem:
                cart) {
            if(cartItem.product == product){
                if (quantity == 0){
                    cart.remove(cartItem);
                    return;
                }
                cartItem.quantity = quantity;
                cartItem.totalPrice = cartItem.getTotalPrice();
            }

        }
    }
    public void deleteItemFromOrder(int id){
        for (Item cartItem:
             cart) {
            if (cartItem.productId == id){
                cart.remove(cartItem);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "cart=" + cart +
                '}';
    }
}

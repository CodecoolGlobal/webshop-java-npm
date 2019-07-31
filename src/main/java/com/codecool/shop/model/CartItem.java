package com.codecool.shop.model;

public class CartItem{
    Product product;
    private int quantity = 1;

    public CartItem(Product product){
        this.product = product;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void add(Product product){
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }
}

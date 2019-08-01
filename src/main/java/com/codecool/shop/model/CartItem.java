package com.codecool.shop.model;

public class CartItem{
    Product product;
    private int quantity = 1;
    private float sumPrice = 0f;

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

    public Product getProduct() {
        return product;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }

    public float getSumPrice() {
        return sumPrice;
    }
}

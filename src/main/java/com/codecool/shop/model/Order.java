package com.codecool.shop.model;

import com.google.gson.annotations.Expose;

import java.util.Collection;

public class Order{
    private  int id;
    @Expose
    private String name;
    @Expose
    private String email;
    @Expose
    private String phoneNumber;
    @Expose
    private String billingAddress;
    @Expose
    private String shippingAddress;
    @Expose
    private Collection<CartItem> cart;
    @Expose
    private float orderTotalPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Collection<CartItem> getCart() {
        return cart;
    }

    public void setCart(Collection<CartItem> cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderTotalPrice() {
        orderTotalPrice = 0f;
        for (CartItem cartItem : cart) {
            orderTotalPrice += cartItem.getSumPrice();
        }
    }
}

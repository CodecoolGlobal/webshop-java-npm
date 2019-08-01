package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.util.Collection;

public interface CartDao {
    void add(Product product);
    //Product find(int id);
    void remove(int id);
    Collection<CartItem> getCart();
    int getCartSize();
}

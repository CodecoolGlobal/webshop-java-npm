package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.sql.SQLException;
import java.util.Collection;

public interface CartDao {
    void add(Product product) throws SQLException;
    //Product find(int id);
    void remove(int id);
    Collection<CartItem> getCart() throws SQLException;
    int getCartSize() throws SQLException;
    Float getTotalPrice() throws SQLException;
    void clearCart();
}

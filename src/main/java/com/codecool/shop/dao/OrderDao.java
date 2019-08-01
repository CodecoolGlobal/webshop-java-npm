package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.Collection;

public interface OrderDao {
    void add(Order order);
    void remove(int id);
    Order getBy(int id);
    Collection<Order> getAll();
}

package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.sql.SQLException;
import java.util.Collection;

public interface OrderDao {
    void add(Order order) throws SQLException;
    void remove(int id) throws SQLException;
    Order getBy(int id) throws SQLException;
    Collection<Order> getAll() throws SQLException;
}

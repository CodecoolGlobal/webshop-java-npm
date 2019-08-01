package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Order;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderDaoMem implements OrderDao {
    private static OrderDaoMem instance = null;
    private Map<Integer,Order> data = new HashMap<>();

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        order.setId(data.size() + 1);
        data.put(order.getId(),order);
    }


    @Override
    public void remove(int id) {
        data.remove(id);
    }

    @Override
    public Order getBy(int id) {
        return data.get(id);
    }

    @Override
    public Collection<Order> getAll() {
        return data.values();
    }
}


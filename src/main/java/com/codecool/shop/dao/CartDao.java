package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;

public interface CartDao {
    void add(Product product);
    void batchAdd(Product product, int number);
    Product find(Product product);
    void remove(Product product);
    void batchRemove(Product product, int number);
    List<Product> getCart();
}

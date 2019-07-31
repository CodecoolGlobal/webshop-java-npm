package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {

    private Map<Integer, CartItem> data = new HashMap<>();
    private static CartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        if (data.containsKey(product.getId())) {
            data.get(product.getId()).add(product);
        } else {
            CartItem cartItem = new CartItem(product);
            data.put(product.getId(), cartItem);
        }
    }

//    @Override
//    public Product find(int id) { return data.entrySet().stream().filter(t -> t.getId() == id).findFirst().orElse(null); }

    @Override
    public void remove(int id) {
        data.remove(id);
    }

    public void setQuantity(int id, int quantity) {
        if (quantity == 0) {
            data.remove(id);
        } else {
            data.get(id).setQuantity(quantity);
        }
    }


    @Override
    public Collection<CartItem> getCart() {
        return data.values();
    }
}

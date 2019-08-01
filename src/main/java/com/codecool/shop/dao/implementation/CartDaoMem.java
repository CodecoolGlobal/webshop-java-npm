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
    private Float totalPrice = 0F;

    /* A private Constructor prevents any other class from instantiating.
     */
    public CartDaoMem() {
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


    @Override
    public void remove(int id) {
        data.remove(id);
    }


    @Override
    public Collection<CartItem> getCart() {
        return data.values();
    }

    public int getCartSize(){
        int cartSize = 0;
        for(CartItem cartItem : data.values()){
            cartSize += cartItem.getQuantity();
        }return cartSize;
    }

    public Float getTotalPrice(){
        totalPrice = 0F;
        for(CartItem cartItem : data.values()){
            totalPrice += cartItem.getSumPrice();
        }return totalPrice;
    }
}

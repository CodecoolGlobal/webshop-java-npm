package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.util.Collection;

public class CartDaoJdbc implements CartDao {

    private static CartDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private CartDaoJdbc() {
    }

    public static CartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Collection<CartItem> getCart() {
        return null;
    }

    @Override
    public int getCartSize() {
        return 0;
    }

    @Override
    public Float getTotalPrice() {
        return null;
    }

    @Override
    public void clearCart() {

    }
}

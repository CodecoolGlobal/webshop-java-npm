package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<Product> data = new ArrayList<>();
    private static CartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }
    @Override
    public void add(Product product) {
        product.setId(data.size() + 1);
        data.add(product);
    }

    @Override
    public void batchAdd(Product product, int number) {
    for(int i = 0; i < number; i++){
        product.setId(data.size()+1);
        data.add(product);
    }
    }

    @Override
    public Product find(Product product) {
        data.stream().findFirst()
    }

    @Override
    public void remove(Product product) {
        data.remove(product);
    }

    @Override
    public void batchRemove(Product product, int number) {
    for (int i = 0; i < number; i++){
        data.remove(product);
    }
    }

    @Override
    public List<Product> getCart() {
        return data;
    }
}

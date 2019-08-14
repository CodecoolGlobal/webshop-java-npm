package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc extends DatabaseAccess implements ProductCategoryDao {

    private Connection connection =  getConnection();

    @Deprecated
    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product_category WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new ProductCategory(resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("description"));
    }

    @Deprecated
    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product_category");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<ProductCategory> productCategories = new ArrayList<>();

        while (resultSet.next()) {
            ProductCategory productCategory = new ProductCategory(resultSet.getString("name"),
                    resultSet.getString("department"), resultSet.getString("description"));
            productCategories.add(productCategory);
        }
        return productCategories;
    }
}

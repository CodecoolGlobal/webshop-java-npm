package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc extends DatabaseAccess implements ProductCategoryDao {

    private Connection connection =  getConnection();
    private static ProductCategoryDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Deprecated
    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product_category WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            ProductCategory productCategory = new ProductCategory(resultSet.getString("name"),
                    resultSet.getString("department"),
                    resultSet.getString("description"));
            productCategory.setId(id);
            return productCategory;
        }
    }

    @Deprecated
    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product_category")) {
            resultSet = preparedStatement.executeQuery();


            List<ProductCategory> productCategories = new ArrayList<>();

            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("department"), resultSet.getString("description"));
                productCategory.setId(resultSet.getInt("id"));
                productCategories.add(productCategory);
            }
            return productCategories;
        }
    }
}

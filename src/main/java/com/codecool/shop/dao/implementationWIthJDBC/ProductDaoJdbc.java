package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoJdbc extends DatabaseAccess implements ProductDao  {
    Connection con = getConnection();

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("" +
                "SELECT id, name, price, currency, description, pc.name," +
                "pc.department, pc.description, s.name, s.description FROM product " +
                "INNER JOIN product_category pc on product.category_id = pc.id " +
                "INNER JOIN supplier s on product.supplier_id = s.id");
        ResultSet rs =  preparedStatement.executeQuery();
        while (rs.next()){
            Product product = new Product(
                    rs.getString("name"),
                    rs.getFloat("price"),
                    rs.getString("currency"),
                    rs.getString("description"),
                    new ProductCategory(rs.getString("pc.name"),
                            rs.getString("pc.department"),rs.getString("pc.description")  ),
                    new Supplier(rs.getString("s.name"), rs.getString("s.description")));
        }

    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}

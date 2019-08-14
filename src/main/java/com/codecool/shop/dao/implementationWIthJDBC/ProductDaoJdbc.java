package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc extends DatabaseAccess implements ProductDao  {
    private Connection con = getConnection();

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
                "SELECT product.name, price, currency, product.description, pc.name," +
                "pc.department, pc.description, s.name, s.description FROM product " +
                "INNER JOIN product_category pc on product.category_id = pc.id " +
                "INNER JOIN supplier s on product.supplier_id = s.id");
        ResultSet rs =  preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()){
            Product product = new Product(
                    rs.getString(1),
                    rs.getFloat(2),
                    rs.getString(3),
                    rs.getString(4),
                    new ProductCategory(rs.getString(5),
                            rs.getString(6),rs.getString(7)  ),
                    new Supplier(rs.getString(8), rs.getString(9)));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws SQLException {
        int supplierId = supplier.getId();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM product " +
                "INNER JOIN PRODUCT_CATEGORY PC ON PRODUCT.CATEGORY_ID = PC.ID " +
                "INNER JOIN supplier s ON product.supplier_id = s.id  " +
                "WHERE supplier_id = ?");
        ps.setInt(1,supplierId);
        ResultSet rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()){
            Product product = new Product(
                    rs.getString(1),
                    rs.getFloat(2),
                    rs.getString(3),
                    rs.getString(4),
                    new ProductCategory(rs.getString(5),
                            rs.getString(6),rs.getString(7)  ),
                    new Supplier(rs.getString(8), rs.getString(9)));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        int productCategoryId = productCategory.getId();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM product " +
                "INNER JOIN PRODUCT_CATEGORY PC ON PRODUCT.CATEGORY_ID = PC.ID " +
                "INNER JOIN supplier s ON product.supplier_id = s.id  " +
                "WHERE pc.id = ?");
        ps.setInt(1,productCategoryId);
        ResultSet rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()){
            Product product = new Product(
                    rs.getString(1),
                    rs.getFloat(2),
                    rs.getString(3),
                    rs.getString(4),
                    new ProductCategory(rs.getString(5),
                            rs.getString(6),rs.getString(7)  ),
                    new Supplier(rs.getString(8), rs.getString(9)));
            products.add(product);
        }
        return products;
    }
}

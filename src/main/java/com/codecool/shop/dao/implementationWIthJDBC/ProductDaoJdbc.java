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

    private static ProductDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

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

    private String makeStatement(){
        String preparable = "SELECT product.id AS product_id, product.name AS product_name, price AS product_price," +
                " currency AS product_currency, product.description AS product_description," +
                " pc.id AS product_category_id, pc.name AS product_category_name," +
                "pc.department AS product_category_department, pc.description AS product_category_description," +
                "s.id AS supplier_id, s.name AS supplier_name, s.description AS supplier_description FROM product " +
                "INNER JOIN product_category pc on product.category_id = pc.id " +
                "INNER JOIN supplier s on product.supplier_id = s.id";
        return preparable;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(makeStatement())){
            return getProducts(preparedStatement);
        }
    }

    private List<Product> getProducts(PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs;
        rs = preparedStatement.executeQuery();

        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            Supplier supplier = new Supplier(rs.getString("supplier_name"),
                    rs.getString("supplier_description"));
            supplier.setId(rs.getInt("supplier_id"));
            ProductCategory productCategory = new ProductCategory(
                    rs.getString("product_category_name"),
                            rs.getString("product_category_department"),
                            rs.getString("product_category_description"));
            productCategory.setId(rs.getInt("product_category_id"));
            Product product = new Product(rs.getString("product_name"),
                    rs.getFloat("product_price"),
                    rs.getString("product_currency"),
                    rs.getString("product_description"),
                    productCategory,
                    supplier);
            product.setId(rs.getInt("product_id"));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws SQLException {
        int supplierId = supplier.getId();
        try (PreparedStatement ps = con.prepareStatement(makeStatement().concat(" WHERE s.id = ?"))) {
            ps.setInt(1, supplierId);
            return getProducts(ps);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        int productCategoryId = productCategory.getId();
        try (PreparedStatement ps = con.prepareStatement(makeStatement().concat(" WHERE pc.id = ?"))) {
            ps.setInt(1, productCategoryId);
            return getProducts(ps);
        }
    }
}

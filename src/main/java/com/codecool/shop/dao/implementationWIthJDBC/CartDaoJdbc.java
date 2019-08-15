package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CartDaoJdbc extends DatabaseAccess implements CartDao {
    Connection con = getConnection();

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
    public void add(Product product) throws SQLException {
        //check if product is in cart
        int productId = product.getId();
        PreparedStatement ps = con.prepareStatement("SELECT quantity FROM cart" +
                " WHERE product_id = ?");
        ps.setInt(1,productId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE cart" +
                    " SET quantity = ? WHERE product_id = ?");
            preparedStatement.setInt(1,rs.getInt("quantity")+1);
            preparedStatement.setInt(2,productId);
            preparedStatement.executeUpdate();
            //TODO setSumPrice()
        }else {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO cart" +
                    "(quantity, product_id) VALUES (1,?)");
            preparedStatement.setInt(1,productId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Collection<CartItem> getCart() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(makeStatement() + " WHERE cart.id >= 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        return getCartItems(resultSet);
    }

    private String makeStatement(){
        return "SELECT cart.id AS cart_id, cart.quantity AS cart_quantity," +
                "cart.sum_price AS cart_sumprice, p.id AS product_id,"+
                "p.name AS product_name, p.price AS product_price,"+
                "p.currency AS product_currency, p.description AS product_description,"+
                "p.category_id AS product_category_id, pc.name AS product_category_name,"+
                "pc.description AS product_category_description,"+
                "pc.department AS product_category_department,s.name AS supplier_name,"+
                "s.description AS supplier_description, supplier_id AS supplier_id "+
                "FROM cart FULL OUTER JOIN product p on cart.product_id = p.id "+
                "INNER JOIN product_category pc on p.category_id = pc.id "+
                "INNER JOIN supplier s on p.supplier_id = s.id";
    }

    private Collection<CartItem> getCartItems(ResultSet resultSet) throws SQLException {
        Collection<CartItem> cartItems = new  ArrayList<>();
        while (resultSet.next()){
            Supplier supplier = new Supplier(resultSet.getString("supplier_name"),
                    resultSet.getString("supplier_description"));
            supplier.setId(resultSet.getInt("supplier_id"));
            ProductCategory productCategory = new ProductCategory(
                    resultSet.getString("product_category_name"),
                    resultSet.getString("product_category_department"),
                    resultSet.getString("product_category_description"));
            productCategory.setId(resultSet.getInt("product_category_id"));
            Product product = new Product(resultSet.getString("product_name"),
                    resultSet.getFloat("product_price"),
                    resultSet.getString("product_currency"),
                    resultSet.getString("product_description"),
                    productCategory,
                    supplier);
            product.setId(resultSet.getInt("product_id"));
            CartItem cartItem = new CartItem(product);
            cartItem.setQuantity(resultSet.getInt("cart_quantity"));
            cartItem.setSumPrice(resultSet.getInt("cart_quantity") * resultSet.getFloat("product_price"));
            cartItems.add(cartItem);
        }

    return cartItems;
    }

    @Override
    public int getCartSize() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(makeStatement() + " WHERE cart.id >= 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        Collection<CartItem> cartItems = getCartItems(resultSet);
        int totalQuantity = 0;
        for (CartItem cartItem: cartItems){
            totalQuantity += cartItem.getQuantity();
        }
        return totalQuantity;
    }

    @Override
    public Float getTotalPrice() {
        return null;
    }

    @Override
    public void clearCart() {

    }
}

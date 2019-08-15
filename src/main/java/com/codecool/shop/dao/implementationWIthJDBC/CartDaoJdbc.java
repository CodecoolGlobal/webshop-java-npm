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
import java.util.List;

public class CartDaoJdbc extends DatabaseAccess implements CartDao {
    private Connection connection = getConnection();

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
        PreparedStatement preparedStatementSelect = connection.prepareStatement("SELECT id from cart");
        ResultSet resultSet = preparedStatementSelect.executeQuery();
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into product (name,price,currency,description,category_id,supplier_id,cart_id)" +
                "VALUES (?,?,?,?,?,?,?)");
        preparedStatement.setString(1,product.getName());
        preparedStatement.setFloat(2,product.getPriceFloat());
        preparedStatement.setString(3,product.getDefaultCurrency().toString());
        preparedStatement.setInt(4,product.getProductCategory().getId());
        preparedStatement.setInt(5,product.getSupplier().getId());
        preparedStatement.setInt(6,resultSet.getInt(1));

    }

    @Override
    public void remove(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cart Where id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public Collection<CartItem> getCart() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM cart INNER JOIN product on cart.id = product.cart_id ");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<CartItem> cartItems = new ArrayList<>();
        while (resultSet.next()){
            ProductCategory productCategory = new ProductCategory(
                    resultSet.getString("name"),
                    resultSet.getString("department"),
                    resultSet.getString("description"));
            Supplier supplier = new Supplier(
                    resultSet.getString("name"),
                    resultSet.getString("description")
            );
            Product product = new Product(
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    resultSet.getString("currency"),
                    resultSet.getString("description"),
                    productCategory,
                    supplier
            );
            CartItem cartItem = new CartItem(product);
            cartItem.setQuantity(resultSet.getInt("quantity"));
            cartItem.setSumPrice(resultSet.getFloat("sum_price"));

            cartItems.add(cartItem);

        }
        return cartItems;
    }

    @Override
    public int getCartSize() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT sum(quantity) from cart");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getInt(1);
    }

    @Override
    public Float getTotalPrice() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT sum(sum_price) from cart");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getFloat(1);
    }

    @Override
    public void clearCart() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cart");
        preparedStatement.executeUpdate();
    }
}

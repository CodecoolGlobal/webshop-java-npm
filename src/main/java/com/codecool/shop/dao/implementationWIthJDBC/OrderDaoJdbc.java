package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderDaoJdbc extends DatabaseAccess implements OrderDao {

    private Connection connection = getConnection();

    @Override
    public void add(Order order) throws SQLException {
        String query = "INSERT INTO order (name, email, phone_number, billing_address, shipping_address) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, order.getName());
        preparedStatement.setString(2, order.getEmail());
        preparedStatement.setString(3, order.getPhoneNumber());
        preparedStatement.setString(4, order.getBillingAddress());
        preparedStatement.setString(5, order.getShippingAddress());
        preparedStatement.executeUpdate();
    }

    @Override
    public void remove(int id) throws SQLException {
        String query = "DELETE FROM order WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public Order getBy(int id) throws SQLException {
        String query = "SELECT * FROM order WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Order result = new Order(resultSet.getString("id"),
                    resultSet.getString("name"), resultSet.getString("email"),
                    resultSet.getString("phone_number"), resultSet.getString("billing_address"),
                    resultSet.getString("shipping_address"));
            return result;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Order> getAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM order");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Order> orders = new ArrayList<>();

        while (resultSet.next()) {
            Order order = new Order(resultSet.getString("id"),
                    resultSet.getString("name"), resultSet.getString("email"),
                    resultSet.getString("phone_number"), resultSet.getString("billing_address"),
                    resultSet.getString("shipping_address"));
            orders.add(order);
        }
        return orders;
    }
}


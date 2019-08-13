package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc extends DatabaseAccess implements SupplierDao {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/webshop";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR: Connection error.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Supplier supplier) {
    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()){

            Supplier supplier = new Supplier(
                    resultSet.getString("name"),
                    resultSet.getString("description")
            );
            suppliers.add(supplier);
        }return suppliers;

    }
}

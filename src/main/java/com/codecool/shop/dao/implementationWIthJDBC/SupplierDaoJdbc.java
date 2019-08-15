package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc extends DatabaseAccess implements SupplierDao {
    private Connection connection = getConnection();

    private static SupplierDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Deprecated
    @Override
    public void add(Supplier supplier) {
    }

    @Override
    public Supplier find(int id) throws SQLException {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier WHERE id=? ")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            Supplier supplier;
            while (resultSet.next()) {
                supplier = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
                supplier.setId(id);
                return supplier;
            }
        }
        return null;
    }

    @Deprecated
    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier")) {
            resultSet = preparedStatement.executeQuery();

            List<Supplier> suppliers = new ArrayList<>();
            while (resultSet.next()) {

                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                supplier.setId(resultSet.getInt("id"));
                suppliers.add(supplier);
            }
            return suppliers;
        }
    }
}

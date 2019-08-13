package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc extends DatabaseAccess implements SupplierDao {
    private Connection connection = getConnection();

    @Override
    public void add(Supplier supplier) {
    }

    @Override
    public Supplier find(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier WHERE id=? ");
    preparedStatement.setInt(1,id);
    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    Supplier supplier = new Supplier(resultSet.getString(2),resultSet.getString(3));
    return supplier;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() throws SQLException {
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

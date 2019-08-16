package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {

//    @Test
//    void KAMUcanGetAllSuppliersZSENI() throws SQLException {
//        //given
//        DbHelper.initDb();
//        SupplierDaoJdbc supplierAccessor = SupplierDaoJdbc.getInstance();
//        //when
//        List<Supplier> suppliers = supplierAccessor.getAll();
//        //then
//        for (int i = 0; i < suppliers.size()-1;i++){
//            assertEquals(suppliers.get(i), supplierAccessor.get(i));
//        }
//    }

    @Test
    void canGetAllSuppliers() throws SQLException {
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(new Supplier("Sony", "Consumer and professional electronics, gaming, entertainment and financial services"));
        supplierList.add(new Supplier("Nintendo", "Consumer electronics and video game company"));
        supplierList.add(new Supplier("Microsoft", "It develops, manufactures, licenses, supports and sells computer software, consumer electronics, personal computers, and related services"));
        SupplierDaoJdbc supp = SupplierDaoJdbc.getInstance();
        List<Supplier> supplierDaoJdbc = supp.getAll();
        for (int i = 0; i < supplierList.size()-1;i++){
            assertEquals(supplierList.get(i), supplierDaoJdbc.get(i));
        }
    }


    @Test
    void testSupplierGetAllFirstNotEqualsSecondJDBC() throws SQLException {
        SupplierDaoJdbc supplierDaoJdbc =  SupplierDaoJdbc.getInstance();
        assertNotEquals(supplierDaoJdbc.getAll().get(1).getName(), supplierDaoJdbc.getAll().get(0).getName());
    }

    @Test
    void testFindFirst() throws SQLException {
        Supplier supplier =  new Supplier("Sony", "Consumer and professional electronics, gaming, entertainment and financial services");
        SupplierDaoJdbc supplierDaoJdbc = SupplierDaoJdbc.getInstance();
        assertEquals(supplier.getName(), supplierDaoJdbc.find(1).getName());
    }

}
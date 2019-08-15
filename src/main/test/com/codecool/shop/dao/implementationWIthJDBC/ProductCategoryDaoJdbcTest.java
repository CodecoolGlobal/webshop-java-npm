package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoJdbcTest {

    @Test
    public void testFindCategory() throws SQLException {
        ProductCategory productCategory = new ProductCategory("Home Consoles", "Hardware", "A video game device that is primarily used for home gamers, as opposed to in arcades or some other commercial establishment");
        ProductCategoryDaoJdbc productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
        assertEquals(productCategory.getName(), productCategoryDaoJdbc.find(1).getName());
    }

    @Test
    public void testGetAll() throws SQLException {
        List<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(new ProductCategory("Home Consoles", "Hardware", "A video game device that is primarily used for home gamers, as opposed to in arcades or some other commercial establishment"));
        productCategories.add(new ProductCategory("Handheld Consoles", "Hardware", "They are smaller and portable, allowing people to carry them and play them at any time or place, along with microconsoles and dedicated consoles."));
        productCategories.add(new ProductCategory("Hybrid Consoles", "Hardware", "Can be used as both a stationary and portable device."));

        ProductCategoryDaoJdbc productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
        List<ProductCategory> productCategoryList = productCategoryDaoJdbc.getAll();

        for (int i = 0; i < productCategories.size() - 1; i++) {
            assertEquals(productCategories.get(i).getName(), productCategoryList.get(i).getName());
            assertEquals(productCategories.get(i).getDepartment(), productCategoryList.get(i).getDepartment());
            assertEquals(productCategories.get(i).getDescription(), productCategoryList.get(i).getDescription());
        }
    }
}
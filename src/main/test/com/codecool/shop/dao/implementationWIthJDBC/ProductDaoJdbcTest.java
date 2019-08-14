package com.codecool.shop.dao.implementationWIthJDBC;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDaoJdbcTest {


    @Test
    void testGetAllProducts() throws SQLException {
        //Create data structures
        List<Supplier> supplierDataStore = new ArrayList<>();
        Supplier sony = new Supplier("Sony", "Consumer and professional electronics, gaming, entertainment and financial services");
        supplierDataStore.add(sony);
        Supplier nintendo = new Supplier("Nintendo", "Consumer electronics and video game company");
        supplierDataStore.add(nintendo);
        Supplier microsoft = new Supplier("Microsoft", "It develops, manufactures, licenses, supports and sells computer software, consumer electronics, personal computers, and related services");
        supplierDataStore.add(microsoft);

        List<ProductCategory> productCategoryDataStore = new ArrayList<>();
        ProductCategory homeConsoles = new ProductCategory("Home Consoles", "Hardware", "A video game device that is primarily used for home gamers, as opposed to in arcades or some other commercial establishment");
        productCategoryDataStore.add(homeConsoles);
        ProductCategory handHeldConsoles = new ProductCategory("Handheld Consoles", "Hardware", "They are smaller and portable, allowing people to carry them and play them at any time or place, along with microconsoles and dedicated consoles.");
        productCategoryDataStore.add(handHeldConsoles);
        ProductCategory hybridConsoles = new ProductCategory("Hybrid Consoles", "Hardware", "Can be used as both a stationary and portable device.");
        productCategoryDataStore.add(hybridConsoles);

        List<Product> productDataStore = new ArrayList<>();
        productDataStore.add(new Product("PlayStation 4 Pro", 399.99f, "USD", "The technology in the PlayStation 4 is similar to the hardware found in modern personal computers. This familiarity is designed to make it easier and less expensive for game studios to develop games for the PS4.", homeConsoles, sony));
        productDataStore.add(new Product("PS Vita", 249, "USD", "The PlayStation Vita (officially abbreviated PS Vita or Vita) is a handheld video game console developed and released by Sony Computer Entertainment. It is the successor to the PlayStation Portable as part of the PlayStation brand of gaming devices.", handHeldConsoles, sony));
        productDataStore.add(new Product("Switch", 299.99f, "USD", "The Nintendo Switch is a hybrid video game console, consisting of a console unit, a dock, and two Joy-Con controllers.", hybridConsoles, nintendo));
        productDataStore.add(new Product("Xbox One", 499, "USD", "The Xbox One is an eighth-generation home video game console that was developed by Microsoft.", homeConsoles, microsoft));
        productDataStore.add(new Product("New Nintendo 3DS", 150, "USD", "The New Nintendo 3DS is a handheld game console developed by Nintendo. It is the fourth system in the Nintendo 3DS family of handheld consoles, following the original Nintendo 3DS, the Nintendo 3DS XL, and the Nintendo 2DS.", handHeldConsoles, nintendo));

        ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc();
        List<Product> databaseProducts = productDaoJdbc.getAll();
        for (int i = 0; i < productDataStore.size(); i++){
            assertEquals(productDataStore.get(i).getPriceFloat(), databaseProducts.get(i).getPriceFloat());
            assertEquals(productDataStore.get(i).getProductCategory().getDepartment(),databaseProducts.get(i).getProductCategory().getDepartment());
            assertEquals(productDataStore.get(i).getSupplier().getName(),databaseProducts.get(i).getSupplier().getName());
            assertEquals(productDataStore.get(i).getDefaultCurrency(), databaseProducts.get(i).getDefaultCurrency());
        }
    }

    @Test
    void testGetBySupplier() throws SQLException {
        List<Supplier> supplierDataStore = new ArrayList<>();
        Supplier sony = new Supplier("Sony", "Consumer and professional electronics, gaming, entertainment and financial services");
        supplierDataStore.add(sony);

        List<ProductCategory> productCategoryDataStore = new ArrayList<>();
        ProductCategory handHeldConsoles = new ProductCategory("Handheld Consoles", "Hardware", "They are smaller and portable, allowing people to carry them and play them at any time or place, along with microconsoles and dedicated consoles.");
        productCategoryDataStore.add(handHeldConsoles);
        ProductCategory homeConsoles = new ProductCategory("Home Consoles", "Hardware", "A video game device that is primarily used for home gamers, as opposed to in arcades or some other commercial establishment");
        productCategoryDataStore.add(homeConsoles);

        List<Product> productDataStore = new ArrayList<>();
        productDataStore.add(new Product("PlayStation 4 Pro", 399.99f, "USD", "The technology in the PlayStation 4 is similar to the hardware found in modern personal computers. This familiarity is designed to make it easier and less expensive for game studios to develop games for the PS4.", homeConsoles, sony));
        productDataStore.add(new Product("PS Vita", 249, "USD", "The PlayStation Vita (officially abbreviated PS Vita or Vita) is a handheld video game console developed and released by Sony Computer Entertainment. It is the successor to the PlayStation Portable as part of the PlayStation brand of gaming devices.", handHeldConsoles, sony));

        ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc();
        List<Product> databaseProducts = productDaoJdbc.getAll();

        for (int i = 0; i < productDataStore.size(); i++){
            assertEquals(productDataStore.get(i).getPriceFloat(),databaseProducts.get(i).getPriceFloat());
            assertEquals(productDataStore.get(i).getDefaultCurrency(),databaseProducts.get(i).getDefaultCurrency());
            assertEquals(productDataStore.get(i).getSupplier().getName(),databaseProducts.get(i).getSupplier().getName());
            assertEquals(productDataStore.get(i).getProductCategory().getDepartment(),databaseProducts.get(i).getProductCategory().getDepartment());
        }
    }
}
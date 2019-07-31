package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier sony = new Supplier("Sony", "Consumer and professional electronics, gaming, entertainment and financial services");
        supplierDataStore.add(sony);
        Supplier nintendo = new Supplier("Nintendo", "Consumer electronics and video game company");
        supplierDataStore.add(nintendo);
        Supplier microsoft = new Supplier("Microsoft", "It develops, manufactures, licenses, supports and sells computer software, consumer electronics, personal computers, and related services");
        supplierDataStore.add(microsoft);

        //setting up a new product category
        ProductCategory homeConsoles = new ProductCategory("Home Consoles", "Hardware", "A video game device that is primarily used for home gamers, as opposed to in arcades or some other commercial establishment");
        productCategoryDataStore.add(homeConsoles);
        ProductCategory handHeldConsoles = new ProductCategory("Handheld Consoles", "Hardware", "They are smaller and portable, allowing people to carry them and play them at any time or place, along with microconsoles and dedicated consoles.");
        productCategoryDataStore.add(handHeldConsoles);
        ProductCategory hybridConsoles = new ProductCategory("Hybrid Consoles", "Hardware", "Can be used as both a stationary and portable device.");
        productCategoryDataStore.add(hybridConsoles);

        //setting up products and printing it
        productDataStore.add(new Product("PlayStation 4", 399.99f, "USD", "The technology in the PlayStation 4 is similar to the hardware found in modern personal computers. This familiarity is designed to make it easier and less expensive for game studios to develop games for the PS4.", homeConsoles, sony));
        productDataStore.add(new Product("Switch", 299.99f, "USD", "The Nintendo Switch is a hybrid video game console, consisting of a console unit, a dock, and two Joy-Con controllers.", homeConsoles, nintendo));
        productDataStore.add(new Product("Xbox One", 499, "USD", "The Xbox One is an eighth-generation home video game console that was developed by Microsoft.", homeConsoles, microsoft));
        productDataStore.add(new Product("PS Vita", 249, "USD", "The PlayStation Vita (officially abbreviated PS Vita or Vita) is a handheld video game console developed and released by Sony Computer Entertainment. It is the successor to the PlayStation Portable as part of the PlayStation brand of gaming devices.", homeConsoles, sony));
    }
}

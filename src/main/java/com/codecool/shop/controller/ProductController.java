package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/products"})
public class ProductController extends HttpServlet {
    private int suppliesID = 0;
    private int productCategoryID = 1;
    private List<Product> productsByCategory = new ArrayList<>();
    private List<Product> productsBySupplier = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = new ArrayList<>();
        SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        CartDao cartDataStore = CartDaoMem.getInstance();

        ProductDao productDataStore = ProductDaoMem.getInstance();
        try {
            productCategoryID = Integer.parseInt(req.getParameter("category_ID"));
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }
        productsByCategory = productDataStore.getBy(productCategoryDataStore.find(productCategoryID));
        productsBySupplier = productDataStore.getBy(supplierDataStore.find(suppliesID));
        for (int i = 0; i < productsByCategory.size(); i++) {
            if (productsBySupplier.contains(productsByCategory.get(i))) {
                products.add(productsByCategory.get(i));
            }
        }
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        try {
            String productName = req.getParameter("id");
            int prodId = Integer.parseInt(productName);
            Product product = productDataStore.find(prodId);
            cartDataStore.add(product);
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }
        if (suppliesID == 0) {
            context.setVariable("products", productsByCategory);
        } else {
            context.setVariable("products", products);
        }
        context.setVariable("suppliers", supplierDataStore.getAll());
        suppliesID = 0;

        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        suppliesID = Integer.parseInt(req.getParameter("Suppliers"));
        doGet(req, resp);
    }
}

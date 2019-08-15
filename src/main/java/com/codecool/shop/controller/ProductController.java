package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementationWIthJDBC.CartDaoJdbc;
import com.codecool.shop.dao.implementationWIthJDBC.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementationWIthJDBC.ProductDaoJdbc;
import com.codecool.shop.dao.implementationWIthJDBC.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/products"})
public class ProductController extends HttpServlet {
    private int suppliesID = 0;
    private int productCategoryID = 1;
    private List<Product> productsByCategory = new ArrayList<>();
    private List<Product> productsBySupplier = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = new ArrayList<>();
        SupplierDaoJdbc supplierDataStore = SupplierDaoJdbc.getInstance();
        ProductCategoryDaoJdbc productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        CartDaoJdbc cartDataStore = CartDaoJdbc.getInstance();
        ProductDao productDataStore = ProductDaoJdbc.getInstance();

        try {
            productCategoryID = Integer.parseInt(req.getParameter("category_ID"));
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }
        try {
            productsByCategory = productDataStore.getBy(productCategoryDataStore.find(productCategoryID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            productsBySupplier = productDataStore.getBy(supplierDataStore.find(suppliesID));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.getMessage();
        }
        for (Product product : productsByCategory) {
            if (productsBySupplier.contains(product)) {
                products.add(product);
            }
        }
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        try {
            context.setVariable("category", productCategoryDataStore.find(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        try {
            context.setVariable("suppliers", supplierDataStore.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        suppliesID = 0;
        context.setVariable("cartSize", cartDataStore.getCartSize());
        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            suppliesID = Integer.parseInt(req.getParameter("Suppliers"));
        }catch (NumberFormatException e){
            doGet(req,resp);
        }
        doGet(req, resp);
    }
}

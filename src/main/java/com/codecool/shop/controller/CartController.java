package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import org.graalvm.compiler.phases.OptimisticOptimizations;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDataStore = CartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        try {
            int productId = Integer.parseInt(req.getParameter("id"));
            cartDataStore.remove(productId);
        }catch (NumberFormatException e){
            e.getStackTrace();
        }
        Float totalPrice = 0F;
        for(CartItem cartItem : cartDataStore.getCart()){
            totalPrice += cartItem.getProduct().getPriceFloat()*cartItem.getQuantity();
        }
        for (CartItem cartItem : cartDataStore.getCart()) {
             cartItem.setSumPrice(cartItem.getQuantity()*cartItem.getProduct().getPriceFloat());
        }
        context.setVariable("cart", cartDataStore.getCart());
        context.setVariable("total", totalPrice);
        engine.process("product/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CartDao cartDataStore = CartDaoMem.getInstance();
        try {
            int prodQuantity = Integer.parseInt(request.getParameter("prodQuantity"));
            int prodId = Integer.parseInt(request.getParameter("prodId"));
            for(CartItem cartItem : cartDataStore.getCart()){
                if(cartItem.getProduct().getId() == prodId){
                    if(prodQuantity == 0){
                        cartDataStore.remove(prodId);
                        break;
                    }cartItem.setQuantity(prodQuantity);
                }
            }
        }catch (NumberFormatException e){
            e.getStackTrace();
        }
        doGet(request, response);
    }

}


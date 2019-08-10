package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.CartItem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        for (CartItem cartItem : cartDataStore.getCart()) {
             cartItem.setSumPrice(cartItem.getQuantity()*cartItem.getProduct().getPriceFloat());
        }
        context.setVariable("cart", cartDataStore.getCart());
        context.setVariable("total", cartDataStore.getTotalPrice());
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


package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDao orderDataStorage = OrderDaoMem.getInstance();
        CartDao cartDataStorage = CartDaoMem.getInstance();
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        Order order = new Order();
//
//        order.setName(req.getParameter("name"));
//        order.setEmail(req.getParameter("email"));
//        order.setPhoneNumber(req.getParameter("phoneNumber"));
//        order.setBillingAddress(req.getParameter("billingAddress"));
//        order.setShippingAddress(req.getParameter("shippingAddress"));
//        order.setCart(cartDataStorage.getCart());
//
//        orderDataStorage.add(order);
//        context.setVariable("orders",orderDataStorage);
//        resp.sendRedirect(req.getContextPath()+"/payment");
    }
}

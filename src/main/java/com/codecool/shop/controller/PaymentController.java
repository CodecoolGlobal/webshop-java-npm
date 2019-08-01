package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

import static java.lang.String.format;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartDao cartDaoMem = CartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());


        String method = request.getParameter("payment_type");
        try {
            if(method == null){
            method = "";
            }}catch (NullPointerException e ){
            e.getMessage();
        }context.setVariable("payment_method", method);

        context.setVariable("total", cartDaoMem.getTotalPrice());
        engine.process("product/payment.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartDao cartDao = CartDaoMem.getInstance();
        OrderDao orderDao = OrderDaoMem.getInstance();
        orderDao.getBy(1).setOrderTotalPrice();
        LocalTime time = LocalTime.now();
        Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        File file = new File("./src/logs"+"1"+format("%s", time)+".txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gsonBuilder.toJson(orderDao.getAll()));
        fileWriter.close();

        cartDao.clearCart();
        response.sendRedirect(request.getContextPath()+"/");
    }
}

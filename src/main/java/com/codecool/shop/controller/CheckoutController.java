package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.checkout.Address;
import com.codecool.shop.model.checkout.User;
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
        engine.process("checkout/checkout_form.html", context, resp.getWriter());

        // TODO: render checkout form, send post request with "Go to Payment" button or validate in js then do post methods things
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDataStore = UserDaoMem.getInstance();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNum = req.getParameter("phoneNumber");

        String billingCountry = req.getParameter("billingCountry");
        String billingCity = req.getParameter("billingCity");
        int billingZipcode = Integer.parseInt(req.getParameter("billingZipcode"));
        String billingAddress = req.getParameter("billingAddress");

        String shippingCountry = (req.getParameter("shippingCountry") == null) ? billingCountry : req.getParameter("billingCountry");
        String shippingCity = (req.getParameter("shippingCity") == null) ? billingCity : req.getParameter("shippingCity");
        int shippingZipcode = (req.getParameter("shippingZipcode") == null) ? billingZipcode : Integer.parseInt(req.getParameter("shippingZipcode"));
        String shippingAddress = (req.getParameter("shippingAddress") == null) ? billingAddress : req.getParameter("shippingAddress");

        User user = new User(firstName, lastName, email, phoneNum);

        Address userBillingAddress = new Address(user.getId(), billingCountry, billingCity, billingZipcode, billingAddress);
        Address userShippingAddress = new Address(user.getId(), shippingCountry, shippingCity, shippingZipcode, shippingAddress);

        user.setBillingAddress(userBillingAddress);
        user.setShippingAddress(userShippingAddress);

        userDataStore.add(user);

//        resp.sendRedirect(req.getContextPath() + "/payment"); // TODO: uncomment when payment is ready

    }
}

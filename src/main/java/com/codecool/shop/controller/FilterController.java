package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.SupplierService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/products"})
public class FilterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
        SupplierService supplierService = new SupplierService(supplierDataStore);
        List<Product> productsByCategory;
        List<Product> productsBySupplier;
        List<Product> filteredProductsBySupplier;
        List<Product> products;

        Gson gson = new Gson();

        PrintWriter out = response.getWriter();

        String categoryId = request.getParameter("categoryId");
        String supplierId = request.getParameter("supplierId");


        if (!Objects.equals(categoryId, "0") && Objects.equals(supplierId, "0")) {
            productsByCategory = productService.getProductsForCategory(Integer.parseInt(categoryId));
            out.println(gson.toJson(productsByCategory));
        } else if (Objects.equals(categoryId, "0") && !Objects.equals(supplierId, "0")) {
            productsBySupplier = productService.getProductsForSupplier(Integer.parseInt(supplierId));
            out.println(gson.toJson(productsBySupplier));
        } else if (!Objects.equals(categoryId, "0") && !Objects.equals(supplierId, "0")){
            productsByCategory = productService.getProductsForCategory(Integer.parseInt(categoryId));
            filteredProductsBySupplier =
                    productsByCategory.stream()
                            .filter(product -> product.getSupplier().getId() == Integer.parseInt(supplierId))
                            .collect(Collectors.toList());
            out.println(gson.toJson(filteredProductsBySupplier));
        } else {
            products = productDataStore.getAll();
            out.println(gson.toJson(products));
        }
    }
}

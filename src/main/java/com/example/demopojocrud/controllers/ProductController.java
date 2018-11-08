package com.example.demopojocrud.controllers;

import com.example.demopojocrud.models.Product;
import com.example.demopojocrud.services.implemetation.FileUploadService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private FileUploadService fileUploadService;

    public ProductController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
    private List<Product> products = new ArrayList<>();

    //show list of product from List<Product>
    @GetMapping("/product/all")
    public String showAllProductForm(Model model){
        model.addAttribute("products", products);
        return "product";
    }

    //call show add new product form
    @GetMapping("/product/add")
    public String showAddProductForm(Model model){
        model.addAttribute("product", new Product());
        return "add-product";
    }
    //fuction on add product submit button
    @GetMapping("/product/add/submit")
    public String addProduct(Product product, List<MultipartFile> files){
        product.setProductImages(this.fileUploadService.upload(files));
        this.products.add(product);
        return "product";
    }
}

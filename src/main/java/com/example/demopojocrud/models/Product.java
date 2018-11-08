package com.example.demopojocrud.models;

import java.util.List;

public class Product {
    private int id;
    private String productName;
    private double productPrice;
    private List<String> productImages;

    public Product() {
    }

    public Product(int id, String productName, double productPrice, List<String> productImages) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImages = productImages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productImages=" + productImages +
                '}';
    }
}

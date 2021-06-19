package com.example.ecommerce;

public class Product {
    private String Name;
    private String Price;
    private String Description;
    private String Category;
    private String SubCategory;
    private String Seller;

    public Product(String name, String price, String description, String category, String subCategory, String seller) {
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        Seller = seller;
    }
    public Product(String name, String price, String category, String subCategory, String seller) {
        Name = name;
        Price = price;
        Category = category;
        SubCategory = subCategory;
        Seller = seller;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(String subCategory) {
        SubCategory = subCategory;
    }

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }
}

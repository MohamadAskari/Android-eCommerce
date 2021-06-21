package com.example.ecommerce;

import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Product {
    private String Name;
    private String Price;
    private String Description;
    private String Category;
    private String SubCategory;
    private String Seller;
    private Uri ImageUrl;

    public Product(String name, String price, String description, String category, String subCategory, String seller) {
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        Seller = seller;
        //ImageUrl = "https://www.whitehouse.gov/wp-content/uploads/2021/01/08_martin_van_buren.jpg";
    }

    // with image path
    public Product(String imageUrl, String name, String price, String description, String category, String subCategory, String seller) {
        ImageUrl = Uri.parse(imageUrl);
        Name = name;
        Price = price;
        Category = category;
        SubCategory = subCategory;
        Seller = seller;
        //ImageUrl = "https://www.whitehouse.gov/wp-content/uploads/2021/01/08_martin_van_buren.jpg";
    }

    // with image Url
    public Product(Uri imageUrl, String name, String price, String description, String category, String subCategory, String seller) {
        ImageUrl = imageUrl;
        Name = name;
        Price = price;
        Category = category;
        SubCategory = subCategory;
        Seller = seller;
        //ImageUrl = "https://www.whitehouse.gov/wp-content/uploads/2021/01/08_martin_van_buren.jpg";
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

    public Uri getImageUrl() {
        return ImageUrl;
    }

    public String getImagePath() {
        return ImageUrl.toString();
    }

    public void setImageUrl(Uri imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setImageOnImageView(ImageView imageView){
        Picasso.get().load(ImageUrl).into(imageView);
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

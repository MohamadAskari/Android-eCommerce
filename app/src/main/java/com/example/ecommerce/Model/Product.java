package com.example.ecommerce.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Product implements Parcelable {
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
    }

    // with image path
    public Product(String imageUrl, String name, String price, String description, String category, String subCategory, String seller) {
        ImageUrl = Uri.parse(imageUrl);
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        Seller = seller;
    }

    // with image Url
    public Product(Uri imageUrl, String name, String price, String description, String category, String subCategory, String seller) {
        ImageUrl = imageUrl;
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        Seller = seller;
    }


    protected Product(Parcel in) {
        Name = in.readString();
        Price = in.readString();
        Description = in.readString();
        Category = in.readString();
        SubCategory = in.readString();
        Seller = in.readString();
        ImageUrl = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Price);
        dest.writeString(Description);
        dest.writeString(Category);
        dest.writeString(SubCategory);
        dest.writeString(Seller);
        dest.writeParcelable(ImageUrl, flags);
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

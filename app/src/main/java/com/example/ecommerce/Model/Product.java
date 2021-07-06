package com.example.ecommerce.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Product implements Parcelable {
    private final String Id;
    private String Name;
    private String Price;
    private String Description;
    private String Category;
    private String SubCategory;
    private String SellerUsername;
    private ArrayList<String> FavoriteAddedUsers;
    private Uri ImageUrl;

    public Product(String name, String price, String description, String category, String subCategory, String sellerUsername) {
        Id = String.valueOf(name.hashCode());
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerUsername = sellerUsername;
        FavoriteAddedUsers = new ArrayList<String>();
    }

    // with image path
    public Product(String imageUrl, String name, String price, String description, String category, String subCategory, String sellerUsername) {
        Id = String.valueOf(name.hashCode());
        ImageUrl = Uri.parse(imageUrl);
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerUsername = sellerUsername;
        FavoriteAddedUsers = new ArrayList<String>();
    }

    // with image Url
    public Product(Uri imageUrl, String name, String price, String description, String category, String subCategory, String sellerUsername) {
        Id = String.valueOf(name.hashCode());
        ImageUrl = imageUrl;
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerUsername = sellerUsername;
        FavoriteAddedUsers = new ArrayList<String>();
    }



    ////// retrieving product form database
    public Product(int id, String name, String price, String description, String category, String subCategory, String sellerUsername, ArrayList<String> favoriteAddedUsers) {
        Id = String.valueOf(id);
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerUsername = sellerUsername;
        FavoriteAddedUsers = favoriteAddedUsers;
    }
    // with image path
    public Product(int id, String imageUrl, String name, String price, String description, String category, String subCategory, String sellerUsername, ArrayList<String> favoriteAddedUsers) {
        this.Id = String.valueOf(id);
        ImageUrl = Uri.parse(imageUrl);
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerUsername = sellerUsername;
        FavoriteAddedUsers = favoriteAddedUsers;
    }
    // with image Url
    public Product(int id, Uri imageUrl, String name, String price, String description, String category, String subCategory, String sellerUsername, ArrayList<String> favoriteAddedUsers) {
        Id = String.valueOf(id);
        ImageUrl = imageUrl;
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerUsername = sellerUsername;
        FavoriteAddedUsers = favoriteAddedUsers;
    }
    //////



    protected Product(Parcel in) {
        Id = in.readString();
        Name = in.readString();
        Price = in.readString();
        Description = in.readString();
        Category = in.readString();
        SubCategory = in.readString();
        SellerUsername = in.readString();
        FavoriteAddedUsers = in.createStringArrayList();
        ImageUrl = in.readParcelable(Uri.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Name);
        dest.writeString(Price);
        dest.writeString(Description);
        dest.writeString(Category);
        dest.writeString(SubCategory);
        dest.writeString(SellerUsername);
        dest.writeStringList(FavoriteAddedUsers);
        dest.writeParcelable(ImageUrl, flags);
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

    public String getId() {
        return Id;
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

    public String getSellerUsername() {
        return SellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        SellerUsername = sellerUsername;
    }

    public ArrayList<String> getFavoriteAddedUsers() {
        return FavoriteAddedUsers;
    }

    public void addToFavoriteAddedUsers(String username) {
        FavoriteAddedUsers.add(username);
    }

    public void removeFromFavoriteAddedUsers(String username) {
        FavoriteAddedUsers.remove(username);
    }
}

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
    private String SellerPhonenumber;
    private boolean hasImage;
    private ArrayList<String> FavoriteAddedUsersPhonenumber;
    private Uri ImageUrl;

    public Product(String name, String price, String description, String category, String subCategory, String sellerPhonenumber) {
        Id = String.valueOf(name.hashCode());
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerPhonenumber = sellerPhonenumber;
        FavoriteAddedUsersPhonenumber = new ArrayList<String>();
    }

    // with image path
    public Product(String imageUrl, String name, String price, String description, String category, String subCategory, String sellerPhonenumber) {
        Id = String.valueOf(name.hashCode());
        ImageUrl = Uri.parse(imageUrl);
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerPhonenumber = sellerPhonenumber;
        FavoriteAddedUsersPhonenumber = new ArrayList<String>();
    }

    // with image Url
    public Product(Uri imageUrl, String name, String price, String description, String category, String subCategory, String sellerPhonenumber) {
        Id = String.valueOf(name.hashCode());
        ImageUrl = imageUrl;
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerPhonenumber = sellerPhonenumber;
        FavoriteAddedUsersPhonenumber = new ArrayList<String>();
    }



    ////// retrieving product form database
    public Product(int id, String name, String price, String description, String category, String subCategory, String sellerPhonenumber, ArrayList<String> favoriteAddedUsersPhonenumber) {
        Id = String.valueOf(id);
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerPhonenumber = sellerPhonenumber;
        FavoriteAddedUsersPhonenumber = favoriteAddedUsersPhonenumber;
    }
    // with image path
    public Product(int id, String imageUrl, String name, String price, String description, String category, String subCategory, String sellerPhonenumber, ArrayList<String> favoriteAddedUsersPhonenumber) {
        this.Id = String.valueOf(id);
        ImageUrl = Uri.parse(imageUrl);
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerPhonenumber = sellerPhonenumber;
        FavoriteAddedUsersPhonenumber = favoriteAddedUsersPhonenumber;
    }
    // with image Url
    public Product(int id, Uri imageUrl, String name, String price, String description, String category, String subCategory, String sellerPhonenumber, ArrayList<String> favoriteAddedUsersPhonenumber) {
        Id = String.valueOf(id);
        ImageUrl = imageUrl;
        Name = name;
        Price = price;
        Description = description;
        Category = category;
        SubCategory = subCategory;
        SellerPhonenumber = sellerPhonenumber;
        FavoriteAddedUsersPhonenumber = favoriteAddedUsersPhonenumber;
    }
    //////



    protected Product(Parcel in) {
        Id = in.readString();
        Name = in.readString();
        Price = in.readString();
        Description = in.readString();
        Category = in.readString();
        SubCategory = in.readString();
        SellerPhonenumber = in.readString();
        FavoriteAddedUsersPhonenumber = in.createStringArrayList();
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
        dest.writeString(SellerPhonenumber);
        dest.writeStringList(FavoriteAddedUsersPhonenumber);
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

    public String getSellerPhonenumber() {
        return SellerPhonenumber;
    }

    public void setSellerPhonenumber(String sellerPhonenumber) {
        SellerPhonenumber = sellerPhonenumber;
    }

    public boolean hasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public ArrayList<String> getFavoriteAddedUsersPhonenumber() {
        return FavoriteAddedUsersPhonenumber;
    }

    public void addToFavoriteAddedUsersPhonenumber(String phonenumber) {
        FavoriteAddedUsersPhonenumber.add(phonenumber);
    }

    public void removeFromFavoriteAddedUsers(String phonenumber) {
        FavoriteAddedUsersPhonenumber.remove(phonenumber);
    }

    @Override
    public String toString() {
        return
                "\n" +
                "Name : " + Name + "\n" +
                "Price : " + Price + "$" + "\n" +
                "Category : " + Category + "\n" +
                "SubCategory : " + SubCategory + "\n" +
                "Seller's phone number : " + SellerPhonenumber + "\n" ;
    }

    public String toString_Prioritize() {
        return Name + " - " + Price + "$" + " - " + SellerPhonenumber;
    }
}

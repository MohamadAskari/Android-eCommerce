package com.example.ecommerce.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // client
    public static final String CLIENT_USERNAME = "CLIENT_USERNAME";
    public static final String CLIENT_FIRSTNAME = "CLIENT_FIRSTNAME";
    public static final String CLIENT_LASTNAME = "CLIENT_LASTNAME";
    public static final String CLIENT_EMAIL = "CLIENT_EMAIL";
    public static final String CLIENT_PHONENUMBER = "CLIENT_PHONENUMBER";
    public static final String CLIENT_PASSWORD = "CLIENT_PASSWORD";
    public static final String IS_SELLER = "IS_SELLER";
    public static final String CLIENT_TABLE = "CLIENT_TABLE";
    public static final String CLIENT_LOGINCOUNT = "CLIENT_LOGINCOUNT";
    public static final String SELLER_PRODUCT_COUNT = "SELLER_PRODUCT_COUNT";
    public static final String CLIENT_PIC = "CLIENT_PIC";

    // admin
    public static final String ADMIN_USERNAME = "ADMIN_USERNAME";
    public static final String ADMIN_PASSWORD = "ADMIN_PASSWORD";
    public static final String ADMIN_TABLE = "ADMIN_TABLE";

    // product
    public static final String PRODUCTS_TABLE = "PRODUCTS_TABLE";
    public static final String ELECTRONIC_TABLE = "ELECTRONIC_TABLE";
    public static final String FASHION_TABLE = "FASHION_TABLE";
    public static final String HOME_TABLE = "HOME_TABLE";
    public static final String SPORTS_TABLE = "SPORTS_TABLE";
    public static final String MOTORS_TABLE = "MOTORS_TABLE";
    public static final String REALSTATE_TABLE = "REALSTATE_TABLE";
    public static final String ENTERTAINMENT_TABLE = "ENTERTAINMENT_TABLE";
    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String PRODUCT_NAME = "PRODUCT_NAME";
    public static final String PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String PRODUCT_CATEGORY = "PRODUCT_CATEGORY";
    public static final String PRODUCT_SUBCATEGORY = "PRODUCT_SUBCATEGORY";
    public static final String PRODUCT_SELLER = "PRODUCT_SELLER";
    public static final String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    public static final String PRODUCT_PIC = "PRODUCT_PIC";
    public static final String PRODUCT_ADDED_FAVORITE_USERS = "PRODUCT_ADDED_FAVORITE_USERS";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String onCreateTableString_Users = "CREATE TABLE " + CLIENT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + CLIENT_USERNAME + " TEXT , " + CLIENT_FIRSTNAME + " TEXT, " + CLIENT_LASTNAME + " TEXT, " + CLIENT_EMAIL + " TEXT, " + CLIENT_PHONENUMBER + " TEXT, " + CLIENT_PASSWORD + " TEXT, " + IS_SELLER + " BOOLEAN, " + CLIENT_LOGINCOUNT + " TEXT, " + SELLER_PRODUCT_COUNT + " TEXT, " + CLIENT_PIC + " TEXT ) ";
        String onCreateTableString_Admins = "CREATE TABLE " + ADMIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_USERNAME + " TEXT , " + ADMIN_PASSWORD + " TEXT ) ";
        String onCreateTableString_Products = "CREATE TABLE " + PRODUCTS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_CATEGORY + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT ) ";
        String onCreateTableString_Electronics = "CREATE TABLE " + ELECTRONIC_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT ) ";
        String onCreateTableString_Fashion = "CREATE TABLE " + FASHION_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT ) ";
        String onCreateTableString_Home = "CREATE TABLE " + HOME_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT ) ";
        String onCreateTableString_Sports = "CREATE TABLE " + SPORTS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT ) ";
        String onCreateTableString_Motors = "CREATE TABLE " + MOTORS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT ) ";
        String onCreateTableString_Realstate = "CREATE TABLE " + REALSTATE_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT ) ";
        String onCreateTableString_Entertainment = "CREATE TABLE " + ENTERTAINMENT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT ) ";


        db.execSQL(onCreateTableString_Users);
        db.execSQL(onCreateTableString_Admins);
        db.execSQL(onCreateTableString_Products);
        db.execSQL(onCreateTableString_Electronics);
        db.execSQL(onCreateTableString_Fashion);
        db.execSQL(onCreateTableString_Home);
        db.execSQL(onCreateTableString_Sports);
        db.execSQL(onCreateTableString_Motors);
        db.execSQL(onCreateTableString_Realstate);
        db.execSQL(onCreateTableString_Entertainment);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //method to add a new client
    public boolean addAdmin(Admin admin) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put(ADMIN_USERNAME, admin.getUsername());
        CV.put(ADMIN_PASSWORD, admin.getPassword());

        long added = DB.insert(ADMIN_TABLE, null, CV);

        return added != -1;
    }

    public boolean addClient(Client client){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put(CLIENT_USERNAME, client.getUserName());
        CV.put(CLIENT_FIRSTNAME, client.getFirstName());
        CV.put(CLIENT_LASTNAME, client.getLastName());
        CV.put(CLIENT_EMAIL, client.getEmail());
        CV.put(CLIENT_PHONENUMBER, client.getPhoneNumber());
        CV.put(CLIENT_PASSWORD, client.getPassword());
        CV.put(IS_SELLER, client.isSeller());
        CV.put(CLIENT_LOGINCOUNT, client.getLogin_count());
        CV.put(SELLER_PRODUCT_COUNT, client.getProduct_count());

        long added = DB.insert(CLIENT_TABLE, null, CV);

        return added != -1;
    }

    public List<Admin> getAdmins(){
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM " + ADMIN_TABLE;

        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery(query, null);

        if(cursor.moveToFirst()){
            //loop through the table of clients
            do {
                String UserName = cursor.getString(1);
                String Password = cursor.getString(2);
                Admin admin = new Admin(UserName, Password);
                admins.add(admin);

            } while (cursor.moveToNext());
        }
        else{
            //task failed, list will be empty
        }
        cursor.close();
        DB.close();
        return admins;
    }

    public boolean updateLoginCount(Client client, String newCount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(CLIENT_LOGINCOUNT, newCount);
        long updated =  DB.update(CLIENT_TABLE, CV, CLIENT_PHONENUMBER + " = ?" , new String[] {client.getPhoneNumber()});
        return updated != -1;
    }

    public boolean updateProductCount(Client client, String newCount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(SELLER_PRODUCT_COUNT, newCount);
        long updated =  DB.update(CLIENT_TABLE, CV, CLIENT_PHONENUMBER + " = ?" , new String[] {client.getPhoneNumber()});
        return updated != -1;
    }

    public Uri getProfileUri(String phonenumber){
        Uri uri = null;
        List<Client> allClients = this.getEveryClient();
        for(Client client : allClients){
            if(client.getPhoneNumber().equalsIgnoreCase(phonenumber)){
                uri = client.getImageUrl();
            }
        }
        return uri;
    }

    public boolean setProfilePic(Client client){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(CLIENT_PIC, client.getImagePath());
        long set = DB.update(CLIENT_TABLE, CV, CLIENT_PHONENUMBER + " = ?" , new String[] {client.getPhoneNumber()});
        return set != -1;
    }

    public List<Client> getEveryClient(){
        List<Client> clients = new ArrayList<>();

        //get every client's data
        String query = "SELECT * FROM " + CLIENT_TABLE;

        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery(query, null);

        if(cursor.moveToFirst()){
            //loop through the table of clients
            do {
                String UserName = cursor.getString(1);
                String FirstName = cursor.getString(2);
                String LastName = cursor.getString(3);
                String Email = cursor.getString(4);
                String PhoneNumber = cursor.getString(5);
                String Password = cursor.getString(6);
                boolean isSeller = cursor.getInt(7) == 1;
                String LoginCount = cursor.getString(8);
                String ProductCount = cursor.getString(9);
                String ImagePath = cursor.getString(10);
                Client client = new Client(UserName, FirstName, LastName, Email, PhoneNumber, Password, isSeller, Integer.parseInt(LoginCount), Integer.parseInt(ProductCount));
                client.setImageUrl(Uri.parse(ImagePath));
                clients.add(client);

            } while (cursor.moveToNext());
        }
        else{
            //task failed, list will be empty
        }
        cursor.close();
        DB.close();
        return clients;
    }

    public Client getClientByPhonenumber(String phonenumber){
        Client client;
        List<Client> clients = this.getEveryClient();
        for (Client c : clients){
            if(c.getPhoneNumber().equalsIgnoreCase(phonenumber)){
                client = new Client(c.getUserName(), c.getFirstName(), c.getLastName(),
                        c.getEmail(), c.getPhoneNumber(), c.getPassword(),
                        c.isSeller(), c.getLogin_count(), c.getProduct_count());
                return client;
            }
        }
        return null;
    }

    public boolean updatePassword(Client client, String newPassword) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        client.setPassword(newPassword);

        CV.put(CLIENT_PASSWORD, newPassword);

        long updated = DB.update(CLIENT_TABLE, CV, CLIENT_PHONENUMBER + " = ?" , new String[] {client.getPhoneNumber()});

        return updated != -1;
    }

    public boolean updateAllValues(Client client, String firstname, String lastname, String username, String email, String phonenumber, String ImagePath){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put(CLIENT_FIRSTNAME, firstname);
        CV.put(CLIENT_LASTNAME, lastname);
        CV.put(CLIENT_USERNAME, username);
        CV.put(CLIENT_EMAIL, email);
        CV.put(CLIENT_PHONENUMBER, phonenumber);
        CV.put(CLIENT_PIC, ImagePath);

        long updated = DB.update(CLIENT_TABLE, CV, CLIENT_PHONENUMBER + " = ?" , new String[] {client.getPhoneNumber()});

        return updated != -1;
    }

    public boolean addProduct(Product product) {

        boolean addedProductToCategory = false;

        switch (product.getCategory()) {
            case "Electronics": {
                addedProductToCategory = this.addProductToCategory(ELECTRONIC_TABLE, product);
                break;
            }
            case "Fashion": {
                addedProductToCategory = this.addProductToCategory(FASHION_TABLE, product);
                break;
            }
            case "Sports": {
                addedProductToCategory = this.addProductToCategory(SPORTS_TABLE, product);
                break;
            }
            case "Home": {
                addedProductToCategory = this.addProductToCategory(HOME_TABLE, product);
                break;
            }
            case "Motors": {
                addedProductToCategory = this.addProductToCategory(MOTORS_TABLE, product);
                break;
            }
            case "Real State": {
                addedProductToCategory = this.addProductToCategory(REALSTATE_TABLE, product);
                break;
            }
            case "Entertainment": {
                addedProductToCategory = this.addProductToCategory(ENTERTAINMENT_TABLE, product);
                break;
            }
        }

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put(PRODUCT_ID, product.getId());
        CV.put(PRODUCT_NAME, product.getName());
        CV.put(PRODUCT_PRICE, product.getPrice());
        CV.put(PRODUCT_DESCRIPTION, product.getDescription());
        CV.put(PRODUCT_CATEGORY, product.getCategory());
        CV.put(PRODUCT_SUBCATEGORY, product.getSubCategory());
        CV.put(PRODUCT_SELLER, product.getSellerPhonenumber());
        CV.put(PRODUCT_PIC, product.getImagePath());
        Gson gson = new Gson();
        CV.put(PRODUCT_ADDED_FAVORITE_USERS, gson.toJson(product.getFavoriteAddedUsersPhonenumber()));;

        long added = DB.insert(PRODUCTS_TABLE, null, CV);

        return (added != -1) && (addedProductToCategory);
    }

    public boolean addProductToCategory(String TableName, Product product){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put(PRODUCT_ID, product.getId());
        CV.put(PRODUCT_NAME, product.getName());
        CV.put(PRODUCT_PRICE, product.getPrice());
        CV.put(PRODUCT_DESCRIPTION, product.getDescription());
        CV.put(PRODUCT_SUBCATEGORY, product.getSubCategory());
        CV.put(PRODUCT_SELLER, product.getSellerPhonenumber());
        CV.put(PRODUCT_PIC, product.getImagePath());
        Gson gson = new Gson();
        CV.put(PRODUCT_ADDED_FAVORITE_USERS, gson.toJson(product.getFavoriteAddedUsersPhonenumber()));;

        long added = DB.insert(TableName, null, CV);

        return added != -1;
    }

    public List<Product> getAllProducts(){
        List<Product> products = new LinkedList<>();
        String query = "SELECT * FROM " + PRODUCTS_TABLE;

        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery(query, null);

        if(cursor.moveToFirst()){
            //loop through the table of clients
            do {
                int Id = Integer.parseInt(cursor.getString(1));
                String Name = cursor.getString(2);
                String Price = cursor.getString(3);
                String Description = cursor.getString(4);
                String Category = cursor.getString(5);;
                String SubCategory = cursor.getString(6);
                String Seller = cursor.getString(7);
                String ImagePath = cursor.getString(8);
                String FavoriteAddedUsers = cursor.getString(9);
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                Gson gson = new Gson();
                ArrayList<String> favoriteAddedUsers = gson.fromJson(FavoriteAddedUsers, type);
                Product product = new Product(Id, ImagePath, Name, Price, Description, Category, SubCategory, Seller, favoriteAddedUsers);
                products.add(product);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DB.close();
        return products;
    }

    public List<Product> getCategoryProducts(final String category_table){
        List<Product> products = new LinkedList<>();
        String query = "SELECT * FROM " + category_table;

        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery(query, null);

        if(cursor.moveToFirst()){
            //loop through the table of clients
            do {
                int Id = Integer.parseInt(cursor.getString(1));
                String Name = cursor.getString(2);
                String Price = cursor.getString(3);
                String Description = cursor.getString(4);
                String Category = category_table.substring(0, 1).toUpperCase() + category_table.substring(1, category_table.length() - 6).toLowerCase();
                String SubCategory = cursor.getString(5);
                String Seller = cursor.getString(6);
                String ImagePath = cursor.getString(7);
                String FavoriteAddedUsers = cursor.getString(8);
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                Gson gson = new Gson();
                ArrayList<String> favoriteAddedUsers = gson.fromJson(FavoriteAddedUsers, type);
                Product product = new Product(Id, ImagePath, Name, Price, Description, Category, SubCategory, Seller, favoriteAddedUsers);
                products.add(product);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DB.close();
        return products;
    }

    public boolean addProductToFavorites(Product product, String phonenumber){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        Gson gson = new Gson();
        product.addToFavoriteAddedUsersPhonenumber(phonenumber);
        String favoriteAddedUsers = gson.toJson(product.getFavoriteAddedUsersPhonenumber());

        CV.put(PRODUCT_ADDED_FAVORITE_USERS, favoriteAddedUsers);

        long updated =  DB.update(PRODUCTS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated1 =  DB.update(ELECTRONIC_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated2 =  DB.update(FASHION_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated3 =  DB.update(HOME_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated4 =  DB.update(SPORTS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated5 =  DB.update(MOTORS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated6 =  DB.update(REALSTATE_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated7 =  DB.update(ENTERTAINMENT_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});

        return updated != -1 && updated1 != -1 && updated2 != -1 && updated3 != -1 && updated4 != -1 && updated5 != -1
                && updated6 != -1 && updated7 != -1 ;
    }

    public boolean removeProductFromFavorites(Product product, String phonenumber){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        Gson gson = new Gson();
        product.removeFromFavoriteAddedUsers(phonenumber);
        String favoriteAddedUsers = gson.toJson(product.getFavoriteAddedUsersPhonenumber());

        CV.put(PRODUCT_ADDED_FAVORITE_USERS, favoriteAddedUsers);

        long updated =  DB.update(PRODUCTS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated1 =  DB.update(ELECTRONIC_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated2 =  DB.update(FASHION_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated3 =  DB.update(HOME_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated4 =  DB.update(SPORTS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated5 =  DB.update(MOTORS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated6 =  DB.update(REALSTATE_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated7 =  DB.update(ENTERTAINMENT_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});

        return updated != -1 && updated1 != -1 && updated2 != -1 && updated3 != -1 && updated4 != -1 && updated5 != -1
                && updated6 != -1 && updated7 != -1 ;
    }

    public boolean isProductInFavorites(Product product, String phonenumber){
        return product.getFavoriteAddedUsersPhonenumber().contains(phonenumber);
    }

    public List<Product> getAddedToFavoritesProducts(String phonenumber){

        List<Product> addedToFavoritesProducts = new ArrayList<>();

        for(Product product : getAllProducts())
            if(isProductInFavorites(product, phonenumber))
                addedToFavoritesProducts.add(product);

        return addedToFavoritesProducts;
    }
}

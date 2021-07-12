package com.example.ecommerce.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ecommerce.InCategory.CategoryUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    public static final String CLIENT_INTERESTS = "CLIENT_INTERESTS";

    // admin
    public static final String ADMIN_USERNAME = "ADMIN_USERNAME";
    public static final String ADMIN_PASSWORD = "ADMIN_PASSWORD";
    public static final String ADMIN_PROMOTED_PRODUCTS = "ADMIN_PROMOTED_PRODUCTS";
    public static final String ADMIN_TABLE = "ADMIN_TABLE";

    // product
    public static final String PRODUCTS_TABLE = "PRODUCTS_TABLE";
    public static final String ELECTRONICS_TABLE = "ELECTRONICS_TABLE";
    public static final String FASHION_TABLE = "FASHION_TABLE";
    public static final String HOME_TABLE = "HOME_TABLE";
    public static final String SPORTS_TABLE = "SPORTS_TABLE";
    public static final String MOTORS_TABLE = "MOTORS_TABLE";
    public static final String REALESTATE_TABLE = "REALESTATE_TABLE";
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
    public static final String PRODUCT_HAS_IMAGE = "PRODUCT_HAS_IMAGE";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String onCreateTableString_Users = "CREATE TABLE " + CLIENT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + CLIENT_USERNAME + " TEXT , " + CLIENT_FIRSTNAME + " TEXT, " + CLIENT_LASTNAME + " TEXT, " + CLIENT_EMAIL + " TEXT, " + CLIENT_PHONENUMBER + " TEXT, " + CLIENT_PASSWORD + " TEXT, " + IS_SELLER + " BOOLEAN, " + CLIENT_LOGINCOUNT + " TEXT, " + SELLER_PRODUCT_COUNT + " TEXT, " + CLIENT_PIC + " TEXT, " + CLIENT_INTERESTS + " TEXT ) ";
        String onCreateTableString_Admins = "CREATE TABLE " + ADMIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_USERNAME + " TEXT , " + ADMIN_PASSWORD + " TEXT ," + ADMIN_PROMOTED_PRODUCTS + " TEXT ) ";
        String onCreateTableString_Products = "CREATE TABLE " + PRODUCTS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_CATEGORY + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT , " + PRODUCT_HAS_IMAGE + " TEXT ) ";
        String onCreateTableString_Electronics = "CREATE TABLE " + ELECTRONICS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT , " + PRODUCT_HAS_IMAGE + " TEXT ) ";
        String onCreateTableString_Fashion = "CREATE TABLE " + FASHION_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT , " + PRODUCT_HAS_IMAGE + " TEXT ) ";
        String onCreateTableString_Home = "CREATE TABLE " + HOME_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT , " + PRODUCT_HAS_IMAGE + " TEXT ) ";
        String onCreateTableString_Sports = "CREATE TABLE " + SPORTS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT , " + PRODUCT_HAS_IMAGE + " TEXT ) ";
        String onCreateTableString_Motors = "CREATE TABLE " + MOTORS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT , " + PRODUCT_HAS_IMAGE + " TEXT ) ";
        String onCreateTableString_Realstate = "CREATE TABLE " + REALESTATE_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT , " + PRODUCT_HAS_IMAGE + " TEXT ) ";
        String onCreateTableString_Entertainment = "CREATE TABLE " + ENTERTAINMENT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " TEXT , " + PRODUCT_NAME + " TEXT , " + PRODUCT_PRICE + " TEXT , " + PRODUCT_DESCRIPTION + " TEXT , " + PRODUCT_SUBCATEGORY + " TEXT , " + PRODUCT_SELLER + " TEXT , " + PRODUCT_PIC + " TEXT , " + PRODUCT_ADDED_FAVORITE_USERS + " TEXT , " + PRODUCT_HAS_IMAGE + " TEXT ) ";


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

    // Admin methods
    public boolean addAdmin(Admin admin) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put(ADMIN_USERNAME, admin.getUsername());
        CV.put(ADMIN_PASSWORD, admin.getPassword());
        Gson gson = new Gson();
        CV.put(ADMIN_PROMOTED_PRODUCTS, gson.toJson(admin.getPromotedProductsID()));

        long added = DB.insert(ADMIN_TABLE, null, CV);

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
                String PromotedProducts = cursor.getString(3);
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                Gson gson = new Gson();
                ArrayList<String> promotedProductsIDs = gson.fromJson(PromotedProducts, type);
                Admin admin = new Admin(UserName, Password);
                admin.setPromotedProductsID(promotedProductsIDs);
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

    public boolean addProductToPromoted(Admin admin, ArrayList<String> IDs){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        admin.setPromotedProductsID(IDs);

//        for (String ID : IDs) {
//            if (!admin.getPromotedProductsID().contains(ID))
//                admin.addToPromotedProducts(ID);
//        }

        Gson gson = new Gson();
        String promotedProducts = gson.toJson(admin.getPromotedProductsID());

        CV.put(ADMIN_PROMOTED_PRODUCTS, promotedProducts);

        long updated = DB.update(ADMIN_TABLE, CV, ADMIN_USERNAME + " = ? " , new String[] {admin.getUsername()});

        return updated != -1;
    }

    public ArrayList<String> getAdminPromotedProducts(Admin admin){
        List<Product> AllProducts = getAllProducts();
        ArrayList<String> PromotedProducts = new ArrayList<>();
        for (Product product : AllProducts){
            if(admin.getPromotedProductsID().contains(product.getId()))
                PromotedProducts.add(product.getId());
        }

        return PromotedProducts;
    }


    public ArrayList<Product> getAllPromotedProducts(){
        ArrayList<Product> AllPromotedProducts = new ArrayList<>();
        List<Admin> AllAdmins = getAdmins();
        List<String> AdminsPromotedProducts;

        for (Admin admin : AllAdmins){
            AdminsPromotedProducts = admin.getPromotedProductsID();
            for (String productsID : AdminsPromotedProducts) {
                AllPromotedProducts.add(getProductByID(productsID));
            }
        }

        return AllPromotedProducts;
    }

    public boolean clearAllPromotedProducts(Admin admin){
        List<String> AllPromotedProducts = getAdminPromotedProducts(admin);
        boolean removed = false;
        for (String ID : AllPromotedProducts){
            removed = removeFromPromotedProducts(ID, admin);
        }
        return removed;
    }

    private boolean removeFromPromotedProducts(String id, Admin admin) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        Gson gson = new Gson();
        admin.removeFromPromotedProducts(id);
        String promotedProducts = gson.toJson(admin.getPromotedProductsID());

        CV.put(ADMIN_PROMOTED_PRODUCTS, promotedProducts);

        long updated =  DB.update(ADMIN_TABLE, CV, ADMIN_USERNAME + " = ?" , new String[] {admin.getUsername()});

        return updated != -1;
    }

    // Client methods
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
        Gson gson = new Gson();
        CV.put(CLIENT_INTERESTS, gson.toJson(client.getInterestedCategories()));;

        long added = DB.insert(CLIENT_TABLE, null, CV);

        return added != -1;
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

    public List<Product> getAllProductsOfClient(Client client){
        List<Product> AllProducts = this.getAllProducts();
        List<Product> AllProductsOfClient = new ArrayList<>();
        for (Product product : AllProducts){
            if(product.getSellerPhonenumber().equalsIgnoreCase(client.getPhoneNumber()))
                AllProductsOfClient.add(product);
        }
        return AllProductsOfClient;
    }

    public List<Client> getAllSellers(){
        List<Client> AllClients = this.getEveryClient();
        List<Client> AllSellers = new ArrayList<>();
        for (Client client : AllClients){
            if(client.isSeller())
                AllSellers.add(client);
        }
        return AllSellers;
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
                String InterestedCategories = cursor.getString(11);
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                Gson gson = new Gson();
                ArrayList<String> interestedCategories = gson.fromJson(InterestedCategories, type);
                Client client = new Client(UserName, FirstName, LastName, Email, PhoneNumber, Password, isSeller, Integer.parseInt(LoginCount), Integer.parseInt(ProductCount), interestedCategories);
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

    public Client getClientByUsername(String username){
        Client client;
        List<Client> clients = this.getEveryClient();
        for (Client c : clients){
            if(c.getUserName().equalsIgnoreCase(username)){
                client = new Client(c.getUserName(), c.getFirstName(), c.getLastName(),
                        c.getEmail(), c.getPhoneNumber(), c.getPassword(),
                        c.isSeller(), c.getLogin_count(), c.getProduct_count(), c.getInterestedCategories());
                return client;
            }
        }
        return null;
    }

    public Client getClientByPhonenumber(String phonenumber){
        Client client;
        List<Client> clients = this.getEveryClient();
        for (Client c : clients){
            if(c.getPhoneNumber().equalsIgnoreCase(phonenumber)){
                client = new Client(c.getUserName(), c.getFirstName(), c.getLastName(),
                        c.getEmail(), c.getPhoneNumber(), c.getPassword(),
                        c.isSeller(), c.getLogin_count(), c.getProduct_count(), c.getInterestedCategories());
                return client;
            }
        }
        return null;
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

    public boolean updatePassword(Client client, String newPassword) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        client.setPassword(newPassword);

        CV.put(CLIENT_PASSWORD, newPassword);

        long updated = DB.update(CLIENT_TABLE, CV, CLIENT_PHONENUMBER + " = ?" , new String[] {client.getPhoneNumber()});

        return updated != -1;
    }

    public boolean updateClientValues(Client client, String firstname, String lastname, String username, String email, String phonenumber, String ImagePath){
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

    public boolean addToInterestedCategories(Client client, String category){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        Gson gson = new Gson();
        client.addToInterestedCategories(category);
        String interestedCategories = gson.toJson(client.getInterestedCategories());

        CV.put(CLIENT_INTERESTS, interestedCategories);

        long updated =  DB.update(CLIENT_TABLE, CV, CLIENT_PHONENUMBER + " = ?" , new String[] {client.getPhoneNumber()});

        return updated != -1;
    }

    public boolean removeFromInterestedCategories(Client client, String category){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        Gson gson = new Gson();
        client.removeFromInterestedCategories(category);
        String interestedCategories = gson.toJson(client.getInterestedCategories());

        CV.put(CLIENT_INTERESTS, interestedCategories);

        long updated =  DB.update(CLIENT_TABLE, CV, CLIENT_PHONENUMBER + " = ?" , new String[] {client.getPhoneNumber()});

        return updated != -1;
    }

    // Product methods
    public boolean addProduct(Product product) {

        String category_table = CategoryUtils.getCategoryTableFromTitle(product.getCategory());
        boolean addedProductToCategory = this.addProductToCategory(category_table, product);

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
        CV.put(PRODUCT_ADDED_FAVORITE_USERS, gson.toJson(product.getFavoriteAddedUsersPhonenumber()));
        String hasImage = (product.hasImage()) ? "true" : "false";
        CV.put(PRODUCT_HAS_IMAGE, hasImage);

        long added = DB.insert(PRODUCTS_TABLE, null, CV);

        Log.d("TAG", hasImage);

        return (added != -1) && (addedProductToCategory);
    }

    public boolean removeProduct(Product product) {

        String category_table = CategoryUtils.getCategoryTableFromTitle(product.getCategory());
        boolean removeProductFromCategory = this.removeProductFromCategory(category_table, product);

        SQLiteDatabase DB = this.getWritableDatabase();

        long removed = DB.delete(PRODUCTS_TABLE, PRODUCT_ID + " = ?" ,new String[] {product.getId()});

        return (removed > 0) && (removeProductFromCategory);
    }

    public Product getProductByID(String id){
        List<Product> AllProducts = getAllProducts();
        for (Product product : AllProducts){
            if (product.getId().equalsIgnoreCase(id))
                return product;
        }
        return null;
    }

    public boolean updateProductValues(Product product){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        String category_table = CategoryUtils.getCategoryTableFromTitle(product.getCategory());
        boolean updatedProductInCategory = this.updateProductInCategory(category_table, product);

        CV.put(PRODUCT_NAME, product.getName());
        CV.put(PRODUCT_PRICE, product.getPrice());
        CV.put(PRODUCT_DESCRIPTION, product.getDescription());
        CV.put(PRODUCT_PIC, product.getImagePath());
        String hasImage = (product.hasImage()) ? "true" : "false";
        CV.put(PRODUCT_HAS_IMAGE, hasImage);

        long updated = DB.update(PRODUCTS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});

        return (updated != -1) && (updatedProductInCategory);
    }

    public boolean updateProductInCategory(String TableName, Product product){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put(PRODUCT_NAME, product.getName());
        CV.put(PRODUCT_PRICE, product.getPrice());
        CV.put(PRODUCT_DESCRIPTION, product.getDescription());
        CV.put(PRODUCT_PIC, product.getImagePath());
        String hasImage = (product.hasImage()) ? "true" : "false";
        CV.put(PRODUCT_HAS_IMAGE, hasImage);

        long updated = DB.update(TableName, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});

        return updated != -1;
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
        String hasImage = (product.hasImage()) ? "true" : "false";
        CV.put(PRODUCT_HAS_IMAGE, hasImage);

        long added = DB.insert(TableName, null, CV);

        return added != -1;
    }

    public boolean removeProductFromCategory(String TableName, Product product){
        SQLiteDatabase DB = this.getWritableDatabase();

        long removed = DB.delete(TableName,PRODUCT_ID + " = ?" ,new String[] {product.getId()});

        return removed > 0;
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
                String hasImage =  cursor.getString(10);
                product.setHasImage(hasImage.equals("true"));
                products.add(product);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DB.close();
        return products;
    }

    public List<Product> getInterestedProducts(Client client){

        if(client.getInterestedCategories().isEmpty())
            return getAllProducts();

        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor;
        List<Product> interestedProducts = new LinkedList<>();

        for(String category : client.getInterestedCategories()){

            String query = "SELECT * FROM " + category;
            cursor = DB.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do {
                    int Id = Integer.parseInt(cursor.getString(1));
                    String Name = cursor.getString(2);
                    String Price = cursor.getString(3);
                    String Description = cursor.getString(4);
                    String Category = CategoryUtils.getCategoryTitleFromTableName(category);
                    String SubCategory = cursor.getString(5);
                    String Seller = cursor.getString(6);
                    String ImagePath = cursor.getString(7);
                    String FavoriteAddedUsers = cursor.getString(8);
                    Type type = new TypeToken<ArrayList<String>>() {}.getType();
                    Gson gson = new Gson();
                    ArrayList<String> favoriteAddedUsers = gson.fromJson(FavoriteAddedUsers, type);
                    Product product = new Product(Id, ImagePath, Name, Price, Description, Category, SubCategory, Seller, favoriteAddedUsers);
                    String hasImage =  cursor.getString(9);
                    product.setHasImage(hasImage.equals("true"));
                    interestedProducts.add(product);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        DB.close();
        return interestedProducts;
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
                String hasImage =  cursor.getString(9);
                product.setHasImage(hasImage.equals("true"));
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
        long updated1 =  DB.update(ELECTRONICS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated2 =  DB.update(FASHION_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated3 =  DB.update(HOME_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated4 =  DB.update(SPORTS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated5 =  DB.update(MOTORS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated6 =  DB.update(REALESTATE_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
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
        long updated1 =  DB.update(ELECTRONICS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated2 =  DB.update(FASHION_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated3 =  DB.update(HOME_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated4 =  DB.update(SPORTS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated5 =  DB.update(MOTORS_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated6 =  DB.update(REALESTATE_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});
        long updated7 =  DB.update(ENTERTAINMENT_TABLE, CV, PRODUCT_ID + " = ?" , new String[] {product.getId()});

        return updated != -1 && updated1 != -1 && updated2 != -1 && updated3 != -1 && updated4 != -1 && updated5 != -1
                && updated6 != -1 && updated7 != -1 ;
    }

    public List<Product> getAddedToFavoritesProducts(String phonenumber){

        List<Product> addedToFavoritesProducts = new ArrayList<>();

        for(Product product : getAllProducts())
            if(isProductInFavorites(product, phonenumber))
                addedToFavoritesProducts.add(product);

        return addedToFavoritesProducts;
    }

    public boolean isProductInFavorites(Product product, String phonenumber){
        return product.getFavoriteAddedUsersPhonenumber().contains(phonenumber);
    }
}

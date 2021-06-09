package com.example.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CLIENT_USERNAME = "CLIENT_USERNAME";
    public static final String CLIENT_FIRSTNAME = "CLIENT_FIRSTNAME";
    public static final String CLIENT_LASTNAME = "CLIENT_LASTNAME";
    public static final String CLIENT_EMAIL = "CLIENT_EMAIL";
    public static final String CLIENT_PHONENUMBER = "CLIENT_PHONENUMBER";
    public static final String CLIENT_PASSWORD = "CLIENT_PASSWORD";
    public static final String IS_SELLER = "IS_SELLER";
    public static final String CLIENT_TABLE = "CLIENT_TABLE";
    public static final String ADMIN_USERNAME = "ADMIN_USERNAME";
    public static final String ADMIN_PASSWORD = "ADMIN_PASSWORD";
    public static final String ADMIN_TABLE = "ADMIN_TABLE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String onCreateTableString_Users = "CREATE TABLE " + CLIENT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + CLIENT_USERNAME + " TEXT , " + CLIENT_FIRSTNAME + " TEXT, " + CLIENT_LASTNAME + " TEXT, " + CLIENT_EMAIL + " TEXT, " + CLIENT_PHONENUMBER + " TEXT, " + CLIENT_PASSWORD + " TEXT, " + IS_SELLER + " BOOLEAN ) ";
        String onCreateTableString_Admins = "CREATE TABLE " + ADMIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_USERNAME + " TEXT , " + ADMIN_PASSWORD + " TEXT ) ";
        db.execSQL(onCreateTableString_Users);
        db.execSQL(onCreateTableString_Admins);
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
                //Client Type(Seller or Ordinary Client) should be considered too
                Client client = new Client(UserName, FirstName, LastName, Email, PhoneNumber, Password, isSeller);
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
}

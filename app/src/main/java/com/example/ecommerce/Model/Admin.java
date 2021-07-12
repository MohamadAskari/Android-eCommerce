package com.example.ecommerce.Model;

import java.util.ArrayList;

public class Admin {

    private static Admin active_admin;

    private String username;
    private String password;
    private ArrayList<String> PromotedProductsID;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        PromotedProductsID = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getPromotedProductsID() {
        return PromotedProductsID;
    }

    public void setPromotedProductsID(ArrayList<String> promotedProductsID) {
        PromotedProductsID = promotedProductsID;
    }

    public static Admin getActive_admin() {
        return active_admin;
    }

    public static void setActive_admin(Admin active_admin) {
        Admin.active_admin = active_admin;
    }

    public void addToPromotedProducts(String id){
        PromotedProductsID.add(id);
    }

    public void removeFromPromotedProducts(String id){
        PromotedProductsID.remove(id);
    }
}

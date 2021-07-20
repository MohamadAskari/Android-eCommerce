package com.example.ecommerce.InCategory;

import android.util.Log;
import android.widget.CheckBox;

import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CategoryUtils {

    private static List<Product> allProductsList;
    private static List<Product> filteredProducts = new LinkedList<>();
    private static String selectedCategory;
    private static boolean isFirstTimeInCategory;
    // save values
    public static int[] save_price_slider = new int[2];
    public static String save_price_from_value, save_price_to_value;
    public static Queue<Boolean> save_subCategoriesCheckboxes = new LinkedList<>();
    public static boolean save_only_with_image_checkbox, save_mostExpensive_rb, save_cheapest_rb;
    // All fragments with recyclerview
    private static boolean isInHomeFragment = true;
    private static boolean isInCategoryFragment = true;
    private static boolean isInFavoritesFragment = true;
    private static boolean isInManageProductsFragment = true;

    private CategoryUtils() {
        throw new RuntimeException("Nope!");
    }

    public static List<Product> getAllCategoryProducts(final String category, DataBaseHelper dataBaseHelper){
        allProductsList = dataBaseHelper.getCategoryProducts(category);
        return allProductsList; // bug : shows all when no product matches
    }

    public static List<Product> getFilteredCategoryProducts(){
        return filteredProducts;
    }

    public static void filterCategoryProducts(List<String> selectedSubCategories, int minPrice, int maxPrice, boolean withImageOnly, String sortType){
        filteredProducts.clear();
        for(Product p : allProductsList) {
            if (Integer.parseInt(p.getPrice()) <= maxPrice && Integer.parseInt(p.getPrice()) >= minPrice) {
                if(selectedSubCategories.contains(p.getSubCategory()) || selectedSubCategories.isEmpty()){
                    if(withImageOnly){
                        if(p.hasImage()){
                            filteredProducts.add(p);
                        }
                    }
                    else {
                        filteredProducts.add(p);
                    }
                }
            }
        }
        if(!filteredProducts.isEmpty()){
            switch (sortType){
                case "cheapest" :
                    Collections.sort(filteredProducts, productCheapestComparator);
                    break;
                case "most expensive" :
                    Collections.sort(filteredProducts, productMostExpensiveComparator);
                    break;
            }
        }
    }
    private static Comparator<Product> productCheapestComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Integer.parseInt(p1.getPrice()) - Integer.parseInt(p2.getPrice());
        }
    };
    private static Comparator<Product> productMostExpensiveComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Integer.parseInt(p2.getPrice()) - Integer.parseInt(p1.getPrice());
        }
    };

    public static int getMaxPrice(){
        int maxPrice = (!allProductsList.isEmpty()) ? Integer.parseInt(allProductsList.stream()
                .max(Comparator.comparing(v -> Integer.parseInt(v.getPrice()))).get().getPrice()) : 0;
        return Math.max(maxPrice, 10);
    }
    public static List<String> getAllSubCategoriesInCategory(String category) {
        List<String> subCategoriesList = new ArrayList<>();
        switch (category) {
            case "Electronics":
                subCategoriesList.add("Smart phones & Tablets");
                subCategoriesList.add("PC & Laptops");
                subCategoriesList.add("TV & Smart TV");
                subCategoriesList.add("Smart watches");
                subCategoriesList.add("Accessories");
                subCategoriesList.add("Audio");
                subCategoriesList.add("Gaming consoles");
                subCategoriesList.add("Others");
                return subCategoriesList;
            case "Fashion":
                subCategoriesList.add("Women clothing");
                subCategoriesList.add("Women shoes");
                subCategoriesList.add("Men clothing");
                subCategoriesList.add("Men shoes");
                subCategoriesList.add("Kids clothing");
                subCategoriesList.add("Kids shoes");
                subCategoriesList.add("Handbags");
                subCategoriesList.add("Jewelery");
                subCategoriesList.add("Watches");
                subCategoriesList.add("Accessories");
                subCategoriesList.add("Others");
                return subCategoriesList;
            case "Sports":
                subCategoriesList.add("Fitness");
                subCategoriesList.add("Running");
                subCategoriesList.add("Hunting");
                subCategoriesList.add("Winter sports");
                subCategoriesList.add("Water sports");
                subCategoriesList.add("Martial arts");
                subCategoriesList.add("Others");
                return subCategoriesList;
            case "Home":
                subCategoriesList.add("Furniture");
                subCategoriesList.add("Kitchen");
                subCategoriesList.add("Smart home");
                subCategoriesList.add("Yard & Garden");
                subCategoriesList.add("Tools");
                subCategoriesList.add("Home decoration");
                subCategoriesList.add("Others");
                return subCategoriesList;
            case "Motors":
                subCategoriesList.clear();
                subCategoriesList.add("Cars");
                subCategoriesList.add("Motorcycles");
                subCategoriesList.add("Tools");
                subCategoriesList.add("Vehicle parts");
                subCategoriesList.add("Others");
                return subCategoriesList;
            case "Real State":
                subCategoriesList.add("Office");
                subCategoriesList.add("Department");
                subCategoriesList.add("Store");
                subCategoriesList.add("Villa");
                subCategoriesList.add("Others");
                return subCategoriesList;
            case "Entertainment":
                subCategoriesList.add("Camping equipment");
                subCategoriesList.add("Toys");
                subCategoriesList.add("Pets");
                subCategoriesList.add("Collectibles & Art");
                subCategoriesList.add("Musical instruments");
                subCategoriesList.add("Video games");
                subCategoriesList.add("Books & Magazine");
                subCategoriesList.add("Others");
                return subCategoriesList;
        }
        return null;
    }

    public static void setSelectedCategory(String selectedCategory) {
        CategoryUtils.selectedCategory = selectedCategory;
    }

    public static String getSelectedCategory() {
        return selectedCategory;
    }

    public static String getSelectedCategoryTitle(){
        if(selectedCategory.equals("REALESTATE_TABLE"))
            return "Real Estate";
        return selectedCategory.substring(0, 1).toUpperCase() + selectedCategory.substring(1, selectedCategory.length() - 6).toLowerCase();
    }

    public static String getCategoryTitleFromTableName(String table_name){
        if(table_name.equals("REALESTATE_TABLE"))
            return "Real Estate";
        return table_name.substring(0, 1).toUpperCase() + table_name.substring(1, table_name.length() - 6).toLowerCase();
    }

    public static String getCategoryTableFromTitle(String category_title){
        switch (category_title) {
            case "Electronics": {
                return "ELECTRONICS_TABLE";
            }
            case "Fashion": {
                return "FASHION_TABLE";
            }
            case "Sports": {
                return "SPORTS_TABLE";
            }
            case "Home": {
                return "HOME_TABLE";
            }
            case "Motors": {
                return "MOTORS_TABLE";
            }
            case "Real Estate": {
                return "REALESTATE_TABLE";
            }
            case "Entertainment": {
                return "ENTERTAINMENT_TABLE";
            }
            default:
                return null;
        }
    }

    public static boolean isInHomeFragment() {
        return isInHomeFragment;
    }
    public static boolean isInCategoryFragment() {
        return isInCategoryFragment;
    }
    public static boolean isInFavoritesFragment() {
        return isInFavoritesFragment;
    }
    public static boolean isInManageProductsFragment() {
        return isInManageProductsFragment;
    }

    public static boolean isFirstTimeInCategory() {
        return isFirstTimeInCategory;
    }

    public static void setIsFirstTimeInCategory(boolean isFirstTimeInCategory) {
        CategoryUtils.isFirstTimeInCategory = isFirstTimeInCategory;
    }

    public static void setIsInHomeFragment() {
        isInHomeFragment = true;
        isInCategoryFragment = false;
        isInFavoritesFragment = false;
        isInManageProductsFragment = false;
    }

    public static void setIsInCategoryFragment() {
        isInHomeFragment = false;
        isInCategoryFragment = true;
        isInFavoritesFragment = false;
        isInManageProductsFragment = false;
    }

    public static void setIsInFavoritesFragment() {
        isInHomeFragment = false;
        isInCategoryFragment = false;
        isInFavoritesFragment = true;
        isInManageProductsFragment = false;
    }

    public static void setIsInManageProductsFragment() {
        isInHomeFragment = false;
        isInCategoryFragment = false;
        isInFavoritesFragment = false;
        isInManageProductsFragment = true;
    }
}

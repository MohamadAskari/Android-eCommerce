package com.example.ecommerce.InCategory;

import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CategoryUtils {

    private static List<Product> allProductsList;
    private static List<Product> filteredProducts = new LinkedList<>();
    private static boolean isInCategoryFragment;

    private CategoryUtils() {
        throw new RuntimeException("Nope!");
    }

    public static List<Product> getAllCategoryProducts(final String category, DataBaseHelper dataBaseHelper){
        allProductsList = dataBaseHelper.getCategoryProducts(category);
        return (getFilteredCategoryProducts().isEmpty()) ? allProductsList : filteredProducts; // bug : shows all when no product matches
    }
    public static List<Product> getFilteredCategoryProducts(){
        return filteredProducts;
    }
    public static void filterCategoryProducts(List<String> selectedSubCategories, int minPrice, int maxPrice, boolean withImageOnly, String sortType){
        // To Do : with images only
        filteredProducts.clear();
        for(Product p : allProductsList) {
            if (Integer.parseInt(p.getPrice()) <= maxPrice && Integer.parseInt(p.getPrice()) >= minPrice) {
                if(selectedSubCategories.contains(p.getSubCategory()) || selectedSubCategories.isEmpty()){
                    filteredProducts.add(p);
                }
            }
        }
        switch (sortType){
            case "cheapest" :
                Collections.sort(filteredProducts, productCheapestComparator);
                break;
            case "most expensive" :
                Collections.sort(filteredProducts, productMostExpensiveComparator);
                break;
        }
    }
    private static Comparator<Product> productCheapestComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getPrice().compareTo(p2.getPrice());
        }
    };
    private static Comparator<Product> productMostExpensiveComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p2.getPrice().compareTo(p1.getPrice());
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

    public static boolean isIsInCategoryFragment() {
        return isInCategoryFragment;
    }

    public static void setIsInCategoryFragment(boolean isInCategoryFragment) {
        CategoryUtils.isInCategoryFragment = isInCategoryFragment;
    }
}

package com.example.ecommerce.InCategory;

import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CategoryUtils {

    private static List<Product> allProductsList;

    private CategoryUtils() {
        throw new RuntimeException("Nope!");
    }

    public static List<Product> getAllCategoryProducts(final String category, DataBaseHelper dataBaseHelper){
        allProductsList = dataBaseHelper.getCategoryProducts(category);
        return allProductsList;
    }
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
}

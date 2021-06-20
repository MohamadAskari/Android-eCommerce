package com.example.ecommerce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class fragment_add_product extends Fragment {

    private Spinner parent_spinner, child_spinner;
    List<String> subcategories = new ArrayList<>();
    private EditText inputproductname, inputproductprice, inputproductdescription;
    private Button submit_btn;
    DataBaseHelper dataBaseHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        parent_spinner = view.findViewById(R.id.parent_spinner);
        child_spinner = view.findViewById(R.id.child_spinner);

        inputproductname = view.findViewById(R.id.product_name);
        inputproductprice = view.findViewById(R.id.product_price);
        inputproductdescription = view.findViewById(R.id.product_description);
        submit_btn = view.findViewById(R.id.submit_btn);
        dataBaseHelper = new DataBaseHelper(getActivity());

        List<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Fashion");
        categories.add("Sports");
        categories.add("Home");
        categories.add("Motors");
        categories.add("Real State");
        categories.add("Entertainment");

        ArrayAdapter<String> adapter_1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parent_spinner.setAdapter(adapter_1);
        parent_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Electronics")) {
                    subcategories.clear();
                    subcategories.add("Smart phones & Tablets");
                    subcategories.add("PC & Laptops");
                    subcategories.add("TV & Smart TV");
                    subcategories.add("Smart watches");
                    subcategories.add("Accessories");
                    subcategories.add("Audio");
                    subcategories.add("Gaming consoles");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Fashion")){
                    subcategories.clear();
                    subcategories.add("Women clothing");
                    subcategories.add("Women shoes");
                    subcategories.add("Men clothing");
                    subcategories.add("Men shoes");
                    subcategories.add("Kids clothing");
                    subcategories.add("Kids shoes");
                    subcategories.add("Handbags");
                    subcategories.add("Jewelery");
                    subcategories.add("Watches");
                    subcategories.add("Accessories");
                    subcategories.add("Others");

                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Sports")){
                    subcategories.clear();
                    subcategories.add("Fitness");
                    subcategories.add("Running");
                    subcategories.add("Hunting");
                    subcategories.add("Winter sports");
                    subcategories.add("Water sports");
                    subcategories.add("Martial arts");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Home")){
                    subcategories.clear();
                    subcategories.add("Furniture");
                    subcategories.add("Kitchen");
                    subcategories.add("Smart home");
                    subcategories.add("Yard & Garden");
                    subcategories.add("Tools");
                    subcategories.add("Home decoration");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Motors")){
                    subcategories.clear();
                    subcategories.add("Cars");
                    subcategories.add("Motorcycles");
                    subcategories.add("Tools");
                    subcategories.add("Vehicle parts");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Real State")){
                    subcategories.clear();
                    subcategories.add("Office");
                    subcategories.add("Department");
                    subcategories.add("Store");
                    subcategories.add("Villa");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Entertainment")){
                    subcategories.clear();
                    subcategories.add("Camping equipment");
                    subcategories.add("Toys");
                    subcategories.add("Pets");
                    subcategories.add("Collectibles & Art");
                    subcategories.add("Musical instruments");
                    subcategories.add("Video games");
                    subcategories.add("Books & Magazine");
                    subcategories.add("Others");
                    fillSpinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit_btn.setOnClickListener(v -> submitProduct());

        return view;
    }

    private void submitProduct() {

        String product_name = inputproductname.getText().toString();
        String product_price = inputproductprice.getText().toString();
        String product_description = inputproductdescription.getText().toString();
        String product_category = parent_spinner.getSelectedItem().toString();
        String product_subCategory = child_spinner.getSelectedItem().toString();
        String product_seller = ((HomeActivity)getActivity()).getActiveUsername();

        if(TextUtils.isEmpty(product_name) || TextUtils.isEmpty(product_price)){
            Toast.makeText(getActivity(), "Please fill out all required fields", Toast.LENGTH_LONG).show();
        }
        else {
            Product product;
            if(TextUtils.isEmpty(product_description))
                product = new Product(product_name, product_price, product_category, product_subCategory, product_seller);
            else
                product = new Product(product_name, product_price, product_description, product_category, product_subCategory, product_seller);

            boolean succeed = dataBaseHelper.addProduct(product);

            if(succeed) {
                Client client = dataBaseHelper.getClientByUsername(product_seller);
                int product_count = client.getProduct_count() + 1;
                boolean updatedProductCount = dataBaseHelper.updateProductCount(client, String.valueOf(product_count));
                if (updatedProductCount) {
                    Toast.makeText(getActivity(), "Product added successfully ", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                    ((HomeActivity)getActivity()).showFab();
                }
                else
                    Toast.makeText(getActivity(), "Task failed, try again", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getActivity(), "Failed to add", Toast.LENGTH_LONG).show();
        }

    }

    public void fillSpinner(){

        ArrayAdapter<String> adapter_2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, subcategories);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        child_spinner.setAdapter(adapter_2);
    }

}

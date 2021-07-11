package com.example.ecommerce.Admin;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ecommerce.Model.Admin;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.R;

import java.util.ArrayList;
import java.util.List;


public class PrioritizeFragment extends Fragment {

    private ListView products_lv;
    private ImageView back_btn;
    private AppCompatButton done_btn;
    private List<Product> AllProducts;
    private DataBaseHelper dataBaseHelper;
    private Admin active_admin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prioritize, container, false);
        List<Product> selectedProducts = new ArrayList<>();

        active_admin = Admin.getActive_admin();
        done_btn = view.findViewById(R.id.done_btn);
        back_btn = view.findViewById(R.id.prioritize_products_back_icon);
        products_lv = view.findViewById(R.id.lv_prioritize_products);
        dataBaseHelper = new DataBaseHelper(getActivity());
        AllProducts = dataBaseHelper.getAllProducts();


        ArrayList<String> ProductsInformation = new ArrayList<>();
        for (Product product : AllProducts)
            ProductsInformation.add(product.toString_Prioritize());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_multiple_choice, ProductsInformation);
        products_lv.setAdapter(arrayAdapter);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0 ; i < products_lv.getCount() ; i++){
                    if(products_lv.isItemChecked(i))
                        selectedProducts.add(AllProducts.get(i));
                }

                ArrayList<String> PromotedProductsID = new ArrayList<>();
                for (Product product : selectedProducts)
                    PromotedProductsID.add(product.getId());

                active_admin.setPromotedProductsID(PromotedProductsID);
            }
        });


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_admin, new AdminFragment()).commit();
            }
        });

        return view;
    }
}
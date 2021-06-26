package com.example.ecommerce.InCategory;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.RecyclerViewAdapter;
import com.example.ecommerce.R;

import java.util.ArrayList;
import java.util.List;


public class InCategoryProductsFragment extends Fragment {

    private String category;
    private EditText search_bar_in_category;
    private TextView tv_selected_category;
    private ImageView back_btn, filter_btn;
    private List<Product> InCategoryProductList = new ArrayList<>();
    DataBaseHelper dataBaseHelper;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Client ActiveClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_category_products, container, false);

        //ActiveClient = ((HomeActivity)getActivity()).getActiveClient();

        dataBaseHelper = new DataBaseHelper(getActivity());
        category = ((InCategoryProductsActivity)getActivity()).getSelectedCategory();
        InCategoryProductList = dataBaseHelper.getCategoryProducts(category);

        tv_selected_category = view.findViewById(R.id.tv_selected_category);
        String text = category.substring(0, category.length() - 6);
        tv_selected_category.setText(text);
        recyclerView = view.findViewById(R.id.lv_productList_in_category);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(InCategoryProductList, getActivity());
        recyclerView.setAdapter(mAdapter);

        filter_btn = view.findViewById(R.id.filter_btn);
        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.in_category_fragment_container, new FilterInCategoryFragment()).commit();
            }
        });

        back_btn = view.findViewById(R.id.in_category_back_icon);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        return view;
    }
}
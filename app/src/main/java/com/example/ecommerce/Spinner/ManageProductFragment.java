package com.example.ecommerce.Spinner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerce.InCategory.CategoryUtils;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.RecyclerViewAdapter;
import com.example.ecommerce.R;

import java.util.List;

public class ManageProductFragment extends Fragment {

    private List<Product> clientsProducts;
    private Client ActiveClient;
    private ImageView back_btn;
    DataBaseHelper dataBaseHelper;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_product, container, false);

        back_btn = view.findViewById(R.id.manage_products_back_icon);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                CategoryUtils.setIsInHomeFragment();
            }
        });


        CategoryUtils.setIsInManageProductsFragment();

        ActiveClient = Client.getActive_client();

        dataBaseHelper = new DataBaseHelper(getActivity());
        recyclerView = view.findViewById(R.id.lv_productList_manage_products);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        clientsProducts = dataBaseHelper.getAllProductsOfClient(ActiveClient);

        mAdapter = new RecyclerViewAdapter(clientsProducts, getActivity());
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
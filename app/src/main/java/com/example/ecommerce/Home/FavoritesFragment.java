package com.example.ecommerce.Home;

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

public class FavoritesFragment extends Fragment {

    private List<Product> favoriteProductList;
    DataBaseHelper dataBaseHelper;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Client ActiveClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        CategoryUtils.setIsInHomeFragment(false);

        ActiveClient = Client.getActive_client();

        dataBaseHelper = new DataBaseHelper(getActivity());
        recyclerView = view.findViewById(R.id.lv_productList_in_favorites);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        favoriteProductList = dataBaseHelper.getAddedToFavoritesProducts(ActiveClient.getUserName());

        mAdapter = new RecyclerViewAdapter(favoriteProductList, getActivity());
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        favoriteProductList = dataBaseHelper.getAddedToFavoritesProducts(ActiveClient.getUserName());
        mAdapter = new RecyclerViewAdapter(favoriteProductList, getActivity());
        recyclerView.setAdapter(mAdapter);
        super.onResume();
    }
}
package com.example.ecommerce.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.ecommerce.InCategory.CategoryUtils;
import com.example.ecommerce.InCategory.InCategoryProductsActivity;
import com.example.ecommerce.R;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class CategoriesFragment extends Fragment {

    private ScrollView scrollView;
    private Button expand_all_electronics, expand_all_fashion, expand_all_sports, expand_all_home, expand_all_motors, expand_all_realstate, expand_all_entertainment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        CategoryUtils.setIsInCategoryFragment();

        scrollView = view.findViewById(R.id.scrollview_categories);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        expand_all_electronics = view.findViewById(R.id.expand_all_electronics);
        expand_all_electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InCategoryProductsActivity.class);
                intent.putExtra("Category", "ELECTRONICS_TABLE");
                CategoryUtils.setIsFirstTimeInCategory(true);
                startActivity(intent);
            }
        });

        expand_all_fashion = view.findViewById(R.id.expand_all_fashion);
        expand_all_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InCategoryProductsActivity.class);
                intent.putExtra("Category", "FASHION_TABLE");
                CategoryUtils.setIsFirstTimeInCategory(true);
                startActivity(intent);
            }
        });

        expand_all_sports = view.findViewById(R.id.expand_all_sports);
        expand_all_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InCategoryProductsActivity.class);
                intent.putExtra("Category", "SPORTS_TABLE");
                CategoryUtils.setIsFirstTimeInCategory(true);
                startActivity(intent);
            }
        });

        expand_all_home = view.findViewById(R.id.expand_all_home);
        expand_all_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InCategoryProductsActivity.class);
                intent.putExtra("Category", "HOME_TABLE");
                CategoryUtils.setIsFirstTimeInCategory(true);
                startActivity(intent);
            }
        });

        expand_all_motors = view.findViewById(R.id.expand_all_motors);
        expand_all_motors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InCategoryProductsActivity.class);
                intent.putExtra("Category", "MOTORS_TABLE");
                CategoryUtils.setIsFirstTimeInCategory(true);
                startActivity(intent);
            }
        });

        expand_all_realstate = view.findViewById(R.id.expand_all_realstate);
        expand_all_realstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InCategoryProductsActivity.class);
                intent.putExtra("Category", "REALESTATE_TABLE");
                CategoryUtils.setIsFirstTimeInCategory(true);
                startActivity(intent);
            }
        });

        expand_all_entertainment = view.findViewById(R.id.expand_all_entertainment);
        expand_all_entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InCategoryProductsActivity.class);
                intent.putExtra("Category", "ENTERTAINMENT_TABLE");
                CategoryUtils.setIsFirstTimeInCategory(true);
                startActivity(intent);
            }
        });

        return view;
    }
}
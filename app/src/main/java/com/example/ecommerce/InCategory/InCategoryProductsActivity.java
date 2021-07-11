package com.example.ecommerce.InCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.ecommerce.Model.Client;
import com.example.ecommerce.R;

public class InCategoryProductsActivity extends AppCompatActivity {

    private Client ActiveClient;
    private String selected_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_category_products);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ActiveClient = Client.getActive_client();

        selected_category = (String) getIntent().getSerializableExtra("Category");
        CategoryUtils.setSelectedCategory(selected_category);

        setCurrentFragment(new InCategoryProductsFragment(), ActiveClient);
    }

    private void setCurrentFragment(Fragment fragment, Client client){
        getSupportFragmentManager().beginTransaction().replace(R.id.in_category_fragment_container, fragment).commit();
    }
}
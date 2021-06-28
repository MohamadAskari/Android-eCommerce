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

        ActiveClient = (Client) getIntent().getSerializableExtra("Active User");

        selected_category = (String) getIntent().getSerializableExtra("Category");

        setCurrentFragment(new InCategoryProductsFragment(), ActiveClient);
    }

    private void setCurrentFragment(Fragment fragment, Client client){
        Bundle bundle = sendData(client);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.in_category_fragment_container, fragment).commit();
    }

    private Bundle sendData(Client client) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Active User", client);
        return bundle;
    }

    public String getSelectedCategory(){
        return selected_category;
    }

    public String getSelectedCategoryTitle(){
        return selected_category.substring(0, 1).toUpperCase() + selected_category.substring(1, selected_category.length() - 6).toLowerCase();
    }

}
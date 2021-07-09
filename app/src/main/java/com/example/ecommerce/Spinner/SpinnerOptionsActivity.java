package com.example.ecommerce.Spinner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ecommerce.Model.Client;
import com.example.ecommerce.R;

public class SpinnerOptionsActivity extends AppCompatActivity {

    private String item;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_options);

        Bundle bundle = getIntent().getExtras();

        Client ActiveClient = Client.getActive_client();

        if (bundle != null)
            item = bundle.getString("Selected Item");

        switch (item){
            case "Edit your profile":{
                Fragment fragment = new EditProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_spinner, fragment).commit();
                break;
            }
            case "Manage your products":{
                Fragment fragment = new ManageProductFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_spinner, fragment).commit();
                break;
            }
            case "Setting":{
                Fragment fragment = new SettingFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_spinner, fragment).commit();
                break;
            }
        }

    }

    public void onBackPressed() {

    }

}

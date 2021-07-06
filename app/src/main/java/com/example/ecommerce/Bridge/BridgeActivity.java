package com.example.ecommerce.Bridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ecommerce.Model.Client;
import com.example.ecommerce.R;

public class BridgeActivity extends AppCompatActivity {

    private Client ActiveClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_bridge, new SetProfilePicFragment()).commit();
    }

    @Override
    public void onBackPressed() {

    }
}

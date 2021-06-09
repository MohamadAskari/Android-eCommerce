package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button login_btn, register_btn;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(this);

        if(dataBaseHelper.getAdmins().isEmpty()){
            Admin admin1 = new Admin("Ali_Ghasemi", "2710376601");
            Admin admin2 = new Admin("Mohamad_Askari", "2710380773");

            boolean succeed1 = dataBaseHelper.addAdmin(admin1);
            boolean succeed2 = dataBaseHelper.addAdmin(admin2);
        }


        login_btn = (Button) findViewById(R.id.log_in_btn);
        register_btn = (Button) findViewById(R.id.register_btn);

        login_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
        });

        register_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
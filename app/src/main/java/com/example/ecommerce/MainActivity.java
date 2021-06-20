package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;


import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String active_username;
    private Button login_btn, register_btn;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(this);

        if (dataBaseHelper.getAdmins().isEmpty()) {
            Admin admin1 = new Admin("Ali_Ghasemi", "2710376601");
            Admin admin2 = new Admin("Mohamad_Askari", "2710380773");

            dataBaseHelper.addAdmin(admin1);
            dataBaseHelper.addAdmin(admin2);
        }


        login_btn = findViewById(R.id.log_in_btn);
        register_btn = findViewById(R.id.register_btn);

        login_btn.setEnabled(false);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(MainActivity.this, R.id.fragment_main).navigate(R.id.action_registerFragment_to_logInFragment);
                login_btn.setEnabled(false);
                register_btn.setEnabled(true);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(MainActivity.this, R.id.fragment_main).navigate(R.id.action_logInFragment_to_registerFragment);
                register_btn.setEnabled(false);
                login_btn.setEnabled(true);
            }
        });
    }
    public void hideButtons(){
        login_btn.setVisibility(View.INVISIBLE);
        register_btn.setVisibility(View.INVISIBLE);
    }
}
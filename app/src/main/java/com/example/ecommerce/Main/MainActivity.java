package com.example.ecommerce.Main;

import androidx.appcompat.app.AppCompatActivity;


import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.ecommerce.Model.Admin;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.R;

public class MainActivity extends AppCompatActivity {

    private RadioButton login_btn, register_btn;
    private ImageView back_btn;
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
        back_btn = findViewById(R.id.forgotpassword_back_button);
        hideBackButton();

        login_btn.setEnabled(false);
        login_btn.setChecked(true);
        register_btn.setEnabled(true);

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

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(MainActivity.this, R.id.fragment_main).navigate(R.id.action_fragment_forgot_password_to_logInFragment);
                showButtons();
                hideBackButton();
            }
        });
    }
    public void hideButtons(){
        login_btn.setVisibility(View.INVISIBLE);
        register_btn.setVisibility(View.INVISIBLE);
    }
    public void showButtons(){
        login_btn.setVisibility(View.VISIBLE);
        register_btn.setVisibility(View.VISIBLE);
    }
    public void hideBackButton(){
        back_btn.setVisibility(View.GONE);
    }
    public void showBackButton(){
        back_btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {

    }
}
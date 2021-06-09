package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LogInActivity extends AppCompatActivity {

    private Button login_btn;
    private EditText inputUsername, inputPassword;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        login_btn = (Button)findViewById(R.id.login_btn);
        inputUsername = (EditText)findViewById(R.id.username_login);
        inputPassword = (EditText)findViewById(R.id.password_login);
        dataBaseHelper = new DataBaseHelper(this);

        login_btn.setOnClickListener(v -> {
            String username = inputUsername.getText().toString();
            String password = inputPassword.getText().toString();
            List<Admin> admins = dataBaseHelper.getAdmins();
            List<Client> clients = dataBaseHelper.getEveryClient();
            boolean flag = true;
            for (Admin admin : admins){
                if(admin.getUsername().equalsIgnoreCase(username) && admin.getPassword().equals(password)){
                    Toast.makeText(this, "Welcome back " + admin.getUsername(), Toast.LENGTH_LONG).show();
                    flag = false;
                    //go to the next activity...
                }
            }
            for (Client client : clients){
                if(client.getUserName().equalsIgnoreCase(username) && client.getPassword().equals(password)){
                    Toast.makeText(this, "Welcome back " + client.getUserName(), Toast.LENGTH_LONG).show();
                    flag = false;
                    //go to the next activity...
                }
            }
            if(flag)
                Toast.makeText(this,"Invalid Username or Password", Toast.LENGTH_LONG).show();
        });
    }
}
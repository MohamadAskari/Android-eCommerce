package com.example.ecommerce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LogInFragment extends Fragment {

    private Button login_btn;
    private EditText inputUsername, inputPassword;
    DataBaseHelper dataBaseHelper;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        login_btn = view.findViewById(R.id.login_btn);
        inputUsername = view.findViewById(R.id.username_login);
        inputPassword = view.findViewById(R.id.password_login);
        dataBaseHelper = new DataBaseHelper(getActivity());

        login_btn.setOnClickListener(v -> {
            String username = inputUsername.getText().toString();
            String password = inputPassword.getText().toString();
            List<Admin> admins = dataBaseHelper.getAdmins();
            List<Client> clients = dataBaseHelper.getEveryClient();
            boolean flag = true;
            for (Admin admin : admins){
                if(admin.getUsername().equalsIgnoreCase(username) && admin.getPassword().equals(password)){
                    Toast.makeText(getActivity(), "Welcome back " + admin.getUsername(), Toast.LENGTH_LONG).show();
                    flag = false;
                    //go to the next activity...
                }
            }
            for (Client client : clients){
                if(client.getUserName().equalsIgnoreCase(username) && client.getPassword().equals(password)){
                    Toast.makeText(getActivity(), "Welcome back " + client.getUserName(), Toast.LENGTH_LONG).show();
                    flag = false;
                    //go to the next activity...
                }
            }
            if(flag)
                Toast.makeText(getActivity(),"Invalid Username or Password", Toast.LENGTH_LONG).show();
        });

        return view;
    }
}
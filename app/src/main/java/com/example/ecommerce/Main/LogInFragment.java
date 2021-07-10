package com.example.ecommerce.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Admin.AdminActivity;
import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.Model.Admin;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.R;

import java.util.List;

public class LogInFragment extends Fragment {

    private Button login_btn;
    private EditText inputUsername, inputPassword;
    private TextView forgotPassword;
    DataBaseHelper dataBaseHelper;
    private CheckBox remember_me_checkbox;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        login_btn = view.findViewById(R.id.login_btn);
        inputUsername = view.findViewById(R.id.username_login);
        inputPassword = view.findViewById(R.id.password_login);
        remember_me_checkbox = view.findViewById(R.id.remember_me_checkbox);
        forgotPassword = view.findViewById(R.id.forgot_password_textView);
        dataBaseHelper = new DataBaseHelper(getActivity());

        List<Admin> admins = dataBaseHelper.getAdmins();
        List<Client> clients = dataBaseHelper.getEveryClient();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
        boolean checkbox_value = sharedPreferences.getBoolean("remember", false);
        if (checkbox_value) {
            inputUsername.setText(sharedPreferences.getString("username", ""));
            inputPassword.setText(sharedPreferences.getString("password", ""));
            remember_me_checkbox.setChecked(true);
            /*Intent intent = new Intent(getActivity(), HomeActivity.class);
            Client.setActive_client(dataBaseHelper.getClientByUsername(inputUsername));
            startActivity(intent);*/
        }

        login_btn.setOnClickListener(v -> {
            String username = inputUsername.getText().toString();
            String password = inputPassword.getText().toString();
            boolean flag = true;
            for (Admin admin : admins){
                if(admin.getUsername().equalsIgnoreCase(username) && admin.getPassword().equals(password)){
                    Toast.makeText(getActivity(), "Welcome back " + admin.getUsername(), Toast.LENGTH_LONG).show();
                    flag = false;
                    Intent intent = new Intent(getActivity(), AdminActivity.class);
                    startActivity(intent);
                }
            }
            for (Client client : clients){
                if(client.getUserName().equalsIgnoreCase(username) && client.getPassword().equals(password)){
                    int login_count = client.getLogin_count() + 1;
                    boolean updated = dataBaseHelper.updateLoginCount(client, String.valueOf(login_count));
                    if (updated) {
                        //// remember me checkbox
                        if(remember_me_checkbox.isChecked()){
                            SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("remember", true);
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.apply();
                        }
                        else {
                            SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("remember", false);
                            editor.apply();
                        }
                        ////

                        Toast.makeText(getActivity(), "Welcome back " + client.getUserName(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        Client.setActive_client(client);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getActivity(), "Task failed, try again", Toast.LENGTH_LONG).show();
                    flag = false;

                }
            }
            if(flag)
                Toast.makeText(getActivity(),"Invalid Username or Password", Toast.LENGTH_LONG).show();
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.fragment_main).navigate(R.id.action_logInFragment_to_fragment_forgot_password);
                ((MainActivity)getActivity()).hideButtons();
            }
        });

        return view;
    }
}
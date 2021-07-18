package com.example.ecommerce.Main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerce.Bridge.BridgeActivity;
import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.Model.Admin;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterFragment extends Fragment {

    private Button signup_btn;
    private EditText inputfirstname, inputlastname, inputusername, inputemail, inputphonenumber, inputpass, inputconfirmpass;
    private CheckBox inputcheckbox;
    DataBaseHelper dataBaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        inputcheckbox = view.findViewById(R.id.checkBox);
        signup_btn = view.findViewById(R.id.signup_btn);
        inputfirstname = view.findViewById(R.id.firstname_register);
        inputlastname = view.findViewById(R.id.lastname_register);
        inputusername = view.findViewById(R.id.username_register);
        inputemail = view.findViewById(R.id.email_register);
        inputphonenumber = view.findViewById(R.id.phonenumber_register);
        inputpass = view.findViewById(R.id.password_register);
        inputconfirmpass = view.findViewById(R.id.password_confirmation_register);
        dataBaseHelper = new DataBaseHelper(getActivity());

        int nightModeFlags = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            signup_btn.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.bg_button_night));
            inputfirstname.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
            inputlastname.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
            inputusername.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
            inputemail.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
            inputphonenumber.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
            inputpass.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
            inputconfirmpass.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
        }

        signup_btn.setOnClickListener(v -> createAccount());


        return view;
    }

    private void createAccount() {
        String username = inputusername.getText().toString();
        String firstname = inputfirstname.getText().toString();
        String lastname = inputlastname.getText().toString();
        String email = inputemail.getText().toString();
        String phonenumber = inputphonenumber.getText().toString();
        String pass = inputpass.getText().toString();
        String confirmpass = inputconfirmpass.getText().toString();
        boolean isSeller = inputcheckbox.isChecked();


        if(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(username) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(phonenumber) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmpass)){
            Toast.makeText(getActivity(), "Please fill out all required fields", Toast.LENGTH_LONG).show();
        }
        else if (!isEmailValid(email)){
            Toast.makeText(getActivity(), "Please enter a valid email", Toast.LENGTH_LONG).show();
        }
        else if(!pass.equals(confirmpass)){
            Toast.makeText(getActivity(), "Your password and confirmation password do not match", Toast.LENGTH_LONG).show();
        }
        else {
            Client newClient = new Client(username, firstname, lastname, email, phonenumber, pass, isSeller, new ArrayList<>());
            //check if username already exists
            boolean UsernameExists = checkIfUsernameExists(newClient);
            boolean EmailExists = checkIfEmailExists(newClient);
            boolean PhonenumberExists = checkIfPhonenumberExists(newClient);
            if(!UsernameExists && !PhonenumberExists && !EmailExists){
                boolean succeed = dataBaseHelper.addClient(newClient);
                if(succeed) {
                    Toast.makeText(getActivity(), "Sign in was successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), BridgeActivity.class);

                    // pass the active user
                    // intent.putExtra("Active User", newClient);
                    Client.setActive_client(newClient);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getActivity(), "Failed to add", Toast.LENGTH_LONG).show();
            }
            else {
                if(UsernameExists)
                    Toast.makeText(getActivity(), getResources().getString(R.string.username_taken), Toast.LENGTH_LONG).show();
                else if(EmailExists)
                    Toast.makeText(getActivity(), getResources().getString(R.string.email_taken), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity(), getResources().getString(R.string.phonenumber_taken), Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean checkIfUsernameExists(Client client){
        List<Client> clients = dataBaseHelper.getEveryClient();
        List<Admin> admins = dataBaseHelper.getAdmins();
        for(Client c : clients){
            if(c.getUserName().equalsIgnoreCase(client.getUserName()))
                return true;
        }
        for (Admin a : admins){
            if(a.getUsername().equalsIgnoreCase(client.getUserName()))
                return true;
        }
        return false;
    }
    private boolean checkIfPhonenumberExists(Client client){
        List<Client> clients = dataBaseHelper.getEveryClient();
        for(Client c : clients){
            if(c.getPhoneNumber().equalsIgnoreCase(client.getPhoneNumber()))
                return true;
        }
        return false;
    }
    private boolean checkIfEmailExists(Client client){
        List<Client> clients = dataBaseHelper.getEveryClient();
        for(Client c : clients){
            if(c.getEmail().equalsIgnoreCase(client.getEmail()))
                return true;
        }
        return false;
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
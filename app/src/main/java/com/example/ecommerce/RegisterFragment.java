package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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

        signup_btn.setOnClickListener(v -> CreateAccount());


        return view;
    }

    private void CreateAccount() {
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
        else if(!pass.equals(confirmpass)){
            Toast.makeText(getActivity(), "Your password and confirmation password do not match", Toast.LENGTH_LONG).show();
        }
        else {
            Client newClient = new Client(username, firstname, lastname, email, phonenumber, pass, isSeller);
            //check if username already exists
            boolean UsernameExists = checkIfExists(newClient);
            if(!UsernameExists){
                boolean succeed = dataBaseHelper.addClient(newClient);
                if(succeed) {
                    Toast.makeText(getActivity(), "Sign in was successful, Welcome " + newClient.getUserName(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    //pass the active user
                    intent.putExtra("Active Username", username);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getActivity(), "Failed to add", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getActivity(), "This Username is already taken, pick a new one", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkIfExists(Client client){
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
}
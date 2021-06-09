package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private Button signup_btn;
    private EditText inputfirstname, inputlastname, inputusername, inputemail, inputphonenumber, inputpass, inputconfirmpass;
    private CheckBox inputcheckbox;
    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputcheckbox = (CheckBox)findViewById(R.id.checkBox);
        signup_btn = (Button)findViewById(R.id.signup_btn);
        inputfirstname = (EditText)findViewById(R.id.firstname_register);
        inputlastname = (EditText)findViewById(R.id.lastname_register);
        inputusername = (EditText)findViewById(R.id.username_register);
        inputemail = (EditText)findViewById(R.id.email_register);
        inputphonenumber = (EditText)findViewById(R.id.phonenumber_register);
        inputpass = (EditText)findViewById(R.id.password_register);
        inputconfirmpass = (EditText)findViewById(R.id.password_confirmation_register);
        dataBaseHelper = new DataBaseHelper(this);

        signup_btn.setOnClickListener(v -> CreateAccount());

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
            Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_LONG).show();
        }
        else if(!pass.equals(confirmpass)){
            Toast.makeText(this, "Your password and confirmation password do not match", Toast.LENGTH_LONG).show();
        }
        else {
            Client newClient = new Client(username, firstname, lastname, email, phonenumber, pass, isSeller);
            //check if username already exists
            boolean UsernameExists = checkIfExists(newClient);
            if(!UsernameExists){
                boolean succeed = dataBaseHelper.addClient(newClient);
                if(succeed) {
                    Toast.makeText(this, "Sign in was successful, Welcome " + newClient.getUserName(), Toast.LENGTH_LONG).show();
                    //go to the next activity...

                }
                else
                    Toast.makeText(this, "Failed to add", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(this, "This Username is already taken, pick a new one", Toast.LENGTH_LONG).show();
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
            if(a.getUsername().equalsIgnoreCase(a.getUsername()))
                return true;
        }
        return false;
    }
}
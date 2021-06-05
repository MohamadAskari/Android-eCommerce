package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button signup_btn;
    private EditText inputfirstname, inputlastname, inputusername, inputemail, inputphonenumber, inputpass, inputconfirmpass;

    /*FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup_btn = (Button)findViewById(R.id.signup_btn);
        inputfirstname = (EditText)findViewById(R.id.firstname_register);
        inputlastname = (EditText)findViewById(R.id.lastname_register);
        inputusername = (EditText)findViewById(R.id.username_register);
        inputemail = (EditText)findViewById(R.id.email_register);
        inputphonenumber = (EditText)findViewById(R.id.phonenumber_register);
        inputpass = (EditText)findViewById(R.id.password_register);
        inputconfirmpass = (EditText)findViewById(R.id.password_confirmation_register);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String firstname = inputfirstname.getText().toString();
        String lastname = inputlastname.getText().toString();
        String username = inputusername.getText().toString();
        String email = inputemail.getText().toString();
        String phonenumber = inputphonenumber.getText().toString();
        String pass = inputpass.getText().toString();
        String confirmpass = inputconfirmpass.getText().toString();

        if(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(username) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(phonenumber) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmpass)){
            Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_LONG).show();
        }
        else if(!pass.equals(confirmpass)){
            Toast.makeText(this, "Your password and confirmation password do not match", Toast.LENGTH_LONG).show();
        }
        else {
            //validatephoneNumber(phonenumber);
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            reference.setValue("abc");
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        }
    }

    private void validatephoneNumber(String phonenumber) {

    }*/
}
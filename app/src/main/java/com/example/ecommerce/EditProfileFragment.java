package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment {

    private CircleImageView circleImageView;
    private AppCompatButton changePic_btn, changePass_btn, confirm_btn;
    private ImageView back_btn_image_view;
    private EditText firstName_et, lastName_et, username_et, email_et, phone_et;
    private DataBaseHelper dataBaseHelper;
    private List<Client> AllClients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        circleImageView = view.findViewById(R.id.change_picture_image_view);
        changePic_btn = view.findViewById(R.id.change_picture_btn);
        changePass_btn = view.findViewById(R.id.change_password_btn);
        confirm_btn = view.findViewById(R.id.edit_profile_confirm_btn);
        back_btn_image_view = view.findViewById(R.id.view_edit_back_icon);
        firstName_et = view.findViewById(R.id.edit_firstname_et);
        lastName_et = view.findViewById(R.id.edit_lastname_et);
        username_et = view.findViewById(R.id.edit_username_et);
        email_et = view.findViewById(R.id.edit_email_et);
        phone_et = view.findViewById(R.id.edit_phone_number_et);
        dataBaseHelper = new DataBaseHelper(getActivity());
        AllClients = dataBaseHelper.getEveryClient();

        Client ActiveClient = (Client) this.getArguments().getSerializable("Active User");

        firstName_et.setText(ActiveClient.getFirstName());
        lastName_et.setText(ActiveClient.getLastName());
        username_et.setText(ActiveClient.getUserName());
        email_et.setText(ActiveClient.getEmail());
        phone_et.setText(ActiveClient.getPhoneNumber());

        back_btn_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("Active Username", ActiveClient.getUserName());
                intent.putExtra("Is Seller", ActiveClient.isSeller());
                intent.putExtra("Active User", ActiveClient);
                startActivity(intent);

            }
        });

        changePic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO
            }
        });

        changePass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO
            }
        });

        username_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    boolean flag = false;
                    for(Client client : AllClients){
                        if(client.getUserName().equalsIgnoreCase(username_et.getText().toString()))
                            flag = true;
                    }
                    if(flag){
                        //TO DO
                        String warning_text = username_et.getText().toString() + "    " + R.string.app_name;
                        Toast.makeText(getActivity(), R.string.username_taken,Toast.LENGTH_LONG).show();
                        username_et.setText(warning_text);
                    }
                }
            }
        });

        return view;
    }
}